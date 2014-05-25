package com.pest.demo;

public class MapMock extends Map {

	public MapMock() { }
	
	public MapMock(Terrain[][] tiles) {
		super.tiles = tiles;
		super.size = tiles.length;
	}
	
	@Override
	public void generate() {
		//do nothing;
	}

}
