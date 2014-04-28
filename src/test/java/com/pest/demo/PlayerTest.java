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
	
	@Test
	public void testResetPos(){
		myplayer.setInitialPos(2, 1);
		myplayer.resetPosition();
		assertEquals(myplayer.position, myplayer.initial_pos);
	}
	
	@Test
	public void testsetPosition(){
		assertEquals(false, myplayer.setPosition(new Position(8,8)));
		assertEquals(false, myplayer.setPosition(new Position(8,0)));
		assertEquals(false, myplayer.setPosition(new Position(0,8)));
		assertEquals(true, myplayer.setPosition(new Position(0,0)));
	}

	@Test
	public void testMove(){
		myplayer.setPosition(new Position(0,0));
		assertEquals(false,myplayer.move('u'));
		assertEquals(true,myplayer.move('d'));
		assertEquals(0,myplayer.position.getX());
		assertEquals(1, myplayer.position.getY());
		myplayer.move('u');
		assertEquals(0,myplayer.position.getX());
		assertEquals(0, myplayer.position.getY());
		myplayer.move('r');
		assertEquals(1,myplayer.position.getX());
		assertEquals(0, myplayer.position.getY());
		myplayer.move('l');
		assertEquals(0,myplayer.position.getX());
		assertEquals(0, myplayer.position.getY());
	}
}
