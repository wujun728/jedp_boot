package com.pinhuba.ueditor.config;

import javax.servlet.http.HttpServletRequest;

import com.pinhuba.ueditor.resolver.MessageResolver;

public class LocalizedMessages {

	private static String getMessage(HttpServletRequest request, String key, Object... args) {
		return MessageResolver.getMessage(request, key, args);
	}

	public static String getInvalidFileTypeSpecified(HttpServletRequest request) {
		return getMessage(request, "invalid_file_type_specified");
	}

	public static String getFileUploadWriteError(HttpServletRequest request) {
		return getMessage(request, "write_error");
	}
	
	public static String getInvalidResouceTypeSpecified(HttpServletRequest request) {
		return getMessage(request, "invalid_resource_type_specified"); //$NON-NLS-1$
	}

	public static String getInvalidFileSuffixSpecified(HttpServletRequest request) {
		return getMessage(request, "invalid_file_suffix_specified"); //$NON-NLS-1$
	}
	
	public static String getInvalidFileToLargeSpecified(HttpServletRequest request, String filename, Integer max) {
		return getMessage(request, "invalid_file_toolarge_specified", filename, max);
	}

	public static String getInvalidUploadDailyLimitSpecified(HttpServletRequest request, String lavesize) {
		return getMessage(request, "invalid_file_dailylimit_specified", lavesize);
	}

	public static String getInvalidUploadMultipartSpecified(HttpServletRequest request) {
		return getMessage(request, "invalid_multipart_specified");
	}

	public static String getInvalidUploadInputStreamSpecified(HttpServletRequest request) {
		return getMessage(request, "invalid_inputstream_specified");
	}

	public static String getRemoteImageSuccessSpecified(HttpServletRequest request) {
		return getMessage(request, "getremoteimage_success");
	}

}
