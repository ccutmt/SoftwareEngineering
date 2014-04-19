package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map mymap;
	@Before
	public void setUp() throws Exception {
		mymap = new Map();
	}

	@Test
	public void testSetMapSize() {
		Game.no_players = 5;
		assertEquals(mymap.setMapSize(51,51), false);
		assertEquals(mymap.setMapSize(2,51), false);
		assertEquals(mymap.setMapSize(2,2), false);
		assertEquals(mymap.setMapSize(7,7), false);
		Game.no_players = 2;
		assertEquals(mymap.setMapSize(7,7), true);
	}

}
