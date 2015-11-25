package com.epam.preprod.tymchenko.entity.security;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "constraint")
public class Constraint {
	
	private String urlPattern; 
	
	private List<String> roles;

	public String getUrlPattern() {
		return urlPattern;
	}

	@XmlElement(name = "url-pattern")
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public List<String> getRoles() {
		return roles;
	}

	@XmlElement(name = "role")
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
