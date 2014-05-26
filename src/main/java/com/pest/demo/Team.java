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
    
    public void notifyObservers()
    {
        System.out.println("notofy observer!");
    }
}
