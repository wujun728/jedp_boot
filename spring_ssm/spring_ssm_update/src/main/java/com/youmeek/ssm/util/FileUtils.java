package com.youmeek.ssm.util;

/*import com.softtek.roche.wechat.sugar.config.FileDirectory;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * Author: 居泽平 Date: 13-7-5, 下午1:54
 */
public class FileUtils {

	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/*public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName, String oldFilePath) {

		String fileName = fileUpload(uploadFile, configFileDirName, configFileFixName);
		if (fileName != null && !"".equals(fileName)) {
			FileUtils.moveFile(configFileDirName, oldFilePath);
		}
		return fileName;
	}

	public static String fileUpload(MultipartFile uploadFile, String configFileDirName, String configFileFixName) {

		if (uploadFile != null && !uploadFile.isEmpty() && uploadFile.getSize() != 0) {
			// 从配置文件获取图片上传物理根路径
			String fileUploadRootPath = Configuration.getConfigurationByName(FileDirectory.FILE_UPLOAD_ROOT_KEY);

			if (fileUploadRootPath == null) {
				logger.error("获取文件保存根路径异常。");
				return null;
			}

			if (!fileUploadRootPath.endsWith("/")) {
				fileUploadRootPath = fileUploadRootPath + "/";
			}
			if (!configFileDirName.endsWith("/")) {
				configFileDirName = configFileDirName + "/";
			}
			configFileDirName += new SimpleDateFormat("yyyyMMdd").format(new Date());

			// 根路径+上传目录=最终路径
			String companyAbsolutePath = fileUploadRootPath + configFileDirName;
			logger.info("从配置文件获取上传文件路径为[" + companyAbsolutePath + "]");
			logger.info("创建文件夹[" + companyAbsolutePath + "]结果为[" + FileUtils.getFolder(companyAbsolutePath) + "]");
			// 根据上传文件获取文件后缀
			String subFix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
			// 生成文件保存名称为
			String fileName = configFileDirName + "/" + configFileFixName + "_" + FileUtils.getFileNameByDateTime() + subFix;// customer.getUsername()
																																// +
																																// "_"
																																// +
			// 获取文件上传路径
			String filePath = fileUploadRootPath + fileName;
			logger.info("开始上传并保存文件资料至[" + filePath + "]\r\n");

			// 开始上传并保存文件资料
			try {
				uploadFile.transferTo(new File(filePath));
			} catch (IOException ex) {
				logger.error("上传文件出错！错误原因[" + ex.toString() + "]", ex);
				return null;
			}

			return fileName;
		}
		return null;
	}*/

	/**
	 * 移动文件
	 *
	 * @param configFileDirName
	 *            目标文件目录
	 * @param filePath
	 *            现有的文件相对路径
	 * @return 移动结果
	 */
	/*public static boolean moveFile(String configFileDirName, String filePath) {

		if (filePath != null && !"".equals(filePath)) {
			// 从配置文件获取【文件目录绝对根路径】
			String fileUploadRootPath = Configuration.getConfigurationByName(FileDirectory.FILE_UPLOAD_ROOT_KEY);
			if (fileUploadRootPath == null) {
				logger.error("获取文件保存根路径异常。");
				return false;
			}

			fileUploadRootPath = fileUploadRootPath.endsWith("/") ? fileUploadRootPath : fileUploadRootPath + "/";
			configFileDirName = configFileDirName == null ? "unknow" : configFileDirName;
			configFileDirName = configFileDirName.endsWith("/") ? configFileDirName : configFileDirName + "/";
			configFileDirName += new SimpleDateFormat("yyyyMMdd").format(new Date());

			// 根据目标文件目录，取得目标目录的绝对路径
			String newFilePath = fileUploadRootPath + "/backup/" + configFileDirName;
			logger.info("创建文件夹[" + newFilePath + "]结果为[" + FileUtils.getFolder(newFilePath) + "]");

			// 根据传入的相对路径获得文件名
			String fileName = "unknow";
			if (filePath.lastIndexOf("/") > 0) {
				fileName = filePath.substring(filePath.lastIndexOf("/"), filePath.length());
			}

			// 移动文件,并返回移动结果
			return FileUtils.renameOldFile(fileUploadRootPath + filePath, newFilePath + fileName);
		}

		return false;
	}*/

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 *
	 * @param path
	 *            需要创建的文件目录
	 * @return 创建是否成功的文件目录
	 */
	public static boolean getFolder(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				return dir.mkdirs();
			} catch (Exception e) {
				logger.error("生成文件夹报错！", e);
			}
		}
		return true;
	}

	/**
	 * 文件重命名
	 *
	 * @param oldFile
	 *            需要重命名的文件【需完整路径】
	 * @param renamePath
	 *            重命名后的名称【需完整路径】
	 * @return 重命名情况
	 */
	public static boolean renameOldFile(String oldFile, String renamePath) {
		File file = new File(oldFile);
		if (file.exists()) {
			try {
				if (file.renameTo(new File(renamePath))) {
					logger.info("修改原文件名称为[" + renamePath + "]");
					return true;
				} else {
					logger.debug("修改失败，原文件不存在！");
					return false;
				}
			} catch (Exception ex) {
				logger.error("修改文件名称失败!", ex);
				return false;
			}
		} else {
			logger.debug("原文件不存在，无法重命名");
			return false;
		}
	}
	
	public static Iterator<Path> getPathIterator(String path) {
		DirectoryStream<Path> dir;
		try {
			dir = Files.newDirectoryStream(Paths.get(path));
			Iterator<Path> iter = dir.iterator();
			return iter;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 通过日期时间生成一个时间戳
	 *
	 * @return 返回固定格式的时间戳
	 */
	public static String getFileNameByDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + new Random().nextInt(1000);
	}
}
