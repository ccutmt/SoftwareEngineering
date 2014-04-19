package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Game {

	public Player[] player;
	public static int no_players = 0;
	public static Map map = new Map();
	File file = null;
	FileWriter writer = null;
	
	public Game(){
		initStreams();
	}
	
	private void initStreams(){
		file = new File("test.html");
		try{
			file.createNewFile();
			writer = new FileWriter(file);
		}catch (Exception e){
			print("Exception occured when creating streams");
		}	
	}
	
	public void closeStreams(){
		try{
			writer.close();
			writer = null;
		}catch(Exception e){
			print("Exception occured when closing streams");
		}
	}
	
	public static void main(String[] args) {
		Scanner sc;
		Game gm = new Game();
		sc = new Scanner(System.in);
		do{
			gm.print("Enter number of players: ");
			try {
				no_players = sc.nextInt();
			} catch (NumberFormatException e) {
				gm.println("Conversion error. Please try again.");
				sc.next();
			} catch (Exception e) {
				gm.println("Something went wrong! Please try again.");
				sc.next();
			}
		}while(!gm.setNumPlayers(no_players));
		
		int mapsize = 0;
		do{
			gm.print("Enter map size: ");
			try {
				mapsize = sc.nextInt();
			} catch (NumberFormatException e) {
				gm.println("Conversion error. Please try again.");
				sc.next();
			} catch (Exception e) {
				gm.println("Something went wrong! Please try again.");
				sc.next();
			}
		}while(!map.setMapSize(mapsize, mapsize));
		map.generate();
		gm.generateHTMLFiles();
		
		
		sc.close();
		gm.closeStreams();
	}

	public void generateHTMLFiles() {
		try {

			writer.write("<html>\n");
			// head
			writer.write("<head>\n");
			// writer.write("<title> Software Engineering Assignment </title>\n");
			writer.write("</head>\n");

			// body
			writer.write("<body>\n");

			// table
			writer.write("<table>");
			for (int i = 0; i < map.getSize(); i++) {
				writer.write("<tr>");
				for (int j = 0; j < map.getSize(); j++) {
					char type = map.getTileType(i, j);
					if(type == 'b')
						writer.write("<td bgcolor='#0000FF' width='50' height='50'>");
					else writer.write("<td bgcolor='#00FF00' width='50' height='50'>");
					writer.write("</td>");
				}
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

	/*
	 * public startGame() {
	 * 
	 * }
	 */
	
	public boolean setNumPlayers(int n) {
		if (n < 2 || n > 8)
			return false;
		else
			return true;
	}

	public void print(String toprint) {
		System.out.print(toprint);
	}

	public void println(String toprint) {
		System.out.println(toprint);
	}
}
