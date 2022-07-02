package sample;

import java.util.ArrayList;



import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;

public class AsteroidSeeker {
    public static boolean forOrBack = true;//true is forward and false is back

    public static void nextMoveAlt(int player, int[] currSpace, int roll, Digraph di) {
        for (int i = 0; i < roll; i += 0) {
            if (forOrBack == true) {
                currSpace[player] = currSpace[player] + 1;
                i++;
            } else {
                currSpace[player] = currSpace[player] - 1;
                i++;
            }
        }
        forOrBack = true;
    }


    public static void nextMove(int player, int[] currSpace, int roll, Digraph di) {
        int nodes = di.outdegree(currSpace[player]);
        int choice = roll;
        int[] moveTo = new int[nodes];
        int j = 0;
        for (int a = 0; a < nodes; a++) {
            moveTo[j] = a;
            j++;
            if (choice == a && forOrBack == true) {
                currSpace[player] = currSpace[player] + roll;
                break;
            } else if (choice == a && forOrBack == false) {
                currSpace[player] = currSpace[player] - roll;
                break;
            }
        }
        forOrBack = true;
    }

    public static void main(String[] args) {
        int numPlayers = Integer.parseInt(args[0]);
        if (numPlayers > 4 || numPlayers < 1) {
            numPlayers = StdRandom.uniform(1, 5);
        }

        In in = new In("digraph.txt");
        Digraph di = new Digraph(in);


        ArrayList<Integer> cards = new ArrayList<Integer>(8);
        while (cards.size() < 9) {
            int rand = StdRandom.uniform(6, 36);
            if (!cards.contains(rand)) {
                cards.add(rand);
            }
        }


        int[] playerPoints = new int[numPlayers];
        int[] currentSpace = new int[numPlayers];
        int[] playerTurns = new int[numPlayers];
        int[] lastSpot = new int[numPlayers];


        StdOut.println("Welcome Asteroid Seekers");
        StdOut.println("There are " + numPlayers + " Asteroid Seekers");
        StdOut.println("The Seeker with the most gold wins");
        StdOut.println();

        for (int i = 0; i < 50; i++) {
            int player = i % numPlayers;
            playerTurns[(i % numPlayers)]++;

            int die = StdRandom.uniform(1, 7);


            StdOut.println("Turn # " + playerTurns[player]);
            StdOut.println("Player " + (player + 1) + " rolls a " + die);
            lastSpot[player] = currentSpace[player];

            nextMove(player, currentSpace, die, di);

            if (cards.contains(currentSpace[player])) {
                int card = StdRandom.uniform(1, 10); // added cases 9
                switch (card) {
                    case 1:
                        playerPoints[player] = playerPoints[player] + 5;
                        StdOut.println("The player got 5 gold");
                        break;
                    case 2:
                        playerPoints[player] = playerPoints[player] - 5;
                        StdOut.println("The player lost  5 gold");
                        break;
                    case 3:
                        forOrBack = false;
                        nextMove(player, currentSpace, 3, di);
                        StdOut.println("Oh no you fell in a trap, move back 3 spaces");
                        break;
                    case 4:
                        playerPoints[player] = playerPoints[player] + 10;
                        StdOut.println("The player got 10 gold");
                        break;
                    case 5:
                        playerPoints[player] = playerPoints[player] - 10;
                        StdOut.println("The player lost  10 gold");
                        break;
                    case 6:
                        forOrBack = false;
                        nextMove(player, currentSpace, 6, di);
                        StdOut.println("Oh no the Kraken attacks, move back 6 spaces");
                        break;
                    case 7:
                        playerPoints[player] = playerPoints[player] + 15;
                        StdOut.println("The player got 15 gold");
                        break;
                    case 8:
                        playerPoints[player] = playerPoints[player] - 15;
                        StdOut.println("The player lost  15 gold");
                        break;
                    case 9:
                        currentSpace[player] = 0;
                        StdOut.println("Oh no you fell to Davy Jones Locker, return to start");
                        break;
                }

            }
            if (playerPoints[player] < 0) {
                playerPoints[player] = 0;
            }
            if (currentSpace[player] < 36) {
                StdOut.println("     Moves to space " + currentSpace[player]);

                StdOut.println("     Player " + (player + 1) + " now has " + playerPoints[player] + " gold");
                StdOut.println("_____________________________________________");
            } else if (currentSpace[player] >= 36) { // once a player reaches the end end the loop
                StdOut.println("     Player " + (player + 1) + " HAS REACHED THE FINISH LINE");
                int x = numPlayers - 1;
                int multiplyer = x * 5;
                playerPoints[player] = playerPoints[player] + multiplyer;
                StdOut.println("     Player " + (player + 1) + " finished before " + x + " players, as a bonus Player " + (player + 1) + " gets " + multiplyer + " gold!!");
                break;
            }


        }
        StdOut.println("_____________________________________________");
        StdOut.println();
        StdOut.println("Statistics");
        StdOut.println();
        int winner = 0;
        int[] playerPointsCopy = new int[playerPoints.length];
        System.arraycopy(playerPoints, 0, playerPointsCopy, 0, playerPoints.length);
        int max = playerPointsCopy[0];
        for (int k = 0; k < playerPointsCopy.length; ++k) {
            if (playerPointsCopy[k] > max) {
                max = playerPointsCopy[k];
            }
        }
        for (int i = 0; i < numPlayers; i++) {
            StdOut.println("Player " + (i + 1) + ": ");
            StdOut.println("Total Gold: " + playerPoints[i]);
            StdOut.println("_____________________________________________");
            if (playerPoints[i] == max) {
                winner = i + 1;
            }

        }
        StdOut.println("The winner is player " + winner + " with a score of " + max + " gold");


    }
}
