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
	public static boolean last_turn = false;
	public static Map map;
	public static Scanner sc;
	public static ArrayList<Integer> winners = new ArrayList<Integer>();

	public static void main(String[] args) {
		Game gm = new Game();
		sc = new Scanner(System.in);

		String team = "";
		boolean valid = false;
		do {
			System.out
					.print("Do you want to play in collaborative mode? (Y/N)");
			try {

				team = sc.next();
				char temp = team.charAt(0);
				if (temp == 'Y' || temp == 'y') {
					valid = true;
					// TODO team code
				} else if (temp == 'n' || temp == 'N') {
					valid = true;
				} else
					valid = false;

			} catch (Exception e) {
				System.out.println("Something went wrong! Please try again.");
				sc.next();
			}
		} while (!valid);

		valid = false;
		int maptype;
		do {
			System.out
					.print("Choose a map type:\n[1] Safe Map\n[2] Hazardous Map\n");
			try {

				maptype = sc.nextInt();
				if(maptype > 0 && maptype <= 2){
					Creator c = new Creator();
					valid = true;
					Game.map = c.createMap(maptype);
				}else valid = false;
				
			} catch (Exception e) {
				System.out.println("Something went wrong! Please try again.");
				sc.next();
			}
		} while (!valid);

		// Read number of players
		do {
			System.out.print("Enter number of players: ");
			try {
				no_players = sc.nextInt();
			} catch (NumberFormatException e) {
				System.out.println("Conversion error. Please try again.");
				sc.next();
			} catch (Exception e) {
				System.out.println("Something went wrong! Please try again.");
				sc.next();
			}
		} while (!gm.setNumPlayers(no_players));

		int mapsize = 0;

		// Read map size
		do {
			System.out.print("Enter map size: ");
			try {
				mapsize = sc.nextInt();
			} catch (NumberFormatException e) {
				System.out.println("Conversion error. Please try again.");
				sc.next();
			} catch (Exception e) {
				System.out.println("Something went wrong! Please try again.");
				sc.next();
			}
		} while (!map.setMapSize(mapsize, mapsize));

		map.generate();
		gm.initPlayersPos();
		gm.generateHTMLFiles();
		gm.gameLoop();
		// Clean up
		sc.close();
	}

	public void gameLoop() {
		while (!last_turn) {
			System.out.println("Player: " + turn);
			System.out.println("Where do you want to go? (u,d,l,r)");
			while (!players[turn].move(sc.next().charAt(0))) {
				System.out
						.println("Invalid input!\nWhere do you want to go? (u,d,l,r)");
			}
			Position temp = players[turn].getPos();
			players[turn].getPlayerMap()[temp.getY()][temp.getX()] = map
					.getTileType(temp.getX(), temp.getY());

			if (map.getTileType(temp.getX(), temp.getY()) == Terrain.TREASURE) {
				game_over = true;
				winners.add(turn);
			} else if ((map.getTileType(temp.getX(), temp.getY()) == Terrain.WATER)) {
				players[turn].resetPosition();
			}

			if (turn < no_players - 1)
				turn++;
			else {
				if (game_over)
					last_turn = true;
				turn = 0;
				generateHTMLFiles();
			}

		}
	}

	public void initPlayersPos() {
		// set player positions
		int xpos;
		int ypos;
		Random rn = new Random();
		for (int i = 0; i < no_players; i++) {
			xpos = rn.nextInt(map.getSize());
			ypos = rn.nextInt(map.getSize());
			if (map.getTileType(xpos, ypos) == Terrain.LAND) {
				players[i].setInitialPos(xpos, ypos, map.getSize());
			} else
				i--;
		}
	}

	public void generateHTMLFiles() {
		File f = null;
		FileWriter fw = null;
		try {
			for (int i = 0; i < no_players; i++) {
				f = new File("map_player_" + i + ".html");
				fw = new FileWriter(f, false);
				fw.write("<html><head></head><body><h1>Player " + i
						+ " map</h1>\n");
				fw.write("<table>");
				for (int m = 0; m < map.getSize(); m++) {
					fw.write("<tr>");
					for (int n = 0; n < map.getSize(); n++) {
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
						if (players[i].getPos().getX() == n
								&& players[i].getPos().getY() == m)
							fw.write("<img src='player.png' width='50' height='50'/>");
						fw.write("</td>");
					}
					fw.write("</tr>");
				}
				fw.write("</table>");

				if (game_over && turn == 0) {
					fw.write("<pr>Congratulations!!");
					for (int z = 0; z < winners.size(); z++) {
						fw.write("<br/>Player " + winners.get(z)
								+ " is a winner!");
					}
					fw.write("</pr>");
				}
				fw.write("</body>\n");
				fw.write("</html>\n");
				fw.flush();
				fw.close();
			}

		} catch (IOException e) {
			System.out.print("Exception");
		}
	}

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
}
