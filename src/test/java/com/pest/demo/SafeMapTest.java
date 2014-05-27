package com.pest.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SafeMapTest {

	@Test
	public void testGetInstanceIsSingleton() {
		//Check that the same instance is returned
		Map temp = SafeMap.getInstance();
		Map temp1 = SafeMap.getInstance();
		assertEquals(temp, temp1);
	}
	
	@Test
	public void testGenerateSafeMap(){
		Map hmap = SafeMap.getInstance();
		
		hmap.setMapSize(5,5,2);
		hmap.generate();
		int blue_count = 0;
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(hmap.getTileType(j, i) == Terrain.WATER){
					blue_count++;
				}
			}
		}
		double max = 0.1 * 25;
		double result = (double)blue_count;
		assertTrue(result < max);
	}

}
