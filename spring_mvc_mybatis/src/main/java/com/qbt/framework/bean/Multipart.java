/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.interceptor</p>
 * <p>File: FileResult.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qbt.framework.util.CalendarUtil;
import com.qbt.framework.util.RandomUtil;
import com.qbt.framework.util.WebPathUtils;

/**<p>Class: ExceptionInterceptor.java</p>
 * <p>Description: 文件上传下载辅助类</p>
 * <pre>
 *       SavePath-TEMP:说明：提供图片预览功能的临时目录，如果只支持HTML5的话，请无视该目录，HTML5原声就支持的
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class Multipart {
	
	//文件对象
	private MultipartFile file;
	
	
	//保存的路径
	private String savePath;
	
	//是否打水印
	private Boolean water = false;
	
	///upload/img/2014/20140515/32.jpg  相对路径
	private String uri;
	
	//文件的类型
	private FileType fileType;
	
	private HttpServletRequest request;
	
	private static final String FILE_SEPARATOR = File.separator;
	
	/*********************************文件上传路径配置，可在此处修改****************************************/
	private static String IMG_PATH = FILE_SEPARATOR + "upload" + FILE_SEPARATOR + "img";
	
	private static String DOC_PATH = FILE_SEPARATOR + "upload" + FILE_SEPARATOR + "doc";
	
	private static String VIDEO_PATH = FILE_SEPARATOR + "upload" + FILE_SEPARATOR + "video";
	
	private static String TEMP_PATH = FILE_SEPARATOR + "upload" + FILE_SEPARATOR + "temp";		//只使用HTML5的可忽略此目录
	/***********************************文件上传路径配置，可在此处修改**************************************/
	
	/**
	 * 
	 * @param file
	 * @param request
	 * @param savePath
	 * @param fileType
	 * @param water
	 */
	public Multipart(MultipartFile file, HttpServletRequest request,  Boolean water) {
		super();
		if(file == null || file.isEmpty()){
			throw new IllegalArgumentException("文件不能为空");
		}
		this.file = file;
		this.water = water;
		this.request = request;
	}
	
	
	/**
	 * 默认水印不打
	 * @param file
	 * @param request
	 * @param savePath
	 * @param fileType
	 */
	public Multipart(MultipartFile file, HttpServletRequest request) {
		this(file, request, false);
	}
	
	public Multipart(MultipartFile file, HttpServletRequest request,  SavePath savePath) {
		this(file, request, false);
		this.setSavePath(savePath);
	}
	
	public Multipart(MultipartFile file,  HttpServletRequest request, SavePath savePath, Boolean water) {
		this(file, request, water);
		this.setSavePath(savePath);
	}

	/**
	 * <p>名称：FileType.java</p>
	 * <p>描述：文件类型枚举</p>
	 * <pre>
	 *    
	 * </pre>
	 * @author 鲍建明
	 * @date 2014年11月26日 下午4:05:50
	 * @version 1.0.0
	 */
	public static enum FileType{
		IMAGE, TXT, VIDEO, FLASH, ZIP, COMMON
	}
	
	/**
	 * <p>名称：SavePath.java</p>
	 * <p>描述：路径枚举</p>
	 * <pre>
	 *    添加路径时，请在此次对应添加枚举
	 * </pre>
	 * @author 鲍建明
	 * @date 2014年11月26日 下午4:06:05
	 * @version 1.0.0
	 */
	public static enum SavePath{
		IMG, DOC, VIDEO,TEMP
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public String getSavePath() {
		return savePath;
	}

	/**
	 * 设置保存路径
	 * @param savePath
	 */
	public void setSavePath(SavePath savePath) {
		switch (savePath) {
		case IMG:
			this.savePath = IMG_PATH;
			break;
		case DOC:
			this.savePath = DOC_PATH;
			break;
		case VIDEO:
			this.savePath = VIDEO_PATH;
			break;
		case TEMP:
			this.savePath = TEMP_PATH;
			break;
		}
		
	}

	public Boolean getWater() {
		return water;
	}

	public void setWater(Boolean water) {
		this.water = water;
	}


	public FileType getFileType() {
		return fileType;
	}


	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}


	/**
	 * 获取文件上传半全路径
	 * @return /upload/img/2014/20140515/32.jpg
	 */
	public String getUploadFilePath() {
		StringBuffer sb = new StringBuffer(this.getSavePath());
		sb.append(FILE_SEPARATOR + CalendarUtil.toString(CalendarUtil.FMT_Y));
		sb.append(FILE_SEPARATOR + CalendarUtil.toString(CalendarUtil.FMT_YMD));
		sb.append(FILE_SEPARATOR + this.getFileNewName());
		setUri(sb.toString());
		return getUri();
	}



	/**
	 * 获取原始的文件名
	 * @return
	 */
	public String getFileName() {
		return file.getOriginalFilename();
	}


	/**
	 * 获取32位UUID文件名
	 * @return
	 */
	public String getFileNewName() {
		return RandomUtil.buildUUID() + "." + FilenameUtils.getExtension(this.getFileName());
	}
	
	/**
	 * 
	 * <p>描述：获取上传后文件的相对路径</p>
	 * <pre>
	 *    
	 * </pre>
	 * @return
	 */
	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	/**
	 * 文件类型
	 * @return
	 */
	public String getContentType() {
		return file.getContentType();
	}

	

	/**
	 * 获取项目前缀
	 * @return
	 */
	public String getSuffixPath() {
		return WebPathUtils.getSuffixPath(request);
	}

	/**
	 * 获取文件全路劲
	 * @return
	 */
	public String getFullPath() {
		String path = this.getSuffixPath() + this.getUploadFilePath();
		return mkdir(path) ? path : null;
	}

	/**
	 * 
	 * <p>描述：创建文件夹路径，如果存在不创建</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param path
	 * @return
	 */
	public  boolean mkdir(String path){
		File file = new File(path);
		if( !file.exists()){
			return file.mkdirs();
		}
		return true;
	}
	
}
