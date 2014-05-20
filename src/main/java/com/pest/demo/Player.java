package com.pest.demo;

public class Player {
	Position position = null;
	Position initial_pos = null;
	Terrain[][] player_map = null;
	
	public Player(){
		
	}
	
	public void setInitialPos(int x, int y, int mapsize){
		player_map = new Terrain[mapsize][mapsize];
		for(int i = 0; i < mapsize; i++){
			for(int j = 0; j < mapsize; j++){
				player_map[i][j] = Terrain.UNKNOWN;
			}
		}
		initial_pos = new Position(x,y);
		position = new Position(x,y);
		player_map[y][x] = Terrain.LAND;
	}
	
	Terrain getPlayerMap(int x, int y){
		return player_map[y][x];
	}
	
	boolean move(char direction){
		switch(direction){
		case 'u':
		case 'U': {
			Position newpos = new Position(position.getX(), position.getY()-1);
			return setPosition(newpos);
		}
		case 'l':
		case 'L':{
			Position newpos = new Position(position.getX() - 1, position.getY());
			return setPosition(newpos);
		}
		case 'd':
		case 'D':{
			Position newpos = new Position(position.getX(), position.getY()+1);
			return setPosition(newpos);
		}
		case 'r':
		case 'R':{
			Position newpos = new Position(position.getX() + 1, position.getY());
			return setPosition(newpos);
		}
		default:{
			return false;
		}
		}
	}
	
	boolean setPosition(Position p){
		if(p.getX() >= 0 && p.getX() < player_map.length && p.getY() < player_map.length && p.getY() >= 0){
			position = p;
			return true;
		}
		else return false;
	}
	
	Terrain[][] getPlayerMap(){
		return player_map;
	}
	
	Position getPos(){
		return position;
	}
	
	void resetPosition(){
		position = initial_pos;
	}
}
