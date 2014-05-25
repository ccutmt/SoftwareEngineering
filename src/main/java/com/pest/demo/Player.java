package com.pest.demo;

public class Player {
	private Position position = null;
	private Position initial_pos = null;
	private boolean[][] player_map = null;

	public Player(int x, int y, int mapsize) {
		player_map = new boolean[mapsize][mapsize];
		for (int i = 0; i < mapsize; i++) {
			for (int j = 0; j < mapsize; j++) {
				player_map[i][j] = false;
			}
		}
		initial_pos = new Position(x, y);
		position = new Position(x, y);
		player_map[y][x] = true;
	}

	public boolean isMapSeen(int x, int y) {
		return player_map[y][x];
	}
	
	public void setMapSeen(int x, int y) {
		player_map[y][x] = true;
	}

	public boolean move(char direction) {
		switch (direction) {
			case 'u':
			case 'U': {
				Position newpos = new Position(position.getX(),
						position.getY() - 1);
				return setPosition(newpos);
			}
			case 'l':
			case 'L': {
				Position newpos = new Position(position.getX() - 1,
						position.getY());
				return setPosition(newpos);
			}
			case 'd':
			case 'D': {
				Position newpos = new Position(position.getX(),
						position.getY() + 1);
				return setPosition(newpos);
			}
			case 'r':
			case 'R': {
				Position newpos = new Position(position.getX() + 1,
						position.getY());
				return setPosition(newpos);
			}
			default: {
				return false;
			}
		}
	}

	public boolean setPosition(Position p) {
		if (p.getX() >= 0 && p.getX() < player_map.length
				&& p.getY() < player_map.length && p.getY() >= 0) {
			position = p;
			return true;
		} else
			return false;
	}

	public Position getPos() {
		return position;
	}

	public void resetPosition() {
		position = initial_pos;
	}
}
