package com.pest.demo;

public class Player implements Observer {
	private Position position = null;
	private Position initial_pos = null;
	private boolean[][] player_map = null;
	private Team team = null;

	public Player(int startX, int startY, int mapsize) {
		player_map = new boolean[mapsize][mapsize];
		for (int i = 0; i < mapsize; i++) {
			for (int j = 0; j < mapsize; j++) {
				player_map[i][j] = false;
			}
		}
		initial_pos = new Position(startX, startY);
		position = new Position(startX, startY);
		player_map[startY][startX] = true;
	}

	public void setTeam(Team s) {
		team = s;
	}

	public Team getTeam() {
		return team;
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
			setMapSeen(p.getX(), p.getY());
                        if(team != null)
                        {
                            team.setLatest(p);
                            team.notifyObservers();
                        }
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

	@Override
	public void Update() {
		Position temp = team.getUpdate();
		setMapSeen(temp.getX(), temp.getY());
	}
}
