package com.pest.demo;

import java.util.Random;

public class SafeMap extends Map {

	private SafeMap(){
		super();
	}
	
	public static Map getInstance(){
		if(Map.map == null){
			Map.map = new SafeMap();
		}
		return Map.map;
	}
	
	public void generate(){
		super.tiles = new Terrain[super.size][super.size];
		Random rn = new Random();
		int blue_count = 0;
		int max_water = (int)0.1*(size*size);
		for(int i = 0; i< size; i++){
			for(int j = 0; j < size; j++){
				if(rn.nextInt(2) == 0 && blue_count < max_water){
					tiles[i][j] = Terrain.WATER;
					blue_count++;
				}
				else tiles[i][j] = Terrain.LAND;
			}
		}
		tiles[rn.nextInt(size)][rn.nextInt(size)] = Terrain.TREASURE;
	}
}
