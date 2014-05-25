package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreatorTest {

	@Test
	public void testGetCreatorSafeMap() {
		Creator c = new Creator();
		Map m = c.createMap(1);
		assertTrue(m instanceof SafeMap);
	}
	
	@Test
	public void testGetCreatorHazardousMap() {
		Creator c = new Creator();
		Map m = c.createMap(2);
		assertTrue(m instanceof HazardousMap);
	}
	
	@Test
	public void testDefaultMapIsSafeMap() {
		Creator c = new Creator();
		Map m = c.createMap();
		assertTrue(m instanceof SafeMap);
	}
	
	@Test
	public void testUnknownMapIsSafeMap() {
		Creator c = new Creator();
		Map m = c.createMap(5);
		assertTrue(m instanceof SafeMap);
	}
}
