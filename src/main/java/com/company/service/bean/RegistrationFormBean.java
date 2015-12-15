package com.company.service.bean;

/**
 * Bean object contains fields from registration form.
 * 
 * @author Ivan_Tymchenko
 *
 */
public class RegistrationFormBean implements Bean{
	
	private static final long serialVersionUID = 6669036688120766276L;
	
	private String firstName;
	private String lastName;
	private String login;
	private String mail;
	private String password;
	private String rePassword;
	private String captchaCode;
	private String[] notifications;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCaptchaCode() {
		return captchaCode;
	}
	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
	public String[] getNotifications() {
		return notifications;
	}
	public void setNotifications(String[] notifications) {
		this.notifications = notifications;
	}
	
}
