package com.pest.demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapTest {

	Map mymap;
	@Before
	public void setUp() throws Exception {
		mymap = new Map();
	}

	@Test
	public void testSetMapSize() {
		Game.no_players = 5;
		assertEquals(mymap.setMapSize(51,51), false);
		assertEquals(mymap.setMapSize(7,7), false);
		Game.no_players = 2;
		assertEquals(mymap.setMapSize(7,7), true);
		assertFalse(mymap.setMapSize(3, 3));
	}

	@Test
	public void testGenerate(){
		Game.no_players = 5;
		mymap.setMapSize(8,8);
		mymap.generate();
		int water_count = 0;
		int treasure_count = 0;
		for(int i = 0; i < Map.getSize(); i++){
			for(int j = 0; j < Map.getSize(); j++){
				switch(mymap.getTileType(i, j)){
				case WATER:{
					water_count++;
					break;
				}
				case TREASURE:{
					treasure_count++;
					break;
				}
				case UNKNOWN:
				case LAND:
				}
			}
		}
		assertEquals(Map.getSize()-1, water_count);
		assertEquals(1,treasure_count);
	}
}
