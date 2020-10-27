package com.jyl.file.util;

public class FileManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605596683103983882L;
	
	public FileManagerException(Exception e){
		super.initCause(e);
	}


	

}
