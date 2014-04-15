/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Christopher
 */
public class Game {
    
    public static Player[] player;
    public static int no_players = 0;
    public Game()
    {
        
    }
    
    public static void main(String[] args) {  
        Game gm = new Game();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        no_players = sc.nextInt();
        
        setNumPlayers(no_players);
        generateHTMLFiles();
    }
    
    public static void generateHTMLFiles()
    {
        try {

            File file = new File("test.html");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            writer.write("<html>\n");
            //head
            writer.write("<head>\n");
            //writer.write("<title> Software Engineering Assignment </title>\n");
            writer.write("</head>\n");
            
            //body
            writer.write("<body>\n");
            
            
            //table
            writer.write("<table style=\"width:300px\">");
            for (int i = 0; i < 3; i++) {   
                writer.write("<tr>");
                writer.write("<td>");
                for (int j = 0; j < 3; j++) {
                    writer.write("*");
                }

                writer.write("</td>");
                writer.write("</tr>");
            }
            writer.write("</table>");
            
            writer.write("</body>\n");
            
            writer.write("</html>\n");

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.print("Exception");
        }
    }
    
    /*public startGame()
    {
        
    }*/
    
    public static boolean setNumPlayers(int n)
    {
       if(no_players < 2 || no_players > 8)
           return false;
       else
           return true;
    }
}
