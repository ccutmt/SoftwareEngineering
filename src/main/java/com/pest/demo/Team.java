/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pest.demo;

/**
 *
 * @author Christopher
 */
import java.util.*;

public class Team implements Subject{
    
    private ArrayList<Observer> observers;
    int latestX;
    int latestY;
    
    public Team()
    {
        this.observers = new ArrayList<Observer>();
    }
    
    @Override
    public void AddObserver(Observer obs)
    {
        observers.add(obs);
    }
    
    @Override
    public void RemoveObserver(Observer obs)
    {
        observers.remove(obs);
    }
    
    public int getLatestX()            
    {
        return latestX;
    }
    
    public int getLatestY()
    {
        return latestY;
    }
    
    public void setLatestX(int x)
    {
        latestX = x;
    }
    
    public void setLatestY(int y)
    {
        latestY = y;
    }
    /*public void Update()
    {
        
    }*/
    
    @Override
    public void notifyObservers()
    {
        for(int i = 0; i < observers.size(); i++)
        {
            observers.get(i).Update();
        }
    }
}
