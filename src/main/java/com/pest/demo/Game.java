package com.pest.demo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	private Player[] players = null;
	private int turn = 0;
	private boolean game_over = false;
	private boolean team_play = false;
	private Map map;
	private Team[] teams = null;
	private ArrayList<Integer> winners = new ArrayList<Integer>();

	public Game(Map map) {
		this.map = map;
	}

	public Game(Map map, Player[] players) {
		this(map);
		this.players = players;
	}

	public Player[] getPlayers() {
		return players;
	}

	public boolean isEndGame() {
		return (game_over && turn == 0);
	}

	public int getCurrentTurn() {
		return turn;
	}

	public boolean setNextMove(char move) {
		if (!players[turn].move(move)) {
			return false;
		}

		Position temp = players[turn].getPos();

		switch (map.getTileType(temp.getX(), temp.getY())) {
			case TREASURE:
				game_over = true;
				winners.add(turn);
				break;
			case WATER:
				players[turn].resetPosition();
				break;
			case LAND:
				;
			case UNKNOWN:
				;
		}

		if (turn < players.length - 1)
			turn++;
		else {
			turn = 0;
			generateHTMLFiles();
		}

		return true;
	}

	public boolean setNumPlayers(int n) {
		if (n < 2 || n > 8) {
			return false;
		}

		players = new Player[n];
		return true;
	}

	public int getNumPlayers() {
		return players.length;
	}

	public boolean setNumTeams(int t) {
		if (t > getNumPlayers())
			return false;
		teams = new Team[t];
		team_play = true;
		return true;
	}

	private void initTeams() {
		for (int i = 0; i < getNumTeams(); i++) {
			teams[i] = new Team();
		}
	}

	public int getNumTeams() {
		return teams.length;
	}

	private void setPlayersInTeams() {
		int number_players = getNumPlayers();
		int teamno = 0;
		while (number_players > 0) {
			int player_no = (int) (Math.random() * (getNumPlayers()));
			if (players[player_no].getTeam() == null) {
				teams[teamno].AddObserver(players[player_no]);
				players[player_no].setTeam(teams[teamno]);
				number_players--;
				teamno++;
			}
			if (teamno == teams.length) {
				teamno = 0;
			}
		}
	}

	public boolean setMapSize(int size) {
		return this.map.setMapSize(size, size, players.length);
	}

	public void init() {
		map.generate();
		initPlayers();
		if(team_play){
			initTeams();
			setPlayersInTeams();
			shareStart();
		}
		generateHTMLFiles();
	}

	private void shareStart(){
		for(int i = 0; i < players.length; i++){
			players[i].getTeam().setLatest(players[i].getPos());
			players[i].getTeam().notifyObservers();
		}
	}
	
	private void initPlayers() {
		int xpos;
		int ypos;
		Random rn = new Random();

		for (int i = 0; i < players.length; i++) {
			do {
				xpos = rn.nextInt(map.getSize());
				ypos = rn.nextInt(map.getSize());
			} while (map.getTileType(xpos, ypos) != Terrain.LAND);

			players[i] = new Player(xpos, ypos, map.getSize());
		}
	}

	public String generatePlayerHTMLMap(Player p) {
		StringBuilder sb = new StringBuilder();
		String color = "";
		String terrainType = "<td bgcolor='#%s' width='50' height='50'>";

		sb.append("<table>");
		for (int m = 0; m < map.getSize(); m++) {
			sb.append("<tr>");

			for (int n = 0; n < map.getSize(); n++) {
				Terrain type = p.isMapSeen(n, m) ? map.getTileType(n, m)
						: Terrain.UNKNOWN;
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
				sb.append(String.format(terrainType, color));

				if (p.getPos().getX() == n && p.getPos().getY() == m)
					sb.append("<img src='player.png' width='50' height='50'/>");
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	private void generateHTMLFiles() {
		FileWriter fw = null;
		try {
			for (int i = 0; i < players.length; i++) {
				fw = new FileWriter(new File("map_player_" + i + ".html"),
						false);
				fw.write("<html><head></head><body><h1>Player " + i
						+ " map</h1>\n");

				fw.write(generatePlayerHTMLMap(players[i]));

				if (isEndGame()) {
					fw.write("<p>Congratulations!!");
					for (int z = 0; z < winners.size(); z++) {
						fw.write(String.format("<br/>Player %d is a winner!",
								winners.get(z)));
					}
					fw.write("</p>");
				}
				fw.write("</body></html>\n");
				fw.flush();
				fw.close();
			}

		} catch (IOException e) {
			System.err.print("Exception:" + e.getMessage());
		}
	}

}
