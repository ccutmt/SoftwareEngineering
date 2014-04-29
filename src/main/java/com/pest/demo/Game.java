package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

enum Terrain {
	LAND, WATER, TREASURE, UNKNOWN
};

public class Game {

	static Player[] players;
	public static int no_players = 0;
	public static int turn = 0;
	public static boolean game_over = false;
	public static Map map = new Map();


	public static void main(String[] args) {
		Scanner sc;
		Game gm = new Game();
		sc = new Scanner(System.in);

		// Read number of players
		do {
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
		} while (!gm.setNumPlayers(no_players));

		int mapsize = 0;

		// Read map size
		do {
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
		} while (!map.setMapSize(mapsize, mapsize));
		
		map.generate();
		gm.initPlayersPos();
		gm.generateHTMLFiles();

		while(!game_over){
			gm.println("Player: " + turn);
			gm.println("Where do you want to go? (u,d,l,r)");
			while(!players[turn].move(sc.next().charAt(0))){
				gm.println("Invalid input!\nWhere do you want to go? (u,d,l,r)");
			}
			Position temp = players[turn].getPos();
			players[turn].getPlayerMap()[temp.getY()][temp.getX()] = map.getTileType(temp.getX(), temp.getY());
			
            if(map.getTileType(temp.getX(), temp.getY()) == Terrain.TREASURE)
            	game_over = true;
            else if((map.getTileType(temp.getX(), temp.getY()) == Terrain.WATER)){
            	players[turn].resetPosition();
            }
			gm.generateHTMLFiles();
			if(turn < no_players-1)
				turn++;
			else turn=0;
			
		}
		// Clean up
		sc.close();
	}

	public void initPlayersPos(){
		//set player positions
				int xpos;
				int ypos;
				Random rn = new Random();
				for(int i = 0; i < no_players; i++){
					xpos = rn.nextInt(Map.getSize());
					ypos = rn.nextInt(Map.getSize());
					if(map.getTileType(xpos, ypos) == Terrain.LAND){
						players[i].setInitialPos(xpos, ypos);
					}else i--;
				}
	}
	
	public void generateHTMLFiles() {
		File f = null;
		FileWriter fw = null;
		try {
			for (int i = 0; i < no_players; i++) {
				f = new File("map_player_" + i + ".html");
				fw = new FileWriter(f, false);
				fw.write("<html>\n");
				fw.write("<head>\n");
				// streams[i].write("<title> Software Engineering Assignment </title>\n");
				fw.write("</head>\n");
				fw.write("<body>\n");
                                fw.write("<h1>Player " + i + " map</h1>");
				fw.write("<table>");
				for (int m = 0; m < Map.getSize(); m++) {
					fw.write("<tr>");
					for (int n = 0; n < Map.getSize(); n++) {
						Terrain type = players[i].getPlayerMap(n, m);
						switch (type) {
						case WATER: {
							fw.write("<td bgcolor='#0000FF' width='50' height='50'>");
							break;
						}
						case LAND: {
							fw.write("<td bgcolor='#00FF00' width='50' height='50'>");
							break;
						}
						case TREASURE: {
							fw.write("<td bgcolor='#FFFF00' width='50' height='50'>");
							break;
						}
						case UNKNOWN: {
							fw.write("<td bgcolor='#AAAAAA' width='50' height='50'>");
							break;
						}
						}
						if(players[i].getPos().getX() == n && players[i].getPos().getY() == m)
							fw.write("<img src='player.png' width='50' height='50'/>");
						fw.write("</td>");
					}
					fw.write("</tr>");
				}
				fw.write("</table>");
                                
                                if(game_over == true)
                                    fw.write("<pr>Congratulations!!</pr>");
                                    
				fw.write("</body>\n");
				fw.write("</html>\n");
				fw.flush();
				fw.close();
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
		else {
			// Create players
			players = new Player[no_players];
			for (int i = 0; i < no_players; i++) {
				players[i] = new Player();
			}
			return true;
		}
	}

	public void print(String toprint) {
		System.out.print(toprint);
	}

	public void println(String toprint) {
		System.out.println(toprint);
	}
}
