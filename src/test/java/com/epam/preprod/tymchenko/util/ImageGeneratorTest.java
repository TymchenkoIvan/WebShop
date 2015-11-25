package com.epam.preprod.tymchenko.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageGeneratorTest {

	@Test
	public void createImageReturnsNotNull() {
		assertNotNull(ImageGenerator.createImage(Integer.MAX_VALUE));
	}

}
