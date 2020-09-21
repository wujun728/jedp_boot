/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: MultipartUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Sets;
import com.qbt.framework.bean.FileResult;
import com.qbt.framework.bean.Multipart;
import com.qbt.framework.bean.Multipart.FileType;
import com.qbt.framework.bean.Multipart.SavePath;
import com.qbt.framework.bean.OfficeDocument;
import com.qbt.framework.constant.Constant;
import com.qbt.framework.emun.FileOperateEmun;
import com.qbt.framework.exception.FileOperateException;

/**<p>Class: MultipartUtils.java</p>
 * <p>Description: 文件上传工具类</p>
 * <pre>
 *      文件上传说明：
 * 	1.上传的所有文件都不做删除操作
 * 	2.删除将以任务调度器的形式去执行
 * 	3.任务调用器形式删除3个月以上或者更久的临时文件架下的文件
 * 	4.文件夹目录说明： img/年/年月日/32.格式文件
 * 	5.这里文件的校验只做了类型和后缀的判断。如果有人恶意在图片或者在文件插入恶意脚本和代码。请在生产环境中设置目录权限。
 *    如果是图片插入恶意脚本，请压缩图片，将恶意代码去掉。这个方式有性能的损耗这里不做考虑
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class MultipartUtils {
	
	protected static Logger logger = LogManager.getLogger(MultipartUtils.class);
	
	//图片类型
	private static Set<String> CONTENT_IMAGE = Sets.newHashSet("image/gif", "image/jpeg", "application/x-jpg", "application/x-png", "image/bmp", "image/png", "application/octet-stream");
	
	//文本类型
	private static Set<String> CONTENT_TXT = Sets.newHashSet("application/octet-stream", "application/pdf", "application/x-ppt", "application/msword");
	
	//视频类型
	private static Set<String> CONTENT_VIDEO = Sets.newHashSet("video/mpeg4", "video/mpg", "video/x-mpeg", "video/mpg");
	
	//压缩文件类型
	private static Set<String> CONTENT_ZIP = null;
	
	//图片后缀
	private static Set<String> SUFFIX_IMAGE = Sets.newHashSet("jpg", "png", "gif", "jpeg", "bmp");
	
	//文本后缀
	private static Set<String> SUFFIX_TXT = Sets.newHashSet("doc");
	
	//视频后缀
	private static Set<String> SUFFIX_VIDEO = null;
	
	//压缩后缀
	private static Set<String> SUFFIX_ZIP = null;
	
	//通用后缀
	@SuppressWarnings("unchecked")
	private static Set<Set<String>> SUFFIX_COMMON = Sets.newHashSet(SUFFIX_IMAGE, SUFFIX_TXT, SUFFIX_VIDEO, SUFFIX_ZIP);
	
	
	/**
	 * 上传图片
	 * @param multipart
	 * @return
	 * @throws FileOperateException
	 */
	public static FileResult uploadImage(Multipart multipart) throws FileOperateException{
		multipart.setFileType(Multipart.FileType.IMAGE);
		if(StringUtils.isBlank(multipart.getSavePath())){
			multipart.setSavePath(Multipart.SavePath.IMG);
		}
		return uploadFile(multipart);
	}
	
	/**
	 * 视频上传，
	 * 说明：视频在上传的时候需要进行视频的解码和转码，转成flv的格式，这样才能在前台页面中显示
	 * java不具备这种能力，需要通过第三方的工具来完成
	 * @param multipart
	 * @return
	 * @throws FileOperateException
	 */
	public static FileResult uploadVideo(Multipart multipart) throws FileOperateException{
		multipart.setFileType(Multipart.FileType.VIDEO);
		if(StringUtils.isBlank(multipart.getSavePath())){
			multipart.setSavePath(Multipart.SavePath.VIDEO);
		}
		return uploadFile(multipart);
	}
	
	/**
	 * 文本类型文件上传
	 * @param multipart
	 * @return
	 * @throws FileOperateException
	 */
	public static FileResult uploadTxt(Multipart multipart) throws FileOperateException{
		multipart.setFileType(Multipart.FileType.TXT);
		if(StringUtils.isBlank(multipart.getSavePath())){
			multipart.setSavePath(Multipart.SavePath.DOC);
		}
		return uploadFile(multipart);
	}
	
	
	
	/**
	 * 压缩类型文件上传
	 * @param multipart
	 * @return
	 * @throws FileOperateException
	 */
	public static FileResult uploadZip(Multipart multipart) throws FileOperateException{
		multipart.setFileType(Multipart.FileType.ZIP);
		if(StringUtils.isBlank(multipart.getSavePath())){
			multipart.setSavePath(Multipart.SavePath.DOC);
		}
		return uploadFile(multipart);
	}
	
	/**
	 * 通用文件类型上传
	 * 说明:不建议使用，安全系数不高，请配合其他的安全校验使用
	 * @param multipart
	 * @return
	 * @throws FileOperateException
	 */
	public static FileResult uploadCommon(Multipart multipart, SavePath savePath) throws FileOperateException{
		multipart.setFileType(Multipart.FileType.COMMON);
		multipart.setSavePath(savePath);
		return uploadFile(multipart);
	}
	
	/**
	 * 本地文件的下载
	 * @param file
	 * @param fileName
	 * @return
	 * @throws FileOperateException
	 */
	public static ResponseEntity<byte[]> downFile(File file, String fileName) throws FileOperateException{
		if(file == null){
			throw new FileOperateException(FileOperateEmun.FILE_NOT_EXITS);
		}
		if(StringUtils.isBlank(fileName)){
			throw new FileOperateException(FileOperateEmun.FILE_NAME_NULL);
		}
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			fileName = new String(clean(fileName).getBytes(), Constant.IOS8859_1);
			headers.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		}  catch (IOException e) {
			logger.error(e);
			throw new FileOperateException("文件下载失败");
		}
	}
	
	
	/**
	 * 提供word文档下载
	 * @param response
	 * @param doc
	 * @param filename
	 * @return
	 */
	public static ResponseEntity<byte[]> downWord(HttpServletResponse  response, OfficeDocument doc, String filename){
		OutputStream out = null;
        try {
        	response.setContentType("application/msword");     
            response.setHeader("Content-disposition", "attachment;filename=" + filename); 
			out = response.getOutputStream();
			doc.write(out);
		} catch (IOException e) {
			logger.error(e);
		}finally{
			IOUtils.closeQuietly(out);
		}
		return null;
	}
	
	/**
	 * 文件下载，支持http方式的文件下载
	 * @param url http://baidu.com/img/123.jpg
	 * @param fileName 文件名  可为空
	 * @return
	 * @throws FileOperateException 
	 */
	public static ResponseEntity<byte[]> downFile(URL url, String fileName) throws FileOperateException{
		if(url == null){
			throw new FileOperateException("文件URL不能为空");
		}
		if(StringUtils.isBlank(fileName)){
			throw new FileOperateException("文件名不能为空");
		}
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			fileName = new String(clean(fileName).getBytes(), Constant.IOS8859_1);
			headers.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(url.openStream()), headers, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e);
			throw new FileOperateException("文件下载失败");
		}
	}
	
	
	public static String saveToDisk(InputStream is, FileType fileType){
		
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("test1.jpg");
			IOUtils.copy(is, fileOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/*********************************private 访法****************************************************/
	
	/**
	 * 清除字符串中的斜杠
	 * 防止文件夹访问漏洞
	 * @param str
	 * @return
	 */
	public static String clean(String str){
		return str.replace("/", "").replace("\\", "");
	}
	
	
	/**
	 * 通用文件上传
	 * @return
	 */
	private static FileResult uploadFile(Multipart multipart) throws FileOperateException{
		if(multipart == null){
			throw new FileOperateException("上传文件不能为空");
		}
		//1.校验文件类型
		if( !validsuffix(validContentType(multipart.getFileType(), multipart.getContentType()), multipart.getFileName())){
			throw new FileOperateException("上传的文件类型不正确");
		}
		//2.创建上传文件夹路径
		String fullPath = multipart.getFullPath();
		if(StringUtils.isBlank(fullPath)){
			throw new FileOperateException("上传目录没有找到，请联系管理人员");
		}
		//3.上传文件
		try {
			
			multipart.getFile().transferTo(new File(fullPath));
			//TODO 打水印	
			return new FileResult(multipart.getUri(), multipart.getFileName(), multipart.getFileType());
		}catch (Exception e) {
			logger.error(e);
			throw new FileOperateException("上传文件失败");
		}
		
	}
	
	/**
	 * 校验文件后缀名
	 * @param fileType
	 * @param fileName
	 * @return
	 * @throws FileOperateException
	 */
	private static boolean validsuffix(FileType fileType, String fileName) throws FileOperateException{
		if(fileType == null){
			throw new FileOperateException("文件类型不匹配");
		}
		String suffixName = FilenameUtils.getExtension(fileName);
		switch (fileType) {
		case IMAGE:
			return validFile(SUFFIX_IMAGE, suffixName);
		case TXT:
			return validFile(SUFFIX_TXT, suffixName);
		case VIDEO:
			return validFile(SUFFIX_VIDEO, suffixName);
		case ZIP:
			return validFile(SUFFIX_ZIP, suffixName);
		case COMMON:
			for(Set<String> type: SUFFIX_COMMON ){
				if( !validFile(type, suffixName) ){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		default:
			return Boolean.FALSE;
		}
	}
	
	/**
	 * 家宴文件类型
	 * @param fileType
	 * @param contentType
	 * @return
	 * @throws FileOperateException
	 */
	private static FileType validContentType(FileType fileType, String contentType) {
		switch (fileType) {
		case IMAGE:
			return validFile(CONTENT_IMAGE, contentType) ? FileType.IMAGE : null;
		case TXT:
			return validFile(CONTENT_TXT, contentType) ? FileType.TXT : null;
		case VIDEO:
			return validFile(CONTENT_VIDEO, contentType) ? FileType.VIDEO : null;
		case ZIP:
			return validFile(CONTENT_ZIP, contentType) ? FileType.ZIP : null;
		case COMMON:
			return FileType.COMMON;			//只校验后缀  不推荐使用
		default:
			return null;
		}
	}
	
	/**
	 * 校验文件类型
	 * @param fileTypes
	 * @param fileType
	 * @return 不通过:false 通过:true
	 */
	private static boolean validFile(Set<String> fileTypes, String fileType){
		return fileTypes.contains(fileType) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	
	
}
