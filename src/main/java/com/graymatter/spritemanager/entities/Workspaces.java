package com.graymatter.spritemanager.entities;

import org.apache.commons.lang3.ArrayUtils;

public class Workspaces {

	public Workspace[] workspaces = new Workspace[0];

	public Workspace[] getWorkspaces() {
		return workspaces;
	}

	public void addWorkSpace(Workspace ws){
		workspaces = (Workspace[]) ArrayUtils.add(workspaces, ws);
	}
	
	public void setWorkspaces(Workspace[] workspaces) {
		this.workspaces = workspaces;
	}
	
}
