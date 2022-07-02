package sample;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class GameBoard {
    public static void drawScore(boolean animateTally,
                                 int[] playerScores) {
        StdDraw.picture(0.85, 0.85, "scoreboard.png", 0.4, 0.35);
        Font font = new Font("Arial Black", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.85, 0.95, "SCORE");

        double defaultTallyMarkXPosition = 0.75;
        double defaultTallyMarkYStartPosition = 0.885;

        double tallyMarkXStartPosition = defaultTallyMarkXPosition;

        double tallyMarkYStartPosition = defaultTallyMarkYStartPosition;
        double tallyMarkYEndPosition = 0.91;
        double tallyMarkXPosition = tallyMarkXStartPosition;
        double playerImageXPosition = tallyMarkXStartPosition - 0.035;


        //Draw the player pictures

        for (int playerNumber = 0; playerNumber < playerScores.length; playerNumber++) {
            String gamePieceImageFileName = "player" + (playerNumber + 1) + ".png"; //Determines the game piece filename
            double imageYPosition = 0.90 - (0.05 * playerNumber);
            StdDraw.picture(playerImageXPosition, imageYPosition, gamePieceImageFileName, 0.05, 0.05);
        }


        for (int playerScoreIterator = 0; playerScoreIterator < playerScores.length; playerScoreIterator++) {
            int theScore = playerScores[playerScoreIterator];
            int totalDrawnTallyMarks = 0;
            while (totalDrawnTallyMarks < theScore) {
                double totalNumberOfTallyMarksRemaining = theScore - totalDrawnTallyMarks;
                double numberOfTallyMarksThisSet = 5;
                if (totalNumberOfTallyMarksRemaining < 5) {
                    numberOfTallyMarksThisSet = totalNumberOfTallyMarksRemaining;
                }

                tallyMarkXStartPosition = tallyMarkXPosition; //Resets the starting point for the following number 5
                // line
                for (double N = 1; N <= numberOfTallyMarksThisSet; N++) {
                    StdDraw.setPenRadius(0.005);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    tallyMarkXPosition += 0.005;
                    if (N == (5)) {
                        StdDraw.line(tallyMarkXStartPosition, tallyMarkYStartPosition, tallyMarkXPosition,
                                tallyMarkYEndPosition);
                    } else {
                        StdDraw.line(tallyMarkXPosition, tallyMarkYStartPosition, tallyMarkXPosition,
                                tallyMarkYEndPosition);
                    }

                    //StdOut.println(tallyMarkXPosition);
                    if (animateTally == true) {
                        StdDraw.show();
                        StdDraw.pause(100);
                    }
                    totalDrawnTallyMarks++;
                }

            }
            tallyMarkXPosition = defaultTallyMarkXPosition;
            tallyMarkYStartPosition -= 0.05;
            tallyMarkYEndPosition -= 0.05;


        }


        StdDraw.show();
    }

    public static void movePlayers(int[] playerPositions, int[] previousPlayerPositions,
                                   double[][] relativeGamePositions) {

        int[] drawnPositions = new int[playerPositions.length];
        //This array contains all of the game board space numbers that have been drawn on the screen already
//draw the game pieces
        for (int i = 0; i < playerPositions.length; i++) //Loops through the arguments
        {
            StdDraw.clear();
            //animate the game board


            for (int x = previousPlayerPositions[i]; x <= playerPositions[i]; x++) //Loops through the arguments
            {

                int nextPositionToDraw;

                drawGameUnderlay();
                ArrayList<Integer> playerPositionsToDraw = new ArrayList<Integer>();
                drawPlayers(previousPlayerPositions, relativeGamePositions);
                for (int v = 0; v <= i; v++) {
                    int[] playerPositionsToDrawArray = playerPositionsToDraw.stream().mapToInt(c -> c).toArray();
                    StdOut.println(Arrays.toString(playerPositionsToDrawArray));
                    drawPlayers(playerPositionsToDrawArray, relativeGamePositions);
                    //playerPositionsToDraw.add(playerPositions[v]);
                    nextPositionToDraw = v;
                    playerPositionsToDraw.add(playerPositions[nextPositionToDraw]);
                    //StdDraw.pause(500);

                }

                double xPosition = relativeGamePositions[x][0]; //X coordinate of the game piece
                double yPosition = 1 - relativeGamePositions[x][1]; //Y coordinate of the game piece
                for (int y = 0; y < i; y++) //Loop through already drawn game pieces
                {
                    if (drawnPositions[y] == playerPositions[i]) //If there is already a game piece at that position
                    {
                        xPosition += 0.005; //Shift the piece to the right
                        yPosition += 0.01; //Shift the piece up
                    }
                }


                String gamePieceImageFileName = "player" + (i + 1) + ".png"; //Determines the game piece filename
                //draw the game board

                StdDraw.picture(xPosition, yPosition, gamePieceImageFileName, 0.1, 0.1);
                drawGameOverlay();
                drawnPositions[i] = playerPositions[i]; //Save the position for later comparison
                StdDraw.show();
                StdDraw.pause(200);
                previousPlayerPositions[i] = -1; //-1 means do not draw the object again
            }
        }
    }

    public static void drawGameUnderlay() {
        StdDraw.picture(0.5, 0.5, "board.jpg", 1.0, 1.0);
    }

    public static void drawGameOverlay() {
        drawScore(false, new int[]{3, 10, 15, 4});

    }

    public static void drawPlayers(int[] playerPositions, double[][] relativeGamePositions) {
        int[] drawnPositions = new int[playerPositions.length];
        //draw the game pieces
        for (int i = 0; i < playerPositions.length; i++) //Loops through the arguments
        {
            if (playerPositions[i] == -1) {
                continue;
            }

            double xPosition = relativeGamePositions[playerPositions[i]][0]; //X coordinate of the game piece
            double yPosition = 1 - relativeGamePositions[playerPositions[i]][1]; //Y coordinate of the game piece
            for (int y = 0; y < i; y++) //Loop through already drawn game pieces
            {
                if (drawnPositions[y] == playerPositions[i]) //If there is already a game piece at that position
                {
                    xPosition += 0.005; //Shift the piece to the right
                    yPosition += 0.01; //Shift the piece up
                }
            }
            String gamePieceImageFileName = "player" + (i + 1) + ".png"; //Determines the game piece filename
            StdDraw.picture(xPosition, yPosition, gamePieceImageFileName, 0.1, 0.1);
            //drawScore();
            drawnPositions[i] = playerPositions[i]; //Save the position for later comparison
            //StdDraw.show();
        }
    }

    public static void drawBoard(
            int[] previousPlayerPositions,
            int[] playerPositions,
            String cardType,
            String cardText) {

        double[][] relativeGamePositions = //Maps game board spaces to their relative coordinates on a scale of 0-1.
                {
                        {0.17240, 0.21397},
                        {0.25, 0.27619},
                        {0.32812, 0.28063},
                        {0.39114, 0.23682},
                        {0.44635, 0.18412},
                        {0.5151, 0.16317},
                        {0.57031, 0.20444},
                        {0.59843, 0.28126},
                        {0.56979, 0.36126},
                        {0.50729, 0.39809},
                        {0.42656, 0.38412},
                        {0.35052, 0.3638},
                        {0.28697, 0.35936},
                        {0.21875, 0.38539},
                        {0.17447, 0.46539},
                        {0.1901, 0.54476},
                        {0.25468, 0.60698},
                        {0.32968, 0.61523},
                        {0.40833, 0.58603},
                        {0.48281, 0.52507},
                        {0.5427, 0.45587},
                        {0.60937, 0.4146},
                        {0.67968, 0.39746},
                        {0.75312, 0.42603},
                        {0.77812, 0.51873},
                        {0.74218, 0.59936},
                        {0.67135, 0.62857},
                        {0.59739, 0.61904},
                        {0.52291, 0.61269},
                        {0.44791, 0.63174},
                        {0.40208, 0.6946},
                        {0.41041, 0.77206},
                        {0.46718, 0.82603},
                        {0.53697, 0.81841},
                        {0.59062, 0.76126},
                        {0.64062, 0.71174},
                        {0.71666, 0.71238},
                        {0.76354, 0.77079},
                        {0.81822, 0.85523},
                };

        //Checks if the supplied input space is beyond the finish line.
        for (int i = 1; i < playerPositions.length; i++) //Loop through already drawn game pieces
        {
            if (playerPositions[i] >= relativeGamePositions.length) {
                playerPositions[i] = relativeGamePositions.length - 1; //Corrects position to the last space instead.

            }
        }

        StdDraw.enableDoubleBuffering(); //Enables drawing in the background before showing completed rendering
        StdDraw.setCanvasSize(1219, 1000); //Configures the window resolution
        StdDraw.clear();

        //draw the game board
        StdDraw.picture(0.5, 0.5, "board.jpg", 1.0, 1.0);

        //dice roll
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledSquare(0.5, 0.5, 0.1);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(0.5, 0.5, 0.01);


        //Move the players
        drawPlayers(playerPositions, relativeGamePositions);
        movePlayers(playerPositions, previousPlayerPositions, relativeGamePositions);
        drawPlayers(playerPositions, relativeGamePositions);


        //StdOut.println(playerPositions.length);


        //Draw a card
        if (cardType.equalsIgnoreCase("Bonus")) {
            for (double t = -0.20; t < 0.20; t += 0.01) {
                double x = 0.5;
                double y = t;
                //StdDraw.clear();
                StdDraw.picture(x, y, "BonusCard.png");
                Font font = new Font("Arial Black", Font.BOLD, 50);
                StdDraw.setFont(font);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(0.5, (y + 0.15), cardText);
                //StdDraw.picture(0.5, 0.15, "player1.png", 0.2, 0.2);
                StdDraw.show();
                StdDraw.pause(30);

            }
            StdDraw.pause(5000);
        }
        if (cardType.equalsIgnoreCase("Trap")) {
            for (double t = -0.20; t < 0.20; t += 0.01) {
                double x = 0.5;
                double y = t;
                //StdDraw.clear();
                StdDraw.picture(x, y, "TrapCard.png");
                Font font = new Font("Arial Black", Font.BOLD, 50);
                StdDraw.setFont(font);
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(0.5, (y + 0.15), cardText);
                //StdDraw.picture(0.5, 0.15, "player1.png", 0.2, 0.2);
                StdDraw.show();
                StdDraw.pause(30);

            }
            StdDraw.pause(5000);

/*        	Font font = new Font("Arial Black", Font.BOLD, 60);
        	StdDraw.setFont(font);
        	StdDraw.setPenColor(Color.WHITE);
        	StdDraw.text(0.5, 0.35, cardText);
        	StdDraw.picture(0.5, 0.15, "player1.png", 0.2, 0.2);*/

        }

        StdDraw.show();
    }

    public static void main(String[] args) throws InterruptedException {


        //=CONCATENATE("{",TRUNC(E1,5),",",TRUNC(F1,5),"}",",")

        //drawBoard(new int[]{1, 3, 10, 23}, "Trap", "BOTTOM TEXT");
        //Players 1-4 positions,card type (if applicable), card text (if applicable)
        //drawBoard(new int[]{2, 5, 15, 30}, "Bonus", "BOTTOM TEXT");
        //Thread.sleep(250);
        drawBoard(new int[]{10, 14, 16, 18}, new int[]{25, 20, 16, 18}, "NoCard", "BOTTOM TEXT");
    }
}

