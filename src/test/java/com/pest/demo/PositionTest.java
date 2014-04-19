package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	Position mypos = null;
	@Before
	public void setUp() throws Exception {
		mypos = new Position(0,0);
	}

	@Test
	public void testSetX() {
		mypos.setX(5);
		assertEquals(mypos.getX(), 5);
	}
	
	@Test
	public void testSetY() {
		mypos.setY(5);
		assertEquals(mypos.getY(), 5);
	}

}
