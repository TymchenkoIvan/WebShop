package com.epam.preprod.tymchenko.service.bean;

/**
 * Bean object contains fields from login form.
 * 
 * @author Ivan_Tymchenko
 */
public class LoginFormBean implements Bean{
	
	private static final long serialVersionUID = 434242076584312785L;
	
	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
