package com.service;

public class Login {

	private String username = "�����Ƽ�";
	
	
	public String getUsername() {
		return username;
	}


	public String welcome(String username)
	{
		return "welcome-you:" + username;
	}
}
