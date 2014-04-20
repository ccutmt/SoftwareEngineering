package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	Player myplayer;
	Map mymap;
	

	@Before
	public void setUp() throws Exception {
		mymap = new Map();
		mymap.setMapSize(8, 8);
		myplayer = new Player();
	}
	

	@Test
	public void testPlayer() {
		assertNotNull(myplayer.player_map);
	}

	@Test
	public void testSetInitialPos() {
		myplayer.setInitialPos(0, 0);
		assertNotNull(myplayer.position);
		assertNotNull(myplayer.initial_pos);
		assertEquals(myplayer.position.getX(), 0);
		assertEquals(myplayer.position.getY(), 0);
		assertEquals(myplayer.initial_pos.getX(), 0);
		assertEquals(myplayer.initial_pos.getY(), 0);
		assertEquals(myplayer.player_map[0][0], Terrain.LAND);
	}

}
