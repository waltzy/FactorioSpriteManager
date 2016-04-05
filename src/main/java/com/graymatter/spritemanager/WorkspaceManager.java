package com.graymatter.spritemanager;

import java.io.File;

import com.graymatter.spritemanager.entities.Workspace;
import com.graymatter.spritemanager.entities.Workspaces;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.util.SMFileUtils;

public class WorkspaceManager {

	private static WorkspaceManager _instance;
	private Workspaces workspaces;
	private File workspaceFile; 
	private WorkspaceManager() throws ProjectSetupException{
		workspaceFile = new File("./workspaces.json");
		SMFileUtils.ensureJson(workspaceFile, new Workspaces());
		reload();
	}
	
	public void reload() throws ProjectSetupException{
		setWorkspaces((Workspaces) SMFileUtils.getJson(workspaceFile.getAbsolutePath(), Workspaces.class));
	}
	
	public void addWorkspace(Workspace workspace){
		workspaces.addWorkSpace(workspace);
		save();
	}
	
	private void save() {
		try {
			System.out.println("Listing Workspaces to save...");
			for (Workspace ws : workspaces.getWorkspaces()) {
				System.out.println(ws.getModBaseDirectory());
			}
			SMFileUtils.writeJson(workspaceFile, workspaces);
			
		} catch (ProjectSetupException e) {
			e.printStackTrace();
		}
	}

	public Project getProject(Workspace workspace) throws ProjectSetupException{
		return Project.getProject(workspace);
	}
	
	public Workspace[] getWorkspaceList(){
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
