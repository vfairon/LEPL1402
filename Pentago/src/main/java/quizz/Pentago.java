package basics;

/**
 * A class that represents a game of Pentago.
 *
 * The rules of the game are described in this short video
 * that we advise you to watch now.
 *   [FR] https://youtu.be/bpkpMxqDmjw
 *   [EN] https://youtu.be/VX6-n1Wm5zI?si=QSLC2auuy7Q1DXFZ
 *
 * Pentago is a two-player game on a 6x6 grid board composed of four
 * 3x3 subparts that can be rotated left or right. The board looks
 * like this:
 *
 * ------------------------------
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * ------------------------------
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * |  x   x   x  |  x   x   x   |
 * ------------------------------
 *
 * with the four subparts being:
 *
 * --------------------------------
 * |              |               |
 * |   TOP_LEFT   |   TOP_RIGHT   |
 * |              |               |
 * -------------------------------
 * |              |               |
 * | BOTTOM_LEFT  | BOTTOM_RIGHT  |
 * |              |               |
 * -------------------------------
 *
 * Two players A and B take turns. Each turn is composed of 2 successive actions:
 * 1) the player places one of his token on the board in a free position, then
 * 2) the player rotates by 90° or -90° one subpart of the board of his choice.
 *
 * A player wins when he has 5 consecutive own tokens in a row, a
 * column, or a diagonal.
 *
 * Your task is to model the game, by implementing:
 * 1) the main method "play()" that registers the move of the player, and
 * 2) the helper methods "rotateMatrix()", "checkWinPlayerVector()" and "checkWinPlayer()".
 *
 * The 3 helper methods are supposed to help you build the code for
 * the "play()" method.  Therefore, you should implement the helper
 * methods first. The helper methods are graded separately.
 *
 * We also provide the "isGridFilled()" helper method that can be
 * useful in your "play()" method.
 *
 * Do not modify the enumerations, the method signatures, or the
 * instance variables. Even if this is not required to succeed this
 * quiz, you can possibly add new methods and import Java classes.
 *
 * NOTE: If you have a question, you can ask it on the following Teams channel:
 * https://teams.microsoft.com/l/channel/19%3adaf32f5af520411ca9562ddbe76e51a7%40thread.tacv2/Quiz?groupId=0a375f0f-8e80-4903-8534-d6f40cc4b69d&tenantId=7ab090d4-fa2e-4ecf-bc7c-4127b4d582ec
 * WARNING: Be careful not to copy your any part of your source code in this channel!
 */
public class Pentago {
    /**
     * An enum that represents the two players playing the game.
     */
    public enum Player {
        A,
        B
    }

    /**
     * An enum that represents the different outcomes of the game.
     * A_WINS is used when player A wins, B_WINS is used when player B wins, and
     * NO_WINNER is used when there is no winner yet, but also when
     * the board is full (which means that there is no winner) or when both
     * players win at the same time.
     */
    public enum Winner {
        A_WINS,
        B_WINS,
        NO_WINNER
    }

    /**
     * An enum that represents the four subparts of the board:
     * --------------------------------
     * |              |               |
     * |   TOP_LEFT   |   TOP_RIGHT   |
     * |              |               |
     * --------------------------------
     * |              |               |
     * | BOTTOM_LEFT  | BOTTOM_RIGHT  |
     * |              |               |
     * --------------------------------
     */
    public enum BoardSubpart {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    /**
     * An enum that represents the direction of the rotation applied on a subpart.
     */
    public enum RotationDirection {
        LEFT,
        RIGHT
    }

    Player[][] board;



    private static final int BOARD_SIZE = 6;
    private static final int SUBPART_SIZE = 3;

    public Pentago() {
        this.board = new Player[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * A helper method that verifies whether the grid is completely filled by player tokens, or not.
     *
     * @return true if the board is full and false otherwise
     */
    private boolean isGridFilled() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (this.board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Implementation of the two steps move and computation of the winner:
     * 1) Plays a piece in the given position for the given player, then
     * 2) Rotates the given subpart in the given direction.
     *
     * The method returns the winner (A_WINS / B_WINS) if only one player is in a victory state.
     * Otherwise, NO_WINNER is returned. It means that NO_WINNER is also returned if both players
     * have aligned 5 pieces in a row, column or diagonal.
     *
     * <p>
     * The methods you should have implemented before are "rotateMatrix", "checkWinPlayerVector" and "checkWinPlayer"
     * Those methods should help you build the code for the play method.
     * Notice that rotating a matrix to the left three times is equivalent to rotating it once the right. 💡
     *
     * @param i         the row index
     * @param j         the column index
     * @param player    the player (A or B)
     * @param subpart   the subpart to rotate by +- 90°
     * @param direction the direction to rotate the subpart (LEFT for -90° or RIGHT for +90°)
     * @throws IllegalArgumentException if i or j is not a valid index or if the position is not empty
     * @return Winner   the winning status of the game after the play (cf. above)
     */
    public Winner play(int i, int j, Player player, BoardSubpart subpart, RotationDirection direction) {
        if (j>=BOARD_SIZE || j < 0 || i >= BOARD_SIZE|| i < 0){
            throw new IllegalArgumentException("Something is wrong ;-)");
        }
        if( board[i][j] != null){
            throw new IllegalArgumentException("Something is wrong ;-)");
        }

        //* 1) Plays a piece in the given position for the given player
        board[i][j] = player;

        //Rotates the given subpart in the given direction.
        Player[][] matrixtorotate = new Player[SUBPART_SIZE][SUBPART_SIZE];

        int addtovert = 0;
        int addtohoriz = 0;

        if (subpart == BoardSubpart.TOP_RIGHT){
            addtohoriz += SUBPART_SIZE;
        }
        if (subpart == BoardSubpart.BOTTOM_LEFT){
            addtovert += SUBPART_SIZE;
        }
        if (subpart == BoardSubpart.BOTTOM_RIGHT){
            addtovert += SUBPART_SIZE;
            addtohoriz += SUBPART_SIZE;
        }


        for (int k = 0; k <  SUBPART_SIZE; k++) {
            for (int l = 0; l <  SUBPART_SIZE; l++) {
                matrixtorotate[k][l] = board[k+addtovert][l+addtohoriz];
            }
        }

        if (direction == RotationDirection.RIGHT){
            matrixtorotate = rotateMatrix(matrixtorotate);
        }
        else{
            matrixtorotate = rotateMatrix(matrixtorotate);
            matrixtorotate = rotateMatrix(matrixtorotate);
            matrixtorotate = rotateMatrix(matrixtorotate);

        }
        // on la refout dans plateau

        for (int k = 0; k <  SUBPART_SIZE; k++) {
            for (int l = 0; l <  SUBPART_SIZE; l++) {
                board[k+addtovert][l+addtohoriz] = matrixtorotate[k][l];
            }
        }




        boolean awin = checkWinPlayer(Player.A);
        boolean bwin = checkWinPlayer(player.B);

        if(awin && !bwin){
            return Winner.A_WINS;
        }
        else if (bwin && !awin) {
            return Winner.B_WINS;
        }


        return Winner.NO_WINNER; // add your own code here
    }



    /**
     * Rotate the given matrix 90° to the right (i.e., clockwise).
     *
     * Beware that the matrix may have an arbitrary n x m shape (i.e.,
     * do NOT assume that the matrix has the 3 x 3 shape of the
     * subparts of the Pentago game).
     *
     * @param matrix   the n x m matrix to rotate
     * @return A version of the input matrix rotated to the right having thus an m x n shape.
     *         The input matrix is left unchanged.
     */

    public Player[][] rotateMatrix(Player[][] matrix) {
        Player[][] toreturn = new Player[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                toreturn[j][matrix.length - i - 1] = matrix[i][j];

            }

        }
         return toreturn;
    }

    /**
     * Returns if five consecutive positions of the given player are present in the array.
     * This  array can represent a row, a column or a diagonal of the board.
     *
     * @param vector    An array.
     * @param player    The player for which we want to find the possible win.
     * @return true if and only if the given player has five consecutive positions in the array.
     */
    public boolean checkWinPlayer(Player[] vector, Player player){
        if (vector.length < 5){
            return false;
        }

        for (int i = 0; i <= vector.length - 5; i++) {
            boolean bool = true;
            for (int j = 0; j < 5; j++) {
                if(vector[i+j] != player){
                    bool = false;

                }
            }
            if (bool == true){
                return true;
            }

        }
         return false;
    }

    /**
     * Indicates if the given player has won.
     *
     * @return true if the given player has won and false otherwise.
     */
    private boolean checkWinPlayer(Player player) {
        //Checking for horizontal win :
        for (int i = 0; i < board.length; i++) {
            if (checkWinPlayer(board[i], player)){
                return true;
            }
        }


        //Checking for vertical win :
        for (int i = 0; i < board[0].length; i++) { //horizontal
            Player[] array = new Player[board.length];
            for (int j = 0; j < board.length; j++) {
                array[j] = board[j][i];
            }
            if(checkWinPlayer(array,player)){
                return true;
            }
        }


        //checking for diagonal win :
        //Only six different diagonals :


        //Vertical 3 Downwards
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                Player[] array = new Player[6];
                for (int j = 0; j < 6 ; j++) {
                    array [j] = board[j][j];
                }
                if (checkWinPlayer(array,player)){
                    return true;
                }
            }
            else {
                //[0,1]
                Player[] array = new Player[5];
                for (int j = 0; j < 5 ; j++) {
                    array [j] = board[j][1+j];
                }
                if (checkWinPlayer(array,player)){
                    return true;
                }

                //[0,1]
                Player[] array1 = new Player[5];
                for (int j = 0; j < 5 ; j++) {
                    array1 [j] = board[j+1][j];
                }
                if (checkWinPlayer(array1,player)){
                    return true;
                }

            }
        }

        //Upwards 3

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                //[5,0]
                Player[] array = new Player[6];
                for (int j = 0; j < 6 ; j++) {
                    array [j] = board[5-j][j];
                }
                if (checkWinPlayer(array,player)){
                    return true;
                }
            }
            else {
                //[4,0]
                Player[] array = new Player[5];
                for (int j = 0; j < 5 ; j++) {
                    array [j] = board[4-j][j];
                }
                if (checkWinPlayer(array,player)){
                    return true;
                }

                //[5,1]
                Player[] array1 = new Player[5];
                for (int j = 0; j < 5 ; j++) {
                    array1 [j] = board[5-j][1+j];
                }
                if (checkWinPlayer(array1,player)){
                    return true;
                }

            }
        }



         return false;
    }
}
