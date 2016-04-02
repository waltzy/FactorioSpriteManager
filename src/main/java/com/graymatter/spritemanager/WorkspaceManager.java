package com.graymatter.spritemanager;

import java.io.File;
import java.util.List;

import com.graymatter.spritemanager.entities.Workspace;
import com.graymatter.spritemanager.entities.Workspaces;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.util.SMFileUtils;

public class WorkspaceManager {

	private static WorkspaceManager _instance;
	private Workspaces workspaces;
	private File workspaceFile; 
	private WorkspaceManager() throws ProjectSetupException{
		workspaceFile = new File("./workspaces");
		SMFileUtils.ensureJson(workspaceFile, new Workspaces());
		setWorkspaces((Workspaces) SMFileUtils.getJson(workspaceFile.getAbsolutePath(), Workspaces.class));
	}
	
	public void addWorkspace(Workspace workspace){
		workspaces.getWorkspaces().add(workspace);
		save();
	}
	
	private void save() {
		try {
			SMFileUtils.ensureJson(workspaceFile, workspaces);
		} catch (ProjectSetupException e) {
			e.printStackTrace();
		}
	}

	public List<Workspace> getWorkspaceList(){
		return workspaces.getWorkspaces();
	}
	
	public static WorkspaceManager getInstance() throws ProjectSetupException{
		if (_instance==null) _instance = new WorkspaceManager();
		return _instance;
	}

	public Workspaces getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(Workspaces workspaces) {
		this.workspaces = workspaces;
	}
	
	
}
