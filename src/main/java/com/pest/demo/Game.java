package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Game {

	public Player[] player;
	FileWriter streams[]; 
	private File files[];
	public static int no_players = 0;
	public static Map map = new Map();
	
	private void initStreams(){
		try{
			files = new File[no_players];
			streams = new FileWriter[no_players];
			for(int i =0; i<no_players; i++){
				files[i] = new File("map_player_" + i + ".html");
				files[i].createNewFile();
				streams[i] = new FileWriter(files[i]);
			}
		}catch (Exception e){
			print("Exception occured when creating streams");
			System.err.println(e);
		}	
	}
	
	public void closeStreams(){
		try{
			for(int i =0; i<no_players; i++){
				streams[i].close();
				streams[i] = null;
			}
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
		
		gm.initStreams();
		
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
			for(int i = 0; i< no_players; i++){
			streams[i].write("<html>\n");
			// head
			streams[i].write("<head>\n");
			// streams[i].write("<title> Software Engineering Assignment </title>\n");
			streams[i].write("</head>\n");

			// body
			streams[i].write("<body>\n");

			// table
			streams[i].write("<table>");
			for (int m = 0; m < map.getSize(); m++) {
				streams[i].write("<tr>");
				for (int n = 0; n < map.getSize(); n++) {
					char type = map.getTileType(m, n);
					if(type == 'b')
						streams[i].write("<td bgcolor='#0000FF' width='50' height='50'>");
					else streams[i].write("<td bgcolor='#00FF00' width='50' height='50'>");
					streams[i].write("</td>");
				}
				streams[i].write("</tr>");
			}
			streams[i].write("</table>");

			streams[i].write("</body>\n");

			streams[i].write("</html>\n");

			streams[i].flush();
			}

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
