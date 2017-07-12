package org.khmeracademy.auction.entities;

import java.util.List;

public class UploadedFileInfo {
	
	private List<String> names;
	private String projectPath;
	private String serverPath;
	private String message;
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public String getProjectPath() {
		return projectPath;
	}
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	@Override
	public String toString() {
		return "File [names=" + names + ", projectPath=" + projectPath + ", serverPath=" + serverPath + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
