package com.pest.demo;

import static org.junit.Assert.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

public class GameTest {

	Game mygame;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		mygame = new Game();
	}
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}


	@Test
	public void testSetNumPlayers() {
		assertEquals(true, mygame.setNumPlayers(2));
		assertEquals(true, mygame.setNumPlayers(8));
		assertEquals(false, mygame.setNumPlayers(9));
		assertEquals(false, mygame.setNumPlayers(1));
	}
	
	@Test
	public void testPrint(){
		mygame.print("test");
		assertEquals("test", outContent.toString());
	}
	
	@Test
	public void testGenerateHtml(){
		Game.no_players = 2;
		mygame.generateHTMLFiles();
		File p1 = new File("map_player_0.html");
		File p2 = new File("map_player_1.html");
		assertTrue(p1.exists());
		assertTrue(p2.exists());
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
