package com.pest.demo;

public class SafeMapFactory extends MapFactory {

	@Override
	public Map createMap() {
		return SafeMap.getInstance();
	}

}
