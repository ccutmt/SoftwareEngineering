package com.pest.demo;

import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

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
		assertEquals(true, mygame.setNumPlayers(8));
		assertEquals(false, mygame.setNumPlayers(9));
		assertEquals(false, mygame.setNumPlayers(1));
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
		Game.map = new Map();
		Game.map.setMapSize(5,5);
		Game.map.generate();
		mygame.initPlayersPos();
		
		assertEquals(Game.players[0].getPlayerMap(Game.players[0].getPos().getX(), Game.players[0].getPos().getY()), Terrain.LAND);
		assertEquals(Game.players[1].getPlayerMap(Game.players[1].getPos().getX(), Game.players[1].getPos().getY()), Terrain.LAND);
	}
	
	@Test
	public void testGameLoop(){
		mygame.setNumPlayers(2);
		Game.no_players = 2;
		Game.map=new Map();
		Game.map.setMapSize(5,5);
		Game.map.generate();
		mygame.initPlayersPos();
		Game.players[0].setInitialPos(0,0);
		Game.players[1].setInitialPos(0,0);
		Game.turn = 0;
		Game.game_over=false;
		String inputData = "f\nr\nr\nd\nd\nd\nd\n";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		Game.sc = new Scanner(System.in);
		Game.map.tiles[1][0] = Terrain.TREASURE;
		Game.map.tiles[0][1] = Terrain.LAND;
		Game.map.tiles[1][1] = Terrain.WATER;
		mygame.gameLoop();
	}

}
