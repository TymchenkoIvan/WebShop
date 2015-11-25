package com.epam.preprod.tymchenko.entity.security;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "security")
public class Security {

	private List<Constraint> constraints;

	@XmlElement(name = "constraint")
	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}

	public List<Constraint> getConstraints() {
		return constraints;
	}
}
