package com.graymatter.spritemanager.exceptions;

public class SpriteBuilderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9046416461600699323L;

	public SpriteBuilderException(String string) {
		super(string);
	}

	public SpriteBuilderException(String string, ProjectSetupException e) {
		super(string, e);
	}

}
