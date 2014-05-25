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
		mygame = new Game(new Creator().createMap(1));
		mygame.setNumPlayers(2);
	}

	@Test
	public void testTwoPlayersAllowed() {
		assertTrue(mygame.setNumPlayers(2));
		assertEquals(2, mygame.getNumPlayers());
	}
	
	@Test
	public void testLessThanTwoPlayersNotAllowed() {
		assertFalse(mygame.setNumPlayers(1));
	}
	
	@Test
	public void testMoreThanEightPlayersNotAllowed() {
		assertFalse(mygame.setNumPlayers(9));
	}
	
	@Test
	public void testEightPlayersAllowed() {
		assertTrue(mygame.setNumPlayers(8));
		assertEquals(8, mygame.getNumPlayers());
	}
	
	/*
	@Test
	public void testGenerateHtml(){		
		mygame.setNumPlayers(3);
		mygame.setMapSize(8);
		mygame.init();

		/*for(int i = 0; i < 8; i++){
			for(int j=0; j<8;j++){
				if(Game.map.getTileType(j, i) == Terrain.WATER){
					Game.players[0].getPlayerMap()[j][i] = Terrain.WATER;
				}else if(Game.map.getTileType(j, i) == Terrain.TREASURE){
					Game.players[1].getPlayerMap()[j][i] = Terrain.TREASURE;
				}
			}
		}
		mygame.generateHTMLFiles();
		GameRunner.game_over = true;
		mygame.generateHTMLFiles();
		
		File p1 = new File("map_player_0.html");
		File p2 = new File("map_player_1.html");
		
		assertTrue(p1.exists());
		assertTrue(p2.exists());
	}
	
	@Test
	public void testInitPlayersPos(){
		GameRunner.map.setMapSize(5,5);
		GameRunner.map.generate();
		GameRunner.no_players = 2;
		mygame.initPlayersPos();
		
		assertEquals(GameRunner.map.getTileType(GameRunner.players[0].getPos().getX(), GameRunner.players[0].getPos().getY()), Terrain.LAND);
		assertEquals(GameRunner.map.getTileType(GameRunner.players[1].getPos().getX(), GameRunner.players[1].getPos().getY()), Terrain.LAND);
	}
	
	@Test
	public void testGameLoop(){
		mygame.setNumPlayers(2);
		GameRunner.no_players = 2;
		GameRunner.map.setMapSize(5,5, 2);
		GameRunner.map.generate();
		mygame.initPlayersPos();
		GameRunner.players[0].setInitialPos(0,0, 5);
		GameRunner.players[1].setInitialPos(0,0, 5);
		GameRunner.turn = 0;
		GameRunner.game_over=false;
		String inputData = "f\nr\nr\nd\nd\nd\nd\n";
		System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
		GameRunner.sc = new Scanner(System.in);
		Game.GameRunner.tiles[1][0] = Terrain.TREASURE;
		Game.GameRunner.tiles[0][1] = Terrain.LAND;
		Game.GameRunner.tiles[1][1] = Terrain.WATER;
		mygame.gameLoop();
		assertEquals(true, GameRunner.game_over);
		assertEquals(2, GameRunner.winners.size());
	}
*/
}
