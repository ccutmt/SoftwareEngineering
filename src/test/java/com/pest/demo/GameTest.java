package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game mygame;
	@Before
	public void setUp() throws Exception {
		mygame = new Game();
	}

	@Test
	public void testSetNumPlayers() {
		assertEquals(true, mygame.setNumPlayers(2));
	}

}
