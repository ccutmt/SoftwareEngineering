package com.pest.demo;

import java.util.Scanner;

public class GameRunner {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Game gm = null;

		boolean valid = false;
		do {
			System.out
					.print("Do you want to play in collaborative mode? (Y/N)");
			try {
				char temp = sc.next().charAt(0);
				if (temp == 'Y' || temp == 'y') {
					valid = true;
					// TODO team code
				} else if (temp == 'n' || temp == 'N') {
					valid = true;
				}
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
				if (maptype > 0 && maptype <= 2) {
					valid = true;
					Creator c = new Creator();
					//set map
					gm = new Game(c.createMap(maptype));
				}
			} catch (Exception e) {
				System.out.println("Something went wrong! Please try again.");
				sc.next();
			}
		} while (!valid);

		// Read number of players
		int no_players = -1;
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
		} while (!gm.setMapSize(mapsize));

		gm.init();
		gameLoop(gm, sc);
		// Clean up
		sc.close();
	}

	public static void gameLoop(Game gm, Scanner sc) {
		while (!gm.isLastTurn()) {
			System.out.println("Player: " + gm.getCurrentTurn());
			System.out.println("Where do you want to go? (u,d,l,r)");
			while (!gm.setNextMove(sc.next().charAt(0)))
				System.out.println("Invalid input!\nWhere do you want to go? (u,d,l,r)");
		}
	}
}
