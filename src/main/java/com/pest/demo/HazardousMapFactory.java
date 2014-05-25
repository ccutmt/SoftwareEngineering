package com.pest.demo;

public class HazardousMapFactory extends MapFactory {

	@Override
	public Map createMap() {
		return HazardousMap.getInstance();	
	}

}
