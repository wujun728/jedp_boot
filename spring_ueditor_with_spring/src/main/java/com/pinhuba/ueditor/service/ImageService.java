package com.pinhuba.ueditor.service;

import static com.pinhuba.ueditor.Constants.CONTEXT_PATH;
import static com.pinhuba.ueditor.Constants.UPLOAD_PATH;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinhuba.ueditor.resolver.RealPathResolver;
import com.pinhuba.ueditor.util.FileNameUtils;
import com.pinhuba.ueditor.util.upload.FileRepository;
import com.pinhuba.ueditor.util.upload.UploadUtils;

@Service
public class ImageService {
	public String crawlImg(String imgUrl) {
		HttpClient client = new DefaultHttpClient();
		String outFileName = "";
		String fileUrl = "";
		try {
			HttpGet httpget = new HttpGet(new URI(imgUrl));
			HttpResponse response = client.execute(httpget);
			InputStream is = null;
			OutputStream os = null;
			HttpEntity entity = null;
			entity = response.getEntity();
			is = entity.getContent();
			String ext = FileNameUtils.getFileSufix(imgUrl);
			outFileName = UploadUtils.generateFilename(UPLOAD_PATH, ext);
			File outFile = new File(realPathResolver.get(outFileName));
			UploadUtils.checkDirAndCreate(outFile.getParentFile());
			os = new FileOutputStream(outFile);
			IOUtils.copy(is, os);
			fileUrl = CONTEXT_PATH + outFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	@Autowired
	protected FileRepository fileRepository;
	@Autowired
	private RealPathResolver realPathResolver;
}
