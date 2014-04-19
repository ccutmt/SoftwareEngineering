package com.pest.demo;

public class Map {
	static int size;
	
	boolean setMapSize(int x, int y){
		if(x > 50)
			return false;
		else if(Game.no_players <= 4 && x >= 5)
			return true;
		else if(Game.no_players <= 8 && x < 8)
			return false;
		else size = x;
		generate();
		return true;
	}
	
	void generate(){
		
	}
	
	char getTileType(int x, int y){
		return 'a';
	}
}
