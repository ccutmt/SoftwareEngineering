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
        generatehtmlFiles();
    }
    
    public static void generatehtmlFiles()
    {
        try {

            File file = new File("test.html");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            writer.write("<html>\n");
            //head
            writer.write("<head>\n");
            writer.write("<title> Software Engineering Assignment </title>\n");
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
