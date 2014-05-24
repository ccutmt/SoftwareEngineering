package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreatorTest {

	@Test
	public void testGetCreatorSafe() {
		Creator c = new Creator();
		Map m = c.createMap(1);
		assertTrue(m instanceof SafeMap);
	}
	
	@Test
	public void testGetCreatorHaz() {
		Creator c = new Creator();
		Map m = c.createMap(2);
		assertTrue(m instanceof HazardousMap);
	}


}
