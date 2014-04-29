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
		Game.no_players = 3;
		mygame.setNumPlayers(3);
		Game.map = new Map();
		Game.map.setMapSize(8, 8);
		Game.map.generate();
		Game.players[0].setInitialPos(0, 0);
		Game.players[1].setInitialPos(0,0);
		Game.players[2].setInitialPos(0, 0);
		for(int i = 0; i < 8; i++){
			for(int j=0; j<8;j++){
				if(Game.map.getTileType(j, i) == Terrain.WATER){
					Game.players[0].getPlayerMap()[j][i] = Terrain.WATER;
				}else if(Game.map.getTileType(j, i) == Terrain.TREASURE){
					Game.players[1].getPlayerMap()[j][i] = Terrain.TREASURE;
				}
			}
		}
		mygame.generateHTMLFiles();
		Game.game_over = true;
		mygame.generateHTMLFiles();
		
		File p1 = new File("map_player_0.html");
		File p2 = new File("map_player_1.html");
		
		assertTrue(p1.exists());
		assertTrue(p2.exists());
	}
	
	@Test
	public void testInitPlayers(){
		Game.no_players = 2;
		mygame.setNumPlayers(2);
		Game.players = new Player[2];
		Game.map = new Map();
		Game.map.setMapSize(5,5);
		Game.players[0] = new Player();
		Game.players[1] = new Player();
		Game.map.generate();
		mygame.initPlayersPos();
		
		assertEquals(Game.players[0].getPlayerMap(Game.players[0].getPos().getX(), Game.players[0].getPos().getY()), Terrain.LAND);
		assertEquals(Game.players[1].getPlayerMap(Game.players[1].getPos().getX(), Game.players[1].getPos().getY()), Terrain.LAND);
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
