package com.pest.demo;

public class Creator {

	public Creator(){
		
	}
	
	Map createMap(int type){
		Creator mapcreator = getCreator(type);
		if(mapcreator != null)
			return mapcreator.createMap();
		else return null;
	}
	
	public Map createMap(){
		return null;
	}
	
	private Creator getCreator(int type){
		switch (type){
		case 1:{
			return new SafeMapCreator();
		}
		case 2:{
			return new HazardousMapCreator();
		}
		default:{
			return null;
		}
		}
	}
}
