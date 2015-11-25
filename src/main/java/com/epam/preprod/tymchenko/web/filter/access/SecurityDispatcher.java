package com.epam.preprod.tymchenko.web.filter.access;

import org.apache.log4j.Logger;

import com.epam.preprod.tymchenko.entity.security.Constraint;
import com.epam.preprod.tymchenko.entity.security.Security;
import com.epam.preprod.tymchenko.util.ClassNameUtil;

public class SecurityDispatcher {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	private Security security;

	public SecurityDispatcher(Security security) {
		this.security = security;

		LOG.info("SecurityDispatcher(Security security)");
	}

	public boolean isUrlSecured(String url) {
		LOG.info("SecurityDispatcher.isSecuredURL(String url)");
		boolean res = false;
		
		for (Constraint constraint : security.getConstraints()) {
			
			if (url.contains(constraint.getUrlPattern())) {
				res = true;
				break;
			}
		}
		
		return res;
	}

	public boolean isAccessAllowed(String url, String role) {
		LOG.info("SecurityDispatcher.isNotForbidden(String url, Role role)");
		boolean res = false;
		
		for (Constraint constraint : security.getConstraints()) {
			
			if (url.contains(constraint.getUrlPattern()) && constraint.getRoles().contains(role.toLowerCase())) {
				res = true;
				break;
			}
		}

		return res;
	}
}
