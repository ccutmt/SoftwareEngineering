package com.pest.demo;

public interface Subject {
    
   public void AddObserver(Observer obs);
   public void RemoveObserver(Observer obs);
   public void notifyObservers();
       
}
