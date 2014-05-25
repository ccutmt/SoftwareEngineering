package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HazardousMapTest {

	Map hmap;
	
	@Before
	public void setUp() throws Exception {
		hmap = HazardousMap.getInstance();		
	}

	@Test
	public void testGetInstance() {
		//Check that the same instance is returned
		Map temp = HazardousMap.getInstance();
		assertEquals(temp, hmap);
	}
	
	

}
