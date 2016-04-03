package com.graymatter.spritemanager.entities;

public class Workspace {

	private String modBaseDirectory;
	private String assetBaseDirectory;
	public Workspace(String modDir, String assetDir) {
		this.modBaseDirectory = modDir;
		this.assetBaseDirectory = assetDir;
		System.out.println("Setting MOD BASE DIRECTORY TO "+modDir);
		System.out.println("Setting WORKING BASE DIRECTORY TO "+assetDir);
		
	}
	public String getRenderBaseDirectory() {
		return assetBaseDirectory;
	}
	public void setRenderBaseDirectory(String renderBaseDirectory) {
		assetBaseDirectory = renderBaseDirectory;
	}
	public String getModBaseDirectory() {
		return modBaseDirectory;
	}
	public void setModBaseDirectory(String modBaseDirectory) {
		this.modBaseDirectory = modBaseDirectory;
	}
	@Override
	public String toString(){
		return modBaseDirectory;
	}
}
