package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

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

        // input game state
        boolean isGameInputInvalid = true;
        String gameInput = "";
        while (isGameInputInvalid) {
            System.out.print("Enter cells: ");
            gameInput = scanner.nextLine();

            if (gameInput.length() == colCount * lineCount) {
                // simplistic validation here
                isGameInputInvalid = false;
            }
        }

        // fill gameBoard with gameInput values
        // default filled with 0s which is gameSymb[0] == '_'
        byte[][] gameBoard = new byte[lineCount][colCount];
        for (int l = 0; l < lineCount; l++) {
            for (int c = 0; c < colCount; c++) {
                if (gameInput.charAt(c + l*colCount) == gameSymb[0]) {
                    gameBoard[l][c] = 0;
                } else  if (gameInput.charAt(c + l*colCount) == gameSymb[1]) {
                    gameBoard[l][c] = 1;
                } else  if (gameInput.charAt(c + l*colCount) == gameSymb[2]) {
                    gameBoard[l][c] = 2;
                } else {
                    System.out.println("Game Input error unrecognized symbol");
                }
            }
        }


        // Display the game board

        // display top border
        for (int i = 0 ; i < (colCount + 1) *2; i++ ) {
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
        for (int i = 0 ; i < (colCount + 1) *2; i++ ) {
            System.out.print(borderDispSymb[0]);
        }
            System.out.println(borderDispSymb[0]);


          ///////////////////////////////////////////////////////////////
        // Probe game state
        //////////////////////////////////////////////////////////////


        boolean isOAWinner = false;
        boolean isXAWinner = false;
        boolean isThereEmptyCells = false;
        boolean isImpossibleGame = false;

        //count Xs and Os to check if game state impossible
            int XCount = 0;
            int OCount = 0;
            for (int l = 0; l < lineCount; l++) {
                for (int c = 0; c < colCount; c++) {
                    if (gameBoard[l][c] == 1) {
                        OCount++;
                    }
                     if (gameBoard[l][c] == 2) {
                        XCount++;
                    }
                }
            }
//        System.out.printf("%d - %d", XCount, OCount);
            if (XCount - OCount > 1 || OCount - XCount > 1) {
                isImpossibleGame = true;
            }

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
        String[] gameStateText = {"Parsing", "Game not finished", "Draw", "X wins", "O wins", "Impossible"};
        int gameState = 0; // Parsing

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

        System.out.println(gameStateText[gameState]);

    }
}
