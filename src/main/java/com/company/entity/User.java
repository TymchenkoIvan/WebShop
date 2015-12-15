package com.company.entity;

/**
 * User entity realization.
 * 
 * @author Ivan_Tymchenko
 */
public class User implements Entity{
	
	private long id;
	private long roleId;
	private String login;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String[] notifications;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getNotifications() {
		return notifications;
	}
	public void setNotifications(String[] notifications) {
		this.notifications = notifications;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
