package com.pest.demo;

public class HazardousMapCreator extends Creator {
	@Override
	public Map createMap(){
		return HazardousMap.getInstance();
	}
}
