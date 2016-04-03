package com.graymatter.spritemanager;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.graymatter.spritemanager.entities.ProjectInfo;
import com.graymatter.spritemanager.entities.SpriteManagerManagedEntities;
import com.graymatter.spritemanager.entities.Workspace;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.util.SMFileUtils;

public class Project {

	private static Project _instance;
	private ProjectInfo projectInfo;
	private File projectDirectory;
	private File graphicsDirectory;
	private File graphicsDefinitionDirectory;
	private File includeLib;
	private File workingDirectory;
	private SpriteManagerManagedEntities managedEntities;

	public static void main(String[] args) throws ProjectSetupException{
		new Project(new Workspace("C:\\Users\\Waltzy\\AppData\\Roaming\\Factorio\\mods\\BomberTutorial_0.1.1", "C:\\Users\\Waltzy\\testWorkspace"));
	}
	
	private Project(Workspace workspace) throws ProjectSetupException {

		setProjectDirectory(new File(workspace.getModBaseDirectory()));
		setWorkingDirectory(new File(workspace.getRenderBaseDirectory()));

		if (!this.projectDirectory.exists())
			throw new ProjectSetupException(
					"Project base Directory (" + workspace.getModBaseDirectory() + ")Does Not Exist");
		
		File info = new File(projectDirectory.getAbsolutePath() + "/info.json");
		File managedSprites = new File(projectDirectory.getAbsolutePath() + "/managedSprites.json");
		
		SMFileUtils.ensureJson(info, new ProjectInfo());
		SMFileUtils.ensureJson(managedSprites, new SpriteManagerManagedEntities());		
		
		
		
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
		setGraphicsDefinitionLib(SMFileUtils.createOrLoadDirectory(projectDirectory.getAbsolutePath() + "/GraphicsLib"));
		setIncludeLib(SMFileUtils.createOrLoadFile(projectDirectory.getAbsolutePath() + "/data.lua"));
		

	}

	

	public static void init(Workspace workspace) throws ProjectSetupException {
		_instance = new Project(workspace);
	}

	public static Project getInstance() throws ProjectSetupException {
		if (_instance == null)
			throw new ProjectSetupException("Project must be initilised first with Project.init(\"/ModPath\")");
		return _instance;
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
}
