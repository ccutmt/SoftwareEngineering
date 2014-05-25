package com.pest.demo;

import static org.junit.Assert.*;
import org.junit.Test;

public class MapTest {

	@Test
	public void testMapSize51NotAllowed() {
		MapMock map = new MapMock();
		assertFalse(map.setMapSize(51, 51, 2));
	}
	
	@Test
	public void testMapSizeFewPlayersSmallMap(){
		MapMock map = new MapMock();
		assertTrue(map.setMapSize(5 , 5, 4));
	}
	
	@Test
	public void testMapSizeFewPlayersBadMap(){
		MapMock map = new MapMock();
		assertFalse(map.setMapSize(4 , 4, 2));
	}
	
	@Test
	public void testMapSizeManyPlayersSmallMap(){
		MapMock map = new MapMock();
		assertFalse(map.setMapSize(5 , 5, 8));
	}
	
	@Test
	public void testMapSizeManyPlayersGoodMap(){
		MapMock map = new MapMock();
		assertTrue(map.setMapSize(8 , 8, 8));
	}
	
	@Test
	public void testMapGetSize(){
		MapMock map = new MapMock();
		map.setMapSize(5, 5, 2);
		assertEquals(map.getSize(), 5);
	}
}
