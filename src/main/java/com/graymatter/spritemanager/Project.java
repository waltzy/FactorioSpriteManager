package com.graymatter.spritemanager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.graymatter.spritemanager.entities.ManagedSprite;
import com.graymatter.spritemanager.entities.ProjectInfo;
import com.graymatter.spritemanager.entities.SpriteManagerManagedEntities;
import com.graymatter.spritemanager.entities.Workspace;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.exceptions.SpriteBuilderException;
import com.graymatter.spritemanager.util.SMFileUtils;

public class Project {

	private ProjectInfo projectInfo;
	private File projectDirectory;
	private File graphicsDirectory;
	private File graphicsDefinitionDirectory;
	private File includeLib;
	private File workingDirectory;
	private File managedEntityFile;
	private File infoFile;
	private SpriteManagerManagedEntities managedEntities;

	public static void main(String[] args) throws ProjectSetupException{
		new Project(new Workspace("C:\\Users\\Waltzy\\AppData\\Roaming\\Factorio\\mods\\BomberTutorial_0.1.1", "C:\\Users\\Waltzy\\testWorkspace"));
	}
	
	public String getRelativetoGraphicsPath(String text) throws ProjectSetupException {
		String out = graphicsDirectory.toURI().relativize(new File(text).toURI()).getPath();
		if (out.startsWith("/")||out.startsWith("\\")) throw new ProjectSetupException("The chosen Directory is outside the Graphics Path");
		return out;
	}
	public String getRelativetoWorkingDirectory(String text) throws ProjectSetupException {
		String out = workingDirectory.toURI().relativize(new File(text).toURI()).getPath();
		if (out.startsWith("/")||out.startsWith("\\")) throw new ProjectSetupException("The chosen Directory is outside the Working Directory");
		return out;
	}
	
	public String getRelativeToMod(String text) throws ProjectSetupException {
		String out = projectDirectory.toURI().relativize(new File(text).toURI()).getPath();
		if (out.startsWith("/")||out.startsWith("\\")) throw new ProjectSetupException("The chosen Directory is outside the Mod Directory");
		return out;
	}

	public void ensureManagedSpriteFoldersWorkingExist() throws ProjectSetupException{
		for (ManagedSprite ms : managedEntities.getSprites()){
			SMFileUtils.createOrLoadDirectory(getWorkingDirectory().getAbsolutePath()+"\\"+ms.getWorkingAssetPath());
		}
	}
	
	private Project(Workspace workspace) throws ProjectSetupException {

		setProjectDirectory(new File(workspace.getModBaseDirectory()));
		setWorkingDirectory(new File(workspace.getRenderBaseDirectory()));

		if (!this.projectDirectory.exists())
			throw new ProjectSetupException(
					"Project base Directory (" + workspace.getModBaseDirectory() + ")Does Not Exist");
		
		infoFile = new File(projectDirectory.getAbsolutePath() + "/info.json");
		managedEntityFile = new File(projectDirectory.getAbsolutePath() + "/managedSprites.json");
		
		SMFileUtils.ensureFile(infoFile, new ProjectInfo());
		SMFileUtils.ensureFile(managedEntityFile, new SpriteManagerManagedEntities());		
		
		
		
		try {
			setProjectInfo(new Gson().fromJson(SMFileUtils.readFile(projectDirectory.getAbsolutePath() + "/info.json"),
					ProjectInfo.class));
			setManagedEntities(
					new Gson().fromJson(SMFileUtils.readFile(projectDirectory.getAbsolutePath() + "/managedSprites.json"),
							SpriteManagerManagedEntities.class));

		} catch (JsonSyntaxException | IOException e) {
			throw new ProjectSetupException(
					"Could not bind to project " + workspace.getModBaseDirectory() + " reason: " + e.getMessage());
		}

		setGraphicsDirectory(SMFileUtils.createOrLoadDirectory(projectDirectory.getAbsolutePath() + "/Graphics"));
		setGraphicsDefinitionLib(SMFileUtils.createOrLoadDirectory(projectDirectory.getAbsolutePath() + "/prototypes/graphicslib"));
		setIncludeLib(SMFileUtils.createOrLoadFile(projectDirectory.getAbsolutePath() + "/data.lua"));
		

	}

	public void saveManagedSprites() throws ProjectSetupException{
		SMFileUtils.writeJson(managedEntityFile, managedEntities);
		ensureManagedSpriteFoldersWorkingExist();
	}

	public static Project getProject(Workspace workspace) throws ProjectSetupException {
		return new Project(workspace);
	}


	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public File getGraphicsDirectory() {
		return graphicsDirectory;
	}

	public void setGraphicsDirectory(File graphicsDirectory) {
		this.graphicsDirectory = graphicsDirectory;
	}

	public File getGraphicsDefinitionLib() {
		return graphicsDefinitionDirectory;
	}

	public void setGraphicsDefinitionLib(File graphicsDefinitionLib) {
		this.graphicsDefinitionDirectory = graphicsDefinitionLib;
	}

	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public SpriteManagerManagedEntities getManagedEntities() {
		return managedEntities;
	}

	public void setManagedEntities(SpriteManagerManagedEntities managedEntities) {
		this.managedEntities = managedEntities;
	}

	public File getProjectDirectory() {
		return projectDirectory;
	}

	public void setProjectDirectory(File projectDirectory) {
		this.projectDirectory = projectDirectory;
	}

	public File getIncludeLib() {
		return includeLib;
	}

	public void setIncludeLib(File includeLib) {
		this.includeLib = includeLib;
	}

	public void saveToMod(List<CombinedSprite> stripes, String luaLib, ManagedSprite selectedSprite) throws ProjectSetupException {
		
		File modAssetFile  = SMFileUtils.createOrLoadDirectory(selectedSprite.getFullyQualifiedModGraphicsAssetPath(this));
		File getLuaAsseDirectory = getGraphicsDefinitionLib();
		
		System.out.println("Saving Stripes to "+modAssetFile.getName());
		
		for (CombinedSprite combinedSprite : stripes) {
			try {
				combinedSprite.SaveBufferedImage(modAssetFile.getAbsolutePath(), combinedSprite.getFile().getName());
			} catch (Exception e) {
				throw new ProjectSetupException("problem saving stripe to mod",e);
			}
		}
		
		System.out.println("Saving lua Lib");
		
		
		File luaLibFile = new File(getLuaAsseDirectory.getAbsolutePath()+"/"+selectedSprite.getManagedSpriteName()+".lua"); 
		SMFileUtils.writeFile(luaLibFile, luaLib);
		
		
		String luaIncludeLib;
		try {
			luaIncludeLib = SMFileUtils.readFile(getIncludeLib().getAbsolutePath());
		} catch (IOException e) {
			throw new ProjectSetupException("problem loading lualib",e);
		}
		
		String lookFor = "require(\""+getRelativeToMod(luaLibFile.getAbsolutePath()).replace("/", ".").replace("\\", ".").replace(".lua", "")+"\")";
		
		
		System.out.println("checking lua lib file "+getIncludeLib().getName());
		
		if (luaIncludeLib.indexOf(lookFor)==-1){
			System.out.println("Include not present, adding...");
			luaIncludeLib = lookFor+"\r\n"+luaIncludeLib;
			SMFileUtils.writeFile(getIncludeLib(), luaIncludeLib);
		} else {
			System.out.println(" data.lua already contains requires.");
		}
		
	}





}
