package com.pest.demo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}
	
	@Test
	public void testGame() {
		//fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGenerateHTMLFiles() {
		assertEquals("","");
	}

	@Test
	public void testSetNumPlayers() {
		//assertEquals(false, game.setNumPlayers(1));
	}

	@Test
	public void testPrint() {
		//assertEquals("hi", game.print("hi"));
	}

	@Test
	public void testPrintln() {
		assertEquals("hi", "hi");
	}

}
