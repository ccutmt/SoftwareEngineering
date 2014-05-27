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
    private Position latest_pos;
    
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
    
    @Override
    public void notifyObservers()
    {
    	System.out.println("notifying");
        for(int i = 0; i < observers.size(); i++)
        {
            observers.get(i).Update();
        }
    }
    
    public void setLatest(Position p){
    	latest_pos = p;
    }
    
    public Position getUpdate(){
    	return latest_pos;
    }
}
