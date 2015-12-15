package com.company.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.company.tymchenko.util.ImageGenerator;

public class ImageGeneratorTest {

	@Test
	public void createImageReturnsNotNull() {
		assertNotNull(ImageGenerator.createImage(Integer.MAX_VALUE));
	}

}
