package tictactoe;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);

        char[] gameSymb = {'_', 'O', 'X' };
        char [] lineDispSymb = {' ', '|', '\n'};
        char [] borderDispSymb = {'-'};

        byte colCount = 3;
        byte lineCount = 3;
        // byte borderDispSymbCount =  (colCount + 1) *2 + 1 // last char lacks space



        // fill gameBoard with gameInput values
        // default filled with 0s which is gameSymb[0] == '_'
        byte[][] gameBoard = new byte[lineCount][colCount];

        byte playerTurn = 2; // starts at X, alternates X = 2 or O = 1;
         String[] gameStateText = {"Parsing", "Game not finished", "Draw", "X wins", "O wins", "Impossible"};
        int gameState = 0; // Parsing

do {

    // BEGIN Display the game board

    // display top border
    for (int i = 0; i < (colCount + 1) * 2; i++) {
        System.out.print(borderDispSymb[0]);
    }
    System.out.println(borderDispSymb[0]);


    for (int l = 0; l < lineCount; l++) {
        System.out.printf("%c%c", lineDispSymb[1], lineDispSymb[0]);

        for (int c = 0; c < colCount; c++) {
            System.out.printf("%c%c", gameSymb[gameBoard[l][c]], lineDispSymb[0]);
        }
        System.out.printf("%c%c", lineDispSymb[1], lineDispSymb[2]);
    }


    // display bottom border
    for (int i = 0; i < (colCount + 1) * 2; i++) {
        System.out.print(borderDispSymb[0]);
    }
    System.out.println(borderDispSymb[0]);

        // END Display the game board



    /////////////////////////////////////////////////////////////
    // Enter user's move
    ////////////////////////////////////////////////////////////


    // input game state
    int lineInput = 0;
    int colInput = 0;
    boolean isGameInputInvalid ;
    do {
        isGameInputInvalid = false;

        // check if gameBoard is now full
//            boolean gameBoardIsFull = true;
//            for (int l = 0; l < lineCount; l++) {
//                for (int c = 0; c < colCount; c++) {
//                    if (gameBoard[l][c] == 0) {
//                        gameBoardIsFull = false;
//                    }
//                }
//            }
//            if (gameBoardIsFull) {
//                break;
//            }

        // scan user's input
        System.out.print("Enter the coordinates: ");

        try {
            lineInput = scanner.nextInt();
            colInput = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You should enter numbers!");
            scanner.nextLine();
        }


        if (lineInput != (int) lineInput || colInput != (int) colInput) {
            // simplistic validation here
            isGameInputInvalid = true;
            System.out.println("You should enter numbers!");
            continue;
        }

        if (lineInput > 3 || lineInput < 1 || colInput > 3 || colInput < 1) {
            // simplistic validation here
            isGameInputInvalid = true;
            System.out.println("Coordinates should be from 1 to 3!");
            continue;
        }

        if (gameBoard[lineInput - 1][colInput - 1] != 0) {
            // simplistic validation here
            isGameInputInvalid = true;
            System.out.println("This cell is occupied! Choose another one!");
            continue;
        }


    } while (isGameInputInvalid);

    // Enter  user input on the board
    gameBoard[lineInput - 1][colInput - 1] = playerTurn;
    playerTurn = playerTurn == (byte)2 ? (byte)1 : (byte)2;
//        gameBoard[colInput][3-lineInput] = 2; // X


          ///////////////////////////////////////////////////////////////
        // Probe game state
        //////////////////////////////////////////////////////////////

        boolean isOAWinner = false;
        boolean isXAWinner = false;
        boolean isThereEmptyCells = false;
        boolean isImpossibleGame = false;

//        //count Xs and Os to check if game state impossible
//            int XCount = 0;
//            int OCount = 0;
//            for (int l = 0; l < lineCount; l++) {
//                for (int c = 0; c < colCount; c++) {
//                    if (gameBoard[l][c] == 1) {
//                        OCount++;
//                    }
//                     if (gameBoard[l][c] == 2) {
//                        XCount++;
//                    }
//                }
//            }
////        System.out.printf("%d - %d", XCount, OCount);
//            if (XCount - OCount > 1 || OCount - XCount > 1) {
//                isImpossibleGame = true;
//            }

         // scan horizontally for same symbol
            for (int l = 0; l < lineCount; l++) {
                for (int c = 0; c < colCount; c++) {
                    if (gameBoard[l][c] == 0) {
                        isThereEmptyCells = true;
                    }
                }
                    if (gameBoard[l][0] == 1 && gameBoard[l][1] == 1 && gameBoard[l][2] == 1 ) {
                        isOAWinner = true;
                    }

                     if (gameBoard[l][0] == 2 && gameBoard[l][1] == 2 && gameBoard[l][2] == 2 ) {
                        isXAWinner = true;
                    }
            }

             // scan vertically for same symbol
            for (int c = 0; c < colCount; c++) {
                for (int l = 0; l < lineCount; l++) {
                    if (gameBoard[l][c] == 0) {
                        isThereEmptyCells = true;
                    }
                }
                    if (gameBoard[0][c] == 1 && gameBoard[1][c] == 1 && gameBoard[2][c] == 1 ) {
                        isOAWinner = true;
                    }

                     if (gameBoard[0][c] == 2 && gameBoard[1][c] == 2 && gameBoard[2][c] == 2 ) {
                        isXAWinner = true;
                    }
            }

                // scan diagonally for same symbol
             for (int l = 0; l < lineCount; l++) {

                 if (gameBoard[l][l] == 0 || gameBoard[l][lineCount - 1 - l] == 0) {
                     isThereEmptyCells = true;
                 }
             }

                    if (gameBoard[0][0] == 1 && gameBoard[1][1] == 1 && gameBoard[2][2] == 1 ) {
                        isOAWinner = true;
                    }

                     if (gameBoard[0][lineCount - 1] == 1 && gameBoard[1][lineCount - 2] == 1 && gameBoard[2][lineCount - 3] == 1 ) {
                        isOAWinner = true;
                    }

                     if (gameBoard[0][0] == 2 && gameBoard[1][1] == 2 && gameBoard[2][2] == 2 ) {
                        isXAWinner = true;
                    }

                       if (gameBoard[0][lineCount - 1] == 2 && gameBoard[1][lineCount - 2] == 2 && gameBoard[2][lineCount - 3] == 2 ) {
                        isXAWinner = true;
                    }

        ///////////////////////////////////////////////////////////////
        // Evaluate game state
        //////////////////////////////////////////////////////////////


        if (!isOAWinner && !isXAWinner && isThereEmptyCells && !isImpossibleGame) {
            gameState = 1;
        }

        if (!isOAWinner && !isXAWinner && !isThereEmptyCells) {
            gameState = 2;
        }

        if (!isOAWinner && isXAWinner) {
            gameState = 3;
        }

        if (isOAWinner && !isXAWinner) {
            gameState = 4;
        }

        if ((isOAWinner && isXAWinner) || isImpossibleGame) {
            gameState = 5;
        }


        /// end evaluate game state


    } while (gameState != 2 && gameState != 3 && gameState != 4); // do game loop

          // BEGIN Display the game board

    // display top border
    for (int i = 0; i < (colCount + 1) * 2; i++) {
        System.out.print(borderDispSymb[0]);
    }
    System.out.println(borderDispSymb[0]);


    for (int l = 0; l < lineCount; l++) {
        System.out.printf("%c%c", lineDispSymb[1], lineDispSymb[0]);

        for (int c = 0; c < colCount; c++) {
            System.out.printf("%c%c", gameSymb[gameBoard[l][c]], lineDispSymb[0]);
        }
        System.out.printf("%c%c", lineDispSymb[1], lineDispSymb[2]);
    }


    // display bottom border
    for (int i = 0; i < (colCount + 1) * 2; i++) {
        System.out.print(borderDispSymb[0]);
    }
    System.out.println(borderDispSymb[0]);

        // END Display the game board
        System.out.println(gameStateText[gameState]);



    } // main
} // Main
