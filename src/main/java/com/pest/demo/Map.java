package com.pest.demo;

import java.util.Random;

public class Map {
	private static int size;
	
	Terrain[][] tiles;
	
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
	
	public void generate(){
		tiles = new Terrain[size][size];
		Random rn = new Random();
		int blue_count = 0;
		for(int i = 0; i< size; i++){
			for(int j = 0; j < size; j++){
				if(rn.nextInt(2) == 0 && blue_count < size-1){
					tiles[i][j] = Terrain.WATER;
					blue_count++;
				}
				else tiles[i][j] = Terrain.LAND;
			}
		}
		tiles[rn.nextInt(size)][rn.nextInt(size)] = Terrain.TREASURE;
	}
	
	public Terrain getTileType(int x, int y){
		return tiles[y][x];
	}
	
	public static int getSize(){
		return size;
	}
}
