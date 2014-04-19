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
	public void testPrintln(){
		mygame.print("test");
		assertEquals("test", outContent.toString());
	}
	
	@Test
	public void testInitStreams(){
		Game.no_players = 2;
		mygame.initStreams();
		for(int i = 0; i < Game.no_players; i++){
			assertNotNull(mygame.streams[i]);
		}
	}
	
	@Test
	public void testCloseStreams(){
		Game.no_players = 2;
		mygame.initStreams();
		mygame.closeStreams();
		for(int i = 0; i < Game.no_players; i++){
			assertNull(mygame.streams[i]);
		}
	}
	
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
