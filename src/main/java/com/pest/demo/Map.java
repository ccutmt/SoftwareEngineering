package com.pest.demo;

import java.util.Random;

public class Map {
	private int size;
	private char[][] tiles;
	
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
		tiles = new char[size][size];
		Random rn = new Random();
		for(int i = 0; i< size; i++){
			for(int j = 0; j < size; j++){
				if(rn.nextInt()%2 == 0)
					tiles[i][j] = 'b';
				else tiles[i][j] = 'g';
			}
		}
	}
	
	public char getTileType(int x, int y){
		return tiles[x][y];
	}
	
	public int getSize(){
		return size;
	}
}
