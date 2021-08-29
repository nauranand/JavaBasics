package tictactoe;
import java.util.Scanner;

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
        
        // display the game board
        
        
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


    }
}
