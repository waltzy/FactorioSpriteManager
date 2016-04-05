package com.graymatter.spritemanager.exceptions;

public class ProjectSetupException extends Exception {
	public ProjectSetupException(String message){
		super(message);
	}
	public ProjectSetupException(String message, Throwable e){
		super(message, e);
	}
}
