package com.pest.demo;

public class Map {
	static int size;
	
	boolean setMapSize(int x, int y){
		if(x < 2 || x > 50)
			return false;
		else size = x;
		return true;
	}
	
	void generate(){
		
	}
	
	char getTileType(int x, int y){
		return 'a';
	}
}
