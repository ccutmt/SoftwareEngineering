package com.pest.demo;

public class Creator {

	Map createMap(int type) {
		return getFactory(type).createMap();
	}

	public Map createMap() {
		return createMap(0);
	}

	private MapFactory getFactory(int type) {
		switch (type) {
			case 1: {
				return new SafeMapFactory();
			}
			case 2: {
				return new HazardousMapFactory();
			}
			default: {
				return new SafeMapFactory();
			}
		}
	}
}
