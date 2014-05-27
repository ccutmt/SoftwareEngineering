package com.pest.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testInitialPositionIsSet(){
		Player myPlayer = new Player(3, 2, 5);
		assertEquals(3, myPlayer.getPos().getX());
		assertEquals(2, myPlayer.getPos().getY());
	}
	
	@Test
	public void testResetPositionIsEqualToInitialPosition(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		myPlayer.move('d');
		myPlayer.resetPosition();
		
		assertEquals(2, myPlayer.getPos().getX());
		assertEquals(1, myPlayer.getPos().getY());
	}

	@Test
	public void testInitialPositionSeen() {
		Player myPlayer = new Player(1,1,5);
		assertTrue(myPlayer.isMapSeen(1, 1));
	}
	
	@Test
	public void testMoveInvalidCharacter(){
		Player myPlayer = new Player(2, 1, 5);
		
		assertFalse(myPlayer.move('j'));
	}
	
	
	@Test
	public void testMoveDownLowerCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('d'));
		assertEquals(2, myPlayer.getPos().getX());
		assertEquals(2, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveDownUpperCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('D'));
		assertEquals(2, myPlayer.getPos().getX());
		assertEquals(2, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveUpLowerCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('u'));
		assertEquals(2, myPlayer.getPos().getX());
		assertEquals(0, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveUpUpperCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('U'));
		assertEquals(2, myPlayer.getPos().getX());
		assertEquals(0, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveLeftLowerCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('l'));
		assertEquals(1, myPlayer.getPos().getX());
		assertEquals(1, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveLeftUpperCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('L'));
		assertEquals(1, myPlayer.getPos().getX());
		assertEquals(1, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveRightLowerCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('r'));
		assertEquals(3, myPlayer.getPos().getX());
		assertEquals(1, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveRightUpperCaseAllowed(){
		Player myPlayer = new Player(2, 1, 5);
		
		myPlayer.setTeam(new Team());
		assertTrue(myPlayer.move('R'));
		assertEquals(3, myPlayer.getPos().getX());
		assertEquals(1, myPlayer.getPos().getY());
	}
	
	@Test
	public void testMoveDownOutOfMap(){
		Player myPlayer = new Player(3, 4, 5);
		
		assertFalse(myPlayer.move('d'));
	}
	
	@Test
	public void testMoveUpOutOfMap(){
		Player myPlayer = new Player(3, 0, 5);
		
		assertFalse(myPlayer.move('u'));
	}
	
	@Test
	public void testMoveLeftOutOfMap(){
		Player myPlayer = new Player(0, 3, 5);
		
		assertFalse(myPlayer.move('l'));
	}
	
	@Test
	public void testMoveRightOutOfMap(){
		Player myPlayer = new Player(4, 3, 5);
		
		assertFalse(myPlayer.move('r'));
	}
	
	@Test
	public void testSetMapSeenIsTrue(){
		Player myPlayer = new Player(4, 3, 5);
		myPlayer.setMapSeen(1, 1);
		assertTrue(myPlayer.isMapSeen(1, 1));
	}
	
	@Test
	public void testUnseenMapIsFalse(){
		Player myPlayer = new Player(4, 3, 5);
		assertFalse(myPlayer.isMapSeen(1, 1));
	}
}
