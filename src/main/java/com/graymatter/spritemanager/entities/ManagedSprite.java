package com.graymatter.spritemanager.entities;

import java.io.File;

import com.graymatter.spritemanager.Project;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;

public class ManagedSprite {

	private String managedSpriteName;
	private SpriteType type = SpriteType.VEHICLE;
	private String modAssetPath;
	private String workingAssetPath;
	private String luaLibPath;
	private String itemName;
	public String getManagedSpriteName() {
		return managedSpriteName;
	}
	
	public String getFullyQualifiedModGraphicsAssetPath(Project p){
		return (p.getProjectDirectory().getAbsolutePath()+"\\"+modAssetPath).replace("/", "\\");
	}
	
	public File getFullyQualifiedModGraphicsAssetFile(Project p) throws ProjectSetupException{
		File file = new File(getFullyQualifiedModGraphicsAssetPath(p));
		if (!file.exists()) throw new ProjectSetupException("File does not exist");
		return file;
	}
	public String getFullyQualifiedWorkingPath(Project p){
		return (p.getWorkingDirectory().getAbsolutePath()+"\\"+workingAssetPath).replace("/", "\\");
	}
	
	public File getFullyQualifiedWorkingFile(Project p) throws ProjectSetupException{
		File file = new File(getFullyQualifiedWorkingPath(p));
		if (!file.exists()) throw new ProjectSetupException("File does not exist");
		return file;
	}
	@Override
	public String toString(){
		return managedSpriteName;
	}
	
	public void setManagedSpriteName(String managedSpriteName) {
		this.managedSpriteName = managedSpriteName;
	}
	public SpriteType getType() {
		return type;
	}
	public void setType(SpriteType type) {
		this.type = type;
	}
	public String getModAssetPath() {
		return modAssetPath;
	}
	public void setModAssetPath(String modAssetPath) {
		this.modAssetPath = modAssetPath;
	}
	public String getWorkingAssetPath() {
		return workingAssetPath;
	}
	public void setWorkingAssetPath(String workingAssetPath) {
		this.workingAssetPath = workingAssetPath;
	}
	public String getLuaLibPath() {
		return luaLibPath;
	}
	public void setLuaLibPath(String luaLibPath) {
		this.luaLibPath = luaLibPath;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getFilePattern() {
		return filePattern;
	}
	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}
	private String filePattern;
	
	 
}
