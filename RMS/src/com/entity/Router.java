package com.entity;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Router implements java.io.Serializable {

	// Fields

	private Integer id;
	private String serial;
	private String level;
	private String message;
	private String time;
	private String clearedBy;
	private String caseid;
	private int status;
	private String casedby;

	// Constructors

	/** default constructor */
	public Router() {
	}

	/** minimal constructor */
	public Router(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Router(Integer id, String serial, String level, String message,
			String time, String clearedBy) {
		this.id = id;
		this.serial= serial;
		this.level= level;
		this.message= message;
		this.time= time;
		this.clearedBy= clearedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getClearedBy() {
		return clearedBy;
	}

	public void setClearedBy(String clearedBy) {
		this.clearedBy = clearedBy;
	}

	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCasedby() {
		return casedby;
	}

	public void setCasedby(String casedby) {
		this.casedby = casedby;
	}

	
	// Property accessors


}