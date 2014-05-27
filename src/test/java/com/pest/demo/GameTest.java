package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void testTwoPlayersAllowed() {
		Game mygame = new Game(new Creator().createMap(1));
		
		assertTrue(mygame.setNumPlayers(2));
		assertEquals(2, mygame.getNumPlayers());
	}
	
	@Test
	public void testLessThanTwoPlayersNotAllowed() {
		Game mygame = new Game(new Creator().createMap(1));
		
		assertFalse(mygame.setNumPlayers(1));
	}
	
	@Test
	public void testMoreThanEightPlayersNotAllowed() {
		Game mygame = new Game(new Creator().createMap(1));
		
		assertFalse(mygame.setNumPlayers(9));
	}
	
	@Test
	public void testEightPlayersAllowed() {
		Game mygame = new Game(new Creator().createMap(1));
		
		assertTrue(mygame.setNumPlayers(8));
		assertEquals(8, mygame.getNumPlayers());
	}
	
	@Test
	public void testPlayerHTMLMapIsCorrect() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player p = new Player(1, 0, 4);
		p.setMapSeen(1, 0); p.setMapSeen(2, 0); p.setMapSeen(3, 0);
		p.setMapSeen(1, 1); p.setMapSeen(2, 1); p.setMapSeen(3, 1);
		p.setMapSeen(1, 2); p.setMapSeen(2, 2); p.setMapSeen(3, 2);
		p.setMapSeen(1, 3); p.setMapSeen(2, 3); p.setMapSeen(3, 3);
		
		Game mygame = new Game(new MapMock(tiles));
		
		
		String expectedOutput = 
		"<table>"
		+ "<tr><td bgcolor='#AAAAAA' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'><img src='player.png' width='50' height='50'/></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td></tr>"
		+ "<tr><td bgcolor='#AAAAAA' width='50' height='50'></td><td bgcolor='#FFFF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#0000FF' width='50' height='50'></td></tr>"
		+ "<tr><td bgcolor='#AAAAAA' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td></tr>"
		+ "<tr><td bgcolor='#AAAAAA' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td><td bgcolor='#00FF00' width='50' height='50'></td></tr>"
		+ "</table>";
		
		assertEquals(expectedOutput, mygame.generatePlayerHTMLMap(p));
	}
	
	@Test
	public void testSetMapSizeIsPropogatedToMap() {
		Map m = new MapMock();
		
		Game mygame = new Game(m);
		mygame.setNumPlayers(5);
		mygame.setMapSize(10);
		
		assertEquals(10, m.getSize());
	}
	
	@Test
	public void testTurnChangesAfterMove() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(0,0,4);
		player1.setTeam(new Team());
		player2.setTeam(new Team());
		Player[] players = { player1, player2 };
		Game mygame = new Game(new MapMock(tiles), players);
		
		assertEquals(0, mygame.getCurrentTurn());
		mygame.setNextMove('d');
		assertEquals(1, mygame.getCurrentTurn());
	}
	
	
	@Test
	public void testGameEndsAfterPlayer1FindsTreasureAndRoundComplete() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(0,0,4);
		player1.setTeam(new Team());
		player2.setTeam(new Team());
		Player[] players = { player1, player2 };
		Game mygame = new Game(new MapMock(tiles), players);
		
		mygame.setNextMove('d');
		mygame.setNextMove('d');
		
		assertTrue(mygame.isEndGame());
	}
	
	@Test
	public void testGameEndsAfterPlayer1FindsTreasureAndRoundNotComplete() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(0,0,4);
		player1.setTeam(new Team());
		player2.setTeam(new Team());
		Player[] players = { player1, player2 };
		Game mygame = new Game(new MapMock(tiles), players);
		
		
		mygame.setNextMove('d');
		
		assertFalse(mygame.isEndGame());
	}
	
	@Test
	public void testGameDoesNotEndAfterOneMove() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(0,0,4);
		player1.setTeam(new Team());
		player2.setTeam(new Team());
		Player[] players = { player1, player2 };
		Game mygame = new Game(new MapMock(tiles), players);
		
		mygame.setNextMove('l');
		
		assertFalse(mygame.isEndGame());
	}
	
	
	@Test
	public void testPlayerPositionResetAfterFindingWater() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(3,0,4);
		Player[] players = { player1, player2 };
		player1.setTeam(new Team());
		player2.setTeam(new Team());
		Game mygame = new Game(new MapMock(tiles), players);
		
		
		mygame.setNextMove('d');
		mygame.setNextMove('d');
		
		assertEquals(3, player2.getPos().getX());
		assertEquals(0, player2.getPos().getY());
	}
	
	@Test
	public void testPlayerPositionDoesNotChangeAfterIllegalMove() {
		Terrain[][] tiles = {
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.TREASURE, Terrain.LAND, Terrain.WATER},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
				{ Terrain.LAND, Terrain.LAND, Terrain.LAND, Terrain.LAND},
		};
		
		Player player1 = new Player(1, 0, 4);
		Player player2 = new Player(3,0,4);
		Player[] players = { player1, player2 };
		Game mygame = new Game(new MapMock(tiles), players);
		
		mygame.setNextMove('u');
		
		assertEquals(1, player1.getPos().getX());
		assertEquals(0, player1.getPos().getY());
	}
	
	@Test
	public void testTeamCreation(){
		Game mygame = new Game(new Creator().createMap(1));
		
		mygame.setNumPlayers(5);
		mygame.setNumTeams(2);
		assertEquals(2, mygame.getNumTeams());
	}
	
	@Test
	public void testSetTeamGreaterThanPlayers(){
		Game mygame = new Game(new Creator().createMap(1));
		
		mygame.setNumPlayers(2);
		assertFalse(mygame.setNumTeams(5));
	}
	
	@Test
	public void testEveryPlayerHasTeam(){
		Game mygame = new Game(new Creator().createMap(1));
		
		mygame.setNumPlayers(4);
		mygame.setMapSize(5);
		mygame.setNumTeams(4);
		mygame.init();
		
		Player[] players = mygame.getPlayers();
		for(int i = 0; i < players.length; i++){
			assertNotNull(players[i].getTeam());
		}
	}
}
