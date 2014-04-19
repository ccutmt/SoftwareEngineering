package com.pest.demo;

public class Player {
	Position position;
	
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
