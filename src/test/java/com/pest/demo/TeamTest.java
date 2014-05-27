/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pest.demo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TeamTest {
    
    public void testTeam()
    {
        Team team = new Team();
        Player p1 = new Player(0,1,5);
        Player p2 = new Player(3,3,5);
        
        p1.setTeam(team);
        p2.setTeam(team);
        
        p2.move('d');
        
        assertTrue(p1.isMapSeen(3,4));
    }
}
