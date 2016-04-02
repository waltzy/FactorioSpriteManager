package com.graymatter.spritemanager;

import java.io.File;

import com.google.gson.Gson;
import com.graymatter.spritemanager.entities.Workspaces;
import com.graymatter.spritemanager.exceptions.ProjectSetupException;
import com.graymatter.spritemanager.util.SMFileUtils;

public class WorkspaceManager {

	private static WorkspaceManager _instance;
	private Workspaces workspaces;
	
	private WorkspaceManager() throws ProjectSetupException{
		File wsfile = new File("./workspaces");
		SMFileUtils.ensureJson(wsfile, new Workspaces());
		//SMFileUtils.getJson(file, clazz)
	}
	
	public static WorkspaceManager getInstance() throws ProjectSetupException{
		if (_instance==null) _instance = new WorkspaceManager();
		return _instance;
	}
	
	
}
