package com.company.web.filter.access;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.company.entity.security.Security;
import com.company.tymchenko.util.ClassNameUtil;

public class SecurityXmlParser {

	private static final Logger LOG = Logger.getLogger(ClassNameUtil.getCurrentClassName());

	public Security parse(String path) {
		LOG.trace("SecurityXmlParser.parse(String path)");
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Security.class);
			File file = new File(path);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			return (Security) jaxbUnmarshaller.unmarshal(file);
			
		} catch (JAXBException ex) {
			LOG.warn(ex.getMessage());
		}
		
		return null;
	}
}
