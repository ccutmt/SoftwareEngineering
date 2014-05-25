package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private Player[] players;
	private boolean initCalled = false;
	private int no_players = -1;
	private int turn = 0;
	private boolean game_over = false;
	private boolean last_turn = false;
	private Map map;

	private ArrayList<Integer> winners = new ArrayList<Integer>();

	public Game(Map map) {
		this.map = map;
	}

	public boolean isLastTurn() {
		return last_turn;
	}

	public int getCurrentTurn() {
		return turn;
	}

	public boolean setNextMove(char move) {
		if (!initCalled) {
			init();
		}

		if (!players[turn].move(move)) {
			return false;
		}

		Position temp = players[turn].getPos();
		players[turn].setMapSeen(temp.getX(), temp.getY());

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

		return true;
	}

	public boolean setNumPlayers(int n) {
		if (n < 2 || n > 8) {
			return false;
		} else {
			no_players = n;
		}
		return true;
	}

	public int getNumPlayers() {
		return no_players;
	}

	public Map getMap() {
		return this.map;
	}

	public boolean setMapSize(int size) {
		return this.map.setMapSize(size, size, no_players);
	}

	public void init() {
		initCalled = true;
		map.generate();
		initPlayersPos();
		generateHTMLFiles();
	}

	private void initPlayersPos() {
		int xpos;
		int ypos;
		Random rn = new Random();

		players = new Player[no_players];
		for (int i = 0; i < no_players; i++) {
			do {
				xpos = rn.nextInt(map.getSize());
				ypos = rn.nextInt(map.getSize());
			} while (map.getTileType(xpos, ypos) != Terrain.LAND);

			players[i] = new Player(xpos, ypos, map.getSize());
		}
	}

	private void generateHTMLFiles() {
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
					String terrainType = "<td bgcolor='#%s' width='50' height='50'>";
					String color = "";
					for (int n = 0; n < map.getSize(); n++) {
						Terrain type = players[i].isMapSeen(n, m) ? map
								.getTileType(n, m) : Terrain.UNKNOWN;
						switch (type) {
							case WATER: {
								color = "0000FF";
								break;
							}
							case LAND: {
								color = "00FF00";
								break;
							}
							case TREASURE: {
								color = "FFFF00";
								break;
							}
							case UNKNOWN: {
								color = "AAAAAA";
								break;
							}
						}
						fw.write(String.format(terrainType, color));

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
			System.err.print("Exception:" + e.getMessage());
		}
	}

}
