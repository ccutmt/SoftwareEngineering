package com.pest.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HazardousMapTest {

	@Test
	public void testGetInstanceIsSingleton() {
		//Check that the same instance is returned
		Map temp = HazardousMap.getInstance();
		Map temp1 = HazardousMap.getInstance();
		assertEquals(temp, temp1);
	}
	
	

}
