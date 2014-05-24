package com.pest.demo;

import java.util.Random;

public class HazardousMap extends Map {

	private HazardousMap() {
		super();
	}

	public static Map getInstance() {
		if (Map.map == null || Map.map instanceof SafeMap) {
			Map.map = new HazardousMap();
		}
		return Map.map;
	}

	@Override
	public void generate() {
		super.tiles = new Terrain[super.size][super.size];
		Random rn = new Random();

		int blue_count = 0;
		int green_count = 0;
		int max_water_limit = (int) 0.35 * (size * size);
		int max_land_limit = (int) 0.75 * (size * size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (rn.nextInt(2) == 0) {
					if (blue_count < max_water_limit) {
						tiles[i][j] = Terrain.WATER;
						blue_count++;
					} else {
						tiles[i][j] = Terrain.LAND;
						green_count++;
					}
				} else {
					if (green_count < max_land_limit) {
						tiles[i][j] = Terrain.LAND;
						green_count++;
					}else{
						tiles[i][j] = Terrain.WATER;
						blue_count++;
					}
				}
			}
		}
		tiles[rn.nextInt(size)][rn.nextInt(size)] = Terrain.TREASURE;
	}

}
