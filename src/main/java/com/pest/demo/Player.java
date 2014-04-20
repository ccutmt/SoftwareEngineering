package com.pest.demo;

public class Player {
	Position position;
	Position initial_pos;
	Terrain[][] player_map;
	
	public Player(){
		player_map = new Terrain[Map.getSize()][Map.getSize()];
		for(int i = 0; i < Map.getSize(); i++){
			for(int j = 0; j < Map.getSize(); j++){
				player_map[i][j] = Terrain.UNKNOWN;
			}
		}
	}
	
	public void setInitialPos(int x, int y){
		initial_pos = new Position(x,y);
		position = new Position(x,y);
		player_map[x][y] = Terrain.LAND;
	}
	
	Terrain getPlayerMap(int x, int y){
		return player_map[x][y];
	}
	
	void move(char direction){
		switch(direction){
		case 'u':
		case 'U': {
			position.setX(position.getX() - 1);
			break;
		}
		case 'l':
		case 'L':{
			position.setY(position.getY() + 1);
			break;
		}
		case 'd':
		case 'D':{
			position.setY(position.getY() + 1);
			break;
		}
		case 'r':
		case 'R':{
			position.setX(position.getX() - 1);
			break;
		}
		}
	}
	
	boolean setPosition(Position p){
		return true;
	}
}
