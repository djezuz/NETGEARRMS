package com.entity;

public class LassHeartbeat {

	private int id;
	public String deviceId="";
	private String createDatetime;
	private String caseid;
	private String casedby;
	private String cleartime;
	
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCaseid() {
		return caseid;
	}
	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}
	public String getCasedby() {
		return casedby;
	}
	public void setCasedby(String casedby) {
		this.casedby = casedby;
	}
	public String getCleartime() {
		return cleartime;
	}
	public void setCleartime(String cleartime) {
		this.cleartime = cleartime;
	}
	
	
	
	
}
