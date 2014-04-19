package com.pest.demo;

public class Position {
	private int x,y;
	
	Position(int xpos, int ypos){
		x = xpos;
		y = ypos;
	}
	
	public void setX(int xpos){
		x = xpos;
	}
	
	public void setY(int ypos){
		y = ypos;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
