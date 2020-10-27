package com.pinhuba.ueditor.action;

import static com.pinhuba.ueditor.Constants.CONTEXT_PATH;
import static com.pinhuba.ueditor.Constants.UPLOAD_PATH;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pinhuba.ueditor.config.LocalizedMessages;
import com.pinhuba.ueditor.config.ResourceType;
import com.pinhuba.ueditor.config.Utils;
import com.pinhuba.ueditor.resolver.RealPathResolver;
import com.pinhuba.ueditor.service.ImageService;
import com.pinhuba.ueditor.util.ResponseUtils;
import com.pinhuba.ueditor.util.img.ImageUtils;
import com.pinhuba.ueditor.util.upload.FileRepository;

/**
 * ueditor服务器端接口
 * 
 * 为了更好、更灵活的处理ueditor上传，重新实现ueditor服务器端接口。
 */
@Controller
public class UeditorAct {

	private static final Logger log = LoggerFactory.getLogger(UeditorAct.class);

	// 状态
	private static final String STATE = "state";
	// 上传成功
	private static final String SUCCESS = "SUCCESS";
	// URL
	private static final String URL = "url";
	// 原URL
	private static final String SRC_URL = "srcUrl";
	// 文件原名
	private static final String ORIGINAL = "original";
	// 文件类型
	private static final String FILETYPE = "fileType";
	// 在线图片管理图片分隔符
	private static final String UE_SEPARATE_UE = "ue_separate_ue";
	// 提示信息
	private static final String TIP = "tip";

	@RequestMapping(value = "/ueditor/upload.do", method = RequestMethod.POST)
	public void upload(@RequestParam(value = "Type", required = false) String typeStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		if (Utils.isEmpty(typeStr)) {
			typeStr = "File";
		}
		JSONObject json = new JSONObject();
		JSONObject ob = validateUpload(request, typeStr);
		if (ob == null) {
			json = doUpload(request, typeStr);
		} else {
			json = ob;
		}
		ResponseUtils.renderJson(response, json.toString());
	}

	/**
	 * 验证是否能够上传
	 * 
	 * @param request
	 * @param typeStr
	 * @return
	 * @throws JSONException
	 */
	private JSONObject validateUpload(HttpServletRequest request, String typeStr) throws JSONException {
		JSONObject result = new JSONObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile uplFile = multipartRequest.getFileMap().entrySet().iterator().next().getValue();
		String filename = FilenameUtils.getName(uplFile.getOriginalFilename());
		int fileSize = (int) (uplFile.getSize() / 1024);//单位KB
		String ext = FilenameUtils.getExtension(filename).toLowerCase(Locale.ENGLISH);
		
		// 此处可根据系统业务定义的配置项再次进行验证

		// 非允许的后缀，建议做成配置项，如用户组配置，进一步对上传文件的格式进行限制
		if (false) {
			result.put(STATE, LocalizedMessages.getInvalidFileSuffixSpecified(request));
			return result;
		}

		// 超过大小限制，建议做成配置项，如用户组配置
		int max = 1024;// 单位KB
		if (fileSize > max) {
			result.put(STATE, LocalizedMessages.getInvalidFileToLargeSpecified(request, filename, max));
			return result;
		}

		// 超过每日上传限制，建议在系统中做成配置项，如用户组配置
		if (false) {
			int tmp = 100;// 单位KB
			result.put(STATE, LocalizedMessages.getInvalidUploadDailyLimitSpecified(request, String.valueOf(tmp)));
			return result;
		}

		if (!ResourceType.isValidType(typeStr)) {
			result.put(STATE, LocalizedMessages.getInvalidResouceTypeSpecified(request));
			return result;
		}
		return null;
	}

	/**
	 * 开始上传
	 * 
	 * @param request
	 * @param typeStr
	 * @return
	 * @throws Exception
	 */
	private JSONObject doUpload(HttpServletRequest request, String typeStr) throws Exception {
		ResourceType type = ResourceType.getDefaultResourceType(typeStr);
		JSONObject result = new JSONObject();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 同时只上传一个文件
			MultipartFile uplFile = multipartRequest.getFileMap().entrySet().iterator().next().getValue();
			String filename = FilenameUtils.getName(uplFile.getOriginalFilename());
			log.debug("Parameter NewFile: {}", filename);
			String ext = FilenameUtils.getExtension(filename);
			if (type.isDeniedExtension(ext)) {
				result.put(STATE, LocalizedMessages.getInvalidFileTypeSpecified(request));
				return result;
			}
			// 如果是图片上传请求，不仅仅是要通过后缀判断，还要通过输入流判断是否为图片
			if (type.equals(ResourceType.IMAGE) && !ImageUtils.isImage(uplFile.getInputStream())) {
				result.put(STATE, LocalizedMessages.getInvalidFileTypeSpecified(request));
				return result;
			}

			String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, ext, uplFile);

			// 加上部署路径
			fileUrl = CONTEXT_PATH + fileUrl;
			// 需要给页面参数(参考ueditor的/jsp/imageUp.jsp)
			result.put(STATE, SUCCESS);
			result.put(URL, fileUrl);
			result.put(ORIGINAL, filename);
			result.put(FILETYPE, "." + ext);
			return result;
		} catch (IOException e) {
			result.put(STATE, LocalizedMessages.getFileUploadWriteError(request));
			return result;
		}
	}

	@RequestMapping(value = "/ueditor/getRemoteImage.do")
	public void getRemoteImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getParameter("upfile");
		JSONObject json = new JSONObject();
		String[] arr = url.split(UE_SEPARATE_UE);
		String[] outSrc = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			outSrc[i] = imgSvc.crawlImg(arr[i]);
		}
		String outstr = "";
		for (int i = 0; i < outSrc.length; i++) {
			outstr += outSrc[i] + UE_SEPARATE_UE;
		}
		outstr = outstr.substring(0, outstr.lastIndexOf(UE_SEPARATE_UE));
		json.put(URL, outstr);
		json.put(SRC_URL, url);
		json.put(TIP, LocalizedMessages.getRemoteImageSuccessSpecified(request));
		ResponseUtils.renderJson(response, json.toString());
	}

	/**
	 * 在线图片管理（选择最近或站点图片）
	 * 
	 * @param picNum
	 *            图片显示数量（数量不宜太大）
	 * @param insite
	 *            站点内所有图片（默认选择最近月内图片）
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/ueditor/imageManager.do")
	public void imageManager(Integer picNum, Boolean insite, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (insite == null) {
			insite = false;
		}
		String path;
		if (insite) {
			path = UPLOAD_PATH;
		} else {
			path = UPLOAD_PATH + "/" + getSubFolderNameDirectory(realPathResolver.get(UPLOAD_PATH));
		}
		String realpath = realPathResolver.get(path);
		String imgStr = "";
		StringBuffer imgStrBuff = new StringBuffer();
		List<File> files = getFiles(realpath, new ArrayList<File>());
		if (picNum == null) {
			picNum = 10;
		}
		if (picNum > files.size()) {
			picNum = files.size();
		}
		for (int i = 0; i < picNum; i++) {
			File file = files.get(i);
			imgStrBuff.append(file.getPath().replace(realPathResolver.get(""), request.getContextPath()) + UE_SEPARATE_UE);
		}
		imgStr = imgStrBuff.toString();
		if (StringUtils.isNotBlank(imgStr)) {
			imgStr = imgStr.substring(0, imgStr.lastIndexOf(UE_SEPARATE_UE)).replace(File.separator, "/").trim();
		}
		response.getWriter().print(imgStr);
	}

	/**
	 * getmovie方法完全参考ueditor提供
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/ueditor/getmovie.do", method = RequestMethod.POST)
	public void getMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		String searchkey = request.getParameter("searchKey");
		String videotype = request.getParameter("videoType");
		try {
			searchkey = URLEncoder.encode(searchkey, "utf-8");
			URL url = new URL("http://api.tudou.com/v3/gw?method=item.search&appKey=myKey&format=json&kw=" + searchkey + "&pageNo=1&pageSize=20&channelId=" + videotype
					+ "&inDays=7&media=v&sort=s");
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			content = readOneLineBuff.toString();
			reader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		ResponseUtils.renderText(response, content);
	}

	/**
	 * 获取目录下所用子目录中的一个子目录名称
	 * 
	 * @param directory
	 * @return
	 */
	private String getSubFolderNameDirectory(String directory) {
		File realFile = new File(directory);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			if (subfiles != null && subfiles.length > 0) {
				return subfiles[subfiles.length - 1].getName();
			} else {
				return directory;
			}
		} else {
			return directory;
		}
	}

	/**
	 * 获得目录下所有子目录的图片
	 * 
	 * @param realpath
	 * @param files
	 * @return
	 */
	private List<File> getFiles(String realpath, List<File> files) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				} else {
					if (ImageUtils.isValidImageExt(FilenameUtils.getExtension(file.getName()))) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private ImageService imgSvc;
}
