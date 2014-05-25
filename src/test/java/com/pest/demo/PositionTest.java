package com.pest.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testSetX() {
		Position mypos = new Position(0, 0);
		mypos.setX(5);
		assertEquals(5, mypos.getX());
	}
	
	@Test
	public void testSetY() {
		Position mypos = new Position(0, 0);
		mypos.setY(5);
		assertEquals(5, mypos.getY());
	}

}
