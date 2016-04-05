package com.graymatter.spritemanager.exceptions;

public class ProjectSetupException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3951736252998201855L;
	public ProjectSetupException(String message){
		super(message);
	}
	public ProjectSetupException(String message, Throwable e){
		super(message, e);
	}
}
