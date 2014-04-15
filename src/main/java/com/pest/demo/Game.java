/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Christopher
 */
public class Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            File file = new File("test.html");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            writer.write("<HTML>\n");
            //HEAD
            writer.write("<HEAD>\n");
            writer.write("<TITLE> Software Engineering Assignment </TITLE>\n");
            writer.write("</HEAD>\n");
            
            //BODY
            writer.write("<BODY>\n");
            
            //FORM
            int tiles = 0;
            writer.write("<FORM>\n");
            writer.write("Number of tiles: <input type=\"text\" name=\"tiles\"><br>\n");
            writer.write("</FORM>\n");
            
            writer.write("</BODY>\n");
            
            writer.write("</HTML>\n");
            
            /*Scanner sc = new Scanner(System.in);
            System.out.print("Enter an integer ");
            int x = sc.nextInt();

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < x; j++) {
                    writer.write("*");
                }

                writer.write("");
            }*/

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.print("Exception");
        }
    }
}
