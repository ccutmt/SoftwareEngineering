package com.pest.demo;

public interface Subject {
    
   public void attach(Observer obs);
   public void deattach(Observer obs);
   public void notifyObservers();
       
}
