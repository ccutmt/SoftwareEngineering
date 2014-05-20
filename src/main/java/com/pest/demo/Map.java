package com.pest.demo;

abstract class Map {
	
	protected int size;
	protected static Map map;
	protected Terrain[][] tiles;
	
	protected Map(){
		
	}
	
	public boolean setMapSize(int x, int y){
		if(x > 50)
			return false;
		else if(Game.no_players <= 4 && x < 5)
			return false;
		else if(Game.no_players >= 5 && x < 8)
			return false;
		else size = x;
		return true;
	}
	
	public abstract void generate();
	
	public Terrain getTileType(int x, int y){
		return tiles[y][x];
	}
	
	public int getSize(){
		return size;
	}
}
