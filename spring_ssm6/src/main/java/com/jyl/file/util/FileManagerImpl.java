package com.jyl.file.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.jyl.file.model.FileInfo;
import com.jyl.file.util.FileStore.FInfo;

public class FileManagerImpl implements FileManager,InitializingBean {
	
	private FileStore store;
	
	private long maxSize = 1024*1024;
	
	private String workDirectory = System.getProperty("java.io.tmpdir");
	
	private Logger log = Logger.getLogger(FileManagerImpl.class);
	
	public List<FileInfo> uploadFile(HttpServletRequest request) throws FileManagerException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(this.workDirectory));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(this.maxSize);			
		List<FileInfo> result = null;
			try {
				FileItemIterator iter = upload.getItemIterator(request);
				String fileName = null;
				while (iter.hasNext()) {
				    FileItemStream item = iter.next();
					if(!item.isFormField()){
						String name = item.getName();
						if(name.lastIndexOf("\\")>-1){
							fileName=name.substring(name.lastIndexOf("\\")+1);
						}else{
							fileName=name;
						}
						if(fileName != null){
							InputStream uploadis=item.openStream();
							FInfo f = store.createFile(fileName,uploadis);
							FileInfo finfo = new FileInfo();
							finfo.setName(fileName);
							finfo.setPath(f.getPath());
							finfo.setContenttype(item.getContentType());
							finfo.setLength(f.getSize());
							finfo.setCreated(new Date());
							if (result == null)
								result = new ArrayList<FileInfo>();
							result.add(finfo);
							uploadis.close();
						}
					}
				}				
			} catch (FileUploadException e) {
				log.error("FileUploadException", e);
				FileManagerException ex = new FileManagerException(e);
				throw ex;
			} catch (Exception e) {
				log.error("Exception", e);
				e.printStackTrace();
			}
			
		return result;
	}

	public InputStream readFileStream(String path) {
		return this.store.readFileStream(path);
	}

	public boolean deletefile(String path) {
		return this.store.deleteFile(path);
	}

	public FileStore getStore() {
		return this.store;
	}

	public void setStore(FileStore store) {
		this.store = store;
	}
	
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.store,"store can't be null");
		
	}

	public boolean isMultipartContent(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public String getWorkDirectory() {
		return workDirectory;
	}

	public void setWorkDirectory(String workDirectory) {
		this.workDirectory = workDirectory;
	}
}
