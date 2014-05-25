package com.pest.demo;

abstract class Map {

	protected int size;
	protected Terrain[][] tiles;

	protected Map() {
	}

	public boolean setMapSize(int x, int y, int no_players) {
		if (x > 50 || (no_players <= 4 && x < 5) || (no_players >= 5 && x < 8)) {
			return false;
		}
		size = x;
		return true;
	}

	public abstract void generate();

	public Terrain getTileType(int x, int y) {
		return tiles[y][x];
	}

	public int getSize() {
		return size;
	}
}
