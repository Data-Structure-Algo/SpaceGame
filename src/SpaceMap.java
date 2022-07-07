import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class SpaceMap {
    // The following is the main method.
    public static void main(String[] args) {

        StdOut.println("******************************");
        StdOut.println();
        StdOut.println("Welcome to the Space Map Game");
        StdOut.println();
        StdOut.println("******************************");
        StdOut.println();

// We are initializing variables for each player (1-4).
        String p1;
        String p2;
        String p3;
        String p4;
        // We are creating variables for position of each player.

        int NewPosP1 = 0;
        int NewPosP2 = 0;
        int NewPosP3 = 0;
        int NewPosP4 = 0;


        Scanner player = new Scanner(System.in);
        // We are asking name of first player. Only 4 players are allowed.
        StdOut.println("Input your name, player one:");
        p1 = player.nextLine();//Take input from user.
        StdOut.println("Input your name, player two:");
        // We are asking name of the second player.
        p2 = player.nextLine();//Take input from user.
        // We are asking a name of third player.
        StdOut.println("Input your name, player three:");
        p3 = player.nextLine(); //Take input from user.
        // We are asking a name of forth player.
        StdOut.println("Input your name, player four:");
        p4 = player.nextLine();
        player.close();
        StdOut.println();
        StdOut.println("The list of players:");
        StdOut.println();
        StdOut.println("******************************");
        StdOut.println("1. " + p1);
        StdOut.println();
        StdOut.println("2. " + p2);
        StdOut.println();
        StdOut.println("3. " + p3);
        StdOut.println();
        StdOut.println("4. " + p4);
        StdOut.println("******************************");
        StdOut.println("Let's get started!");


        do {
            Random rand = new Random();

            // First player's turn.************************************
            int random_integer = rand.nextInt(6 - 1) + 1; // We are generating random number 1-6.
            int a = random_integer;
            NewPosP1 = NewPosP1 + a;
            StdOut.println();
            StdOut.println(p1 + " throws dices, your number is " + a);
            StdOut.println();
            StdOut.println(p1 + " position is " + NewPosP1);

            // If statements for traps and bonuses of the game. Position 3 is a mine, you die and return to 0 position.
            if (NewPosP1 == 3) {
                NewPosP1 = 0;
                StdOut.println();
                StdOut.println(p1 + " you got a mine. You are getting back to the position " + NewPosP1);
            }

            // If statements for traps and bonuses of the game. Position 26 is a canon, you die and return to 16 position.
            if (NewPosP1 == 26) {
                NewPosP1 = 16;
                StdOut.println();
                StdOut.println(p1 + " you got a mine. You are getting back to the position " + NewPosP1);
            }

            // If statements for traps and bonuses of the game. Position 12 is a bonus, you will be in position 17.
            if (NewPosP1 == 12) {
                NewPosP1 = 17;
                StdOut.println();
                StdOut.println(p1 + " you got a bonus. You are getting to the position " + NewPosP1);
            }


            // Try block to check for exceptions
            try {

                // Step 1: Create an object of BufferedWriter
                BufferedWriter f_writer
                        = new BufferedWriter(new FileWriter(
                        "demo.txt"));

                // Step 2: Write text(content) to file
                f_writer.write(NewPosP1);

                // Step 3: Printing the content inside the file
                // on the terminal/CMD
                // System.out.print(NewPosP1);

                // Step 4: Display message showcasing
                // successful execution of the program
                //  System.out.print(
                //          "File is created successfully with the content.");

                // Step 5: Close the BufferedWriter object
                f_writer.close();
            }

            // Catch block to handle if exceptions occurs
            catch (IOException e) {

                // Print the exception on console
                // using getMessage() method
                //   System.out.print(e.getMessage());
            }


            // If statement that determines who is winner. 40 is maximum positions in the game.
            if (NewPosP1 >= 40) {
                break;
            }


            // Second player's turn****************************************
            int random_integer2 = rand.nextInt(6 - 1) + 1;
            // currentPosP1 = random_integer;
            int b = random_integer2;
            NewPosP2 = NewPosP2 + b;

            StdOut.println();
            StdOut.println(p2 + " throws dices, your number is " + b);
            StdOut.println();
            StdOut.println(p2 + " position is " + NewPosP2);


            // If statements for traps and bonuses of the game. Position 3 is a mine, you die and return to 0 position.
            if (NewPosP2 == 3) {
                NewPosP2 = 0;
                StdOut.println();
                StdOut.println(p2 + " you got a mine. You are getting back to the position " + NewPosP2);
            }

            // If statements for traps and bonuses of the game. Position 26 is a canon, you die and return to 16 position.
            if (NewPosP2 == 26) {
                NewPosP2 = 16;
                StdOut.println();
                StdOut.println(p2 + " you got a mine. You are getting back to the position " + NewPosP2);
            }

            // If statements for traps and bonuses of the game. Position 12 is a bonus, you will be in position 17.
            if (NewPosP2 == 12) {
                NewPosP2 = 17;
                StdOut.println();
                StdOut.println(p2 + " you got a bonus. You are getting to the position " + NewPosP2);
            }


            // If statement that determines who is the winner.
            if (NewPosP2 >= 40) {
                break;
            }


            // Third player's turn**************************************
            int random_integer3 = rand.nextInt(6 - 1) + 1;
            // currentPosP1 = random_integer;
            int c = random_integer3;
            NewPosP3 = NewPosP3 + c;
            StdOut.println();
            StdOut.println(p3 + " throws dices, your number is " + c);
            StdOut.println();
            StdOut.println(p3 + " position is " + NewPosP3);

            // If statements for traps and bonuses of the game. Position 3 is a mine, you die and return to 0 position.
            if (NewPosP3 == 3) {
                NewPosP3 = 0;
                StdOut.println();
                StdOut.println(p3 + " you got a mine. You are getting back to the position " + NewPosP3);
            }

            // If statements for traps and bonuses of the game. Position 26 is a canon, you die and return to 16 position.
            if (NewPosP3 == 26) {
                NewPosP3 = 16;
                StdOut.println();
                StdOut.println(p3 + " you got a mine. You are getting back to the position " + NewPosP3);
            }

            // If statements for traps and bonuses of the game. Position 12 is a bonus, you will be in position 17.
            if (NewPosP3 == 12) {
                NewPosP3 = 17;
                StdOut.println();
                StdOut.println(p3 + " you got a bonus. You are getting to the position " + NewPosP3);
            }

            // If statement that determines who is a winner.
            if (NewPosP3 >= 40) {
                break;
            }


            // Fourth player's turn*******************************************8
            int random_integer4 = rand.nextInt(6 - 1) + 1;
            // currentPosP1 = random_integer;
            int d = random_integer4;
            NewPosP4 = NewPosP4 + d;

            StdOut.println();
            StdOut.println(p4 + " throws dices, your number is " + d);
            StdOut.println();
            StdOut.println(p4 + " position is " + NewPosP4);

            // If statements for traps and bonuses of the game. Position 3 is a mine, you die and return to 0 position.
            if (NewPosP4 == 3) {
                NewPosP4 = 0;
                StdOut.println();
                StdOut.println(p4 + " you got a mine. You are getting back to the position " + NewPosP1);
            }

            // If statements for traps and bonuses of the game. Position 26 is a canon, you die and return to 16 position.
            if (NewPosP4 == 26) {
                NewPosP4 = 16;
                StdOut.println();
                StdOut.println(p4 + " you got a mine. You are getting back to the position " + NewPosP4);
            }

            // If statements for traps and bonuses of the game. Position 12 is a bonus, you will be in position 17.
            if (NewPosP4 == 12) {
                NewPosP4 = 17;
                StdOut.println();
                StdOut.println(p4 + " you got a bonus. You are getting to the position " + NewPosP4);
            }

            // If statement that determines who is a winner.
            if (NewPosP4 >= 40) {
                break;
            }


            // if (NewPosP1 >= 40 || NewPosP2 >= 40 || NewPosP3 >= 40 || NewPosP4 >= 40) {
            //   break;
            //}
        }
        while (NewPosP1 < 40 || NewPosP2 < 40 || NewPosP3 < 40 || NewPosP4 < 40);


        // If statements that detects who is a winner.
        if (NewPosP1 > NewPosP2 && NewPosP1 > NewPosP3 && NewPosP1 > NewPosP4) {
            StdOut.println();
            StdOut.println("******************************");
            StdOut.println("The winner is " + p1);
            StdOut.println("******************************");
        }
        if (NewPosP2 > NewPosP1 && NewPosP2 > NewPosP3 && NewPosP2 > NewPosP4) {
            StdOut.println();
            StdOut.println("******************************");
            StdOut.println("The winner is " + p2);
            StdOut.println("******************************");
        }
        if (NewPosP3 > NewPosP1 && NewPosP3 > NewPosP2 && NewPosP3 > NewPosP4) {
            StdOut.println();
            StdOut.println("******************************");
            StdOut.println("The winner is " + p3);
            StdOut.println("******************************");
        }

        if (NewPosP4 > NewPosP1 && NewPosP4 > NewPosP2 && NewPosP4 > NewPosP3) {
            StdOut.println();
            StdOut.println("******************************");
            StdOut.println("The winner is " + p4);
            StdOut.println("******************************");
        }
    } // This is the end of main method.

}

