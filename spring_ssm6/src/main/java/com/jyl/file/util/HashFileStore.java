package com.jyl.file.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * 创建一个双层目录的hash目录结构<br/>
 * 文件名是文件的MD5值，如果有碰撞,可以假设文件其实是一样的，不处理,此时可以认为他们文件名和文件内容都是完全一样。
 * 
 * @see <a
 *      href="http://michaelandrews.typepad.com/the_technical_times/2009/10/creating-a-hashed-directory-structure.html"
 *      target="_blank">参考文档</a>
 */
public class HashFileStore implements FileStore, InitializingBean {
	
	private String baseDirectory;
	
	private Logger log = Logger.getLogger(HashFileStore.class);

	/**
	 * UID used in unique file name generation.
	 */
	private static final String UID = new java.rmi.server.UID().toString()
			.replace(':', '_').replace('-', '_');

	/**
	 * Counter used in unique identifier generation.
	 */
	private static int counter = 0;

	public String getBaseDirectory() {
		return baseDirectory;
	}

	/**
	 * 设置文件系统的基本目录
	 * 
	 * @param baseDirectory
	 *            目录名
	 */
	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public String createFile(String name, File file) {
		String result = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			FInfo f = this.createFile(name, is);
			result = f.getPath();
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException", e);
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					log.error("IOException", e);
					e.printStackTrace();
				}
		}

		return result;
	}

	public FInfo createFile(String name, InputStream is) {
		FInfo result = null;
		String hashpath = this.computeHashDir(name);

		File file = null;// 临时文件
		String tname = UID + "_" + getUniqueId() + ".tmp";// 临时文件的文件名
		File tDir = new File(this.getBaseDirectory() + hashpath);
		if (!tDir.exists())
			tDir.mkdirs();
		file = new File(this.getBaseDirectory() + hashpath + tname);

		BufferedOutputStream os = null;
		try {
			file.createNewFile();
			os = new BufferedOutputStream(new FileOutputStream(file));
			MessageDigest md = MessageDigest.getInstance("MD5");
			IOUtils.copy(new DigestInputStream(is, md), os);
			String md5 = new BigInteger(1, md.digest()).toString(16);
			hashpath = hashpath.concat(md5);
			result = new FInfo();
			result.setPath(hashpath);
			result.setSize(file.length());
		} catch (Exception e) {
			log.error("Exception", e);
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.error("IOException", e);
					// ignore
				}
			}
		}
		File target = new File(this.getBaseDirectory() + hashpath);
		if (target.exists()){
			file.delete();
		}else{
			file.renameTo(target);
		}
		return result;
	}

	public InputStream readFileStream(String path) {
		File file = new File(this.baseDirectory.concat(path));
		InputStream is = null;
		if (file.exists() && file.canRead()) {
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				log.error("FileNotFoundException", e);
				e.printStackTrace();
			}
		}
		return is;
	}

	public boolean deleteFile(String path) {
		File file = new File(this.baseDirectory.concat(path));
		return file.delete();
	}

	/**
	 * Spring 环境中 baseDirectory必须设置
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.baseDirectory, "baseDirectory can't be null");
	}

	/**
	 * 
	 * @param name
	 *            文件名
	 * @return 返回一个/xxx/xxx/这样的路径，xxx为三位从hash值中取的十六进制数字
	 */
	private String computeHashDir(String name) {
		if (name == null || name.length() == 0)
			return null;
		int hashcode = name.hashCode();
		int mask = 255;
		int firstDir = hashcode & mask;
		int secondDir = (hashcode >> 8) & mask;
		StringBuilder path = new StringBuilder(File.separator);
		path.append(String.format("%03d", firstDir));
		path.append(File.separator);
		path.append(String.format("%03d", secondDir));
		path.append(File.separator);
		return path.toString();

	}

	private static String getUniqueId() {
		final int limit = 100000000;
		int current;
		synchronized (HashFileStore.class) {
			current = counter++;
		}
		String id = Integer.toString(current);

		// If you manage to get more than 100 million of ids, you'll
		// start getting ids longer than 8 characters.
		if (current < limit) {
			id = ("00000000" + id).substring(id.length());
		}
		return id;
	}

}
