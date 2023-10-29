package basics;

/**
 * A class that represents a game of Four in a Row.
 * The game is played on a 6x7 board.
 * A player wins when he has 4 pieces in a row, column or diagonal.
 *
 * ForInARow is a two-player connection rack game, in which the players choose a color and
 * then take turns dropping colored tokens into a six-row, seven-column vertically suspended grid.
 * The pieces fall straight down, occupying the lowest available space within the column.
 *
 * The objective of the game is to be the first to form a horizontal,
 * vertical, or diagonal line of four of one's own tokens.
 *
 * Your taks is to model the game and implement the method hasWon(char player) that returns true.
 * if the player has won the game.
 * We advise you to model the state of the game with an internal 2D array of char.
 */
public class FourInARow {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private static final char EMPTY = '-';
    private static final char[] PLAYERS = {'X', 'O'};

    char[][] board = new char[6][7];




     // add your own instance variables here

    public FourInARow() {
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++) {
                board[i][j] = EMPTY;

            }
        }

    }

    /**
     * Play a piece in column j for the given player.
     * @param j the column index
     * @param player the player (X or O)
     * @throws IllegalArgumentException if j is not a valid column index or if the column is full or if the player is not X or O
     */
    public void play(int j, char player) {
        if (j>7 || j<0 || board[0][j] != EMPTY || (player != PLAYERS[0] && player != PLAYERS[1])){
            throw new IllegalArgumentException("Argument not valid");
        }
        for (int i = 5; i > -1; i--) {
            System.out.println(board[5][0]);
            if (board[i][j] == EMPTY){
                board[i][j] = player;
                break;
            }
        }
    }


    /**
     * Returns true if the player has won the game.
     * @param player the player (X or O)
     * @return true if the player has won the game
     * @throws IllegalArgumentException if the player is not X or O
     */
    public boolean hasWon(char player) {
        if ((player != PLAYERS[0] && player != PLAYERS[1])){
            throw new IllegalArgumentException("Argument not valid");
        }

        //For every point in the field we will check (if possible) if there are 4 consecutive in all directions (right; down and diagonal)

        for (int i =0; i <7; i++){//horizontal
            for (int j = 0; j < 6; j++) {//vertical
                //checking horizontally
                if (i<=3){
                    boolean subcheck = true;
                    for (int k = 0; k < 3; k++) {
                        if (board[j][k+i] != player){
                            subcheck = false;
                        }
                    }
                    if (subcheck){
                        return true;
                    }
                }

                //checking vertically
                if (j<=2){
                    boolean subcheck = true;
                    for (int k = 0; k < 3; k++) {
                        if (board[j+k][i] != player){
                            subcheck = false;
                        }
                    }
                    if (subcheck){
                        return true;
                    }
                }

                //checking diagnonally
                if (i+4<= 7 && j+4<= 6){
                    boolean subcheck = true;
                    for (int k = 0; k < 3; k++) {
                        if (board[j+k][i+k] != player){
                            subcheck = false;
                        }
                    }
                    if (subcheck){
                        return true;
                    }
                }


            }
        }



        return false;
    }
}
