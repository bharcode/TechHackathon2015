package com.aexp.hackathon.pojo;

public class APIDetails {

	String jarname;
	
	String api;
	
	String download;
	
	String status;
	
	public APIDetails() {
		// TODO Auto-generated constructor stub
	}

	public APIDetails(String jarname, String api, String download, String status) {
		super();
		this.jarname = jarname;
		this.api = api;
		this.download = download;
		this.status = status;
	}

	public String getJarname() {
		return jarname;
	}

	public void setJarname(String jarname) {
		this.jarname = jarname;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
