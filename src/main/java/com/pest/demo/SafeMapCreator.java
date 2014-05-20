package com.pest.demo;

public class SafeMapCreator extends Creator {

	@Override
	public Map createMap(){
		return SafeMap.getInstance();
	}
}
