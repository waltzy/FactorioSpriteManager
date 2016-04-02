package com.graymatter.spritemanager.entities;

public class Workspace {

	private String modBaseDirectory;
	private String RenderBaseDirectory;
	public String getRenderBaseDirectory() {
		return RenderBaseDirectory;
	}
	public void setRenderBaseDirectory(String renderBaseDirectory) {
		RenderBaseDirectory = renderBaseDirectory;
	}
	public String getModBaseDirectory() {
		return modBaseDirectory;
	}
	public void setModBaseDirectory(String modBaseDirectory) {
		this.modBaseDirectory = modBaseDirectory;
	}
}
