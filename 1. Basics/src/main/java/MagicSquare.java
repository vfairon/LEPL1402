package basics;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.Arrays;

public class MagicSquare {


    /**
     * A magic square is an (n x n) matrix such that:
     *
     * - all the positive numbers 1,2, ..., n*n are present (thus each number appears exactly once)
     * - the sums of the numbers in each row, each column and both main diagonals are the same
     *
     *   For instance a 3 x 3 magic square is the following
     *
     *   2 7 6
     *   9 5 1
     *   4 3 8
     *
     *   You have to implement the method that verifies if a matrix is a valid magic square
     */

    /**
     *
     * @param matrix a square matrix of size n x n
     * @return true if matrix is a n x n magic square, false otherwise
     */
    public static boolean isMagicSquare(int [][] matrix) {

        int len = matrix.length;
        int sum_h = 0;
        int sum_v = 0;
        int sumMainDiagonal = 0;
        int sumSecondaryDiagonal = 0;

        int row = 0;
        int column = 0;



        int[] already = new int[(len)* (len)];


        boolean result = true;

        for (int i = 0; i< len; i++) {
            sum_h = 0;
            sum_v = 0;

            sumMainDiagonal += matrix[i][i];
            sumSecondaryDiagonal += matrix[i][len - 1 - i];

            for (int j = 0; j< len; j++){

                // adding unprecedented values & check if > n
                int target_value = matrix[i][j];
                int target_value_vertical = matrix[i][j];

                if (!Arrays.stream(already).anyMatch(x -> x == target_value)){
                    already[i*(len)+j] = matrix[i][j];
                }
                if (target_value>len*len){
                    System.out.println(target_value);
                    return false;
                }
                sum_h += target_value;
                sum_v += target_value_vertical;


            }
            //Check if the raw sum is ok with previous and raw = column (if i = 0 well its the first so ok)
            if(sum_h != row && i != 0 || sum_h != sum_v){
                return false;
            }
            // same here
            if(sum_v != column && i != 0){
                return false;
            }


            // updating raw and column
            row = sum_h;
            column = sum_v;


            //Checking if all values from 1 to n are there :
        }
        Arrays.sort(already);
        if(already[len*len-1]<len*len){
            return false;
        }
        if (sumMainDiagonal != sumSecondaryDiagonal || sumMainDiagonal != row || sumSecondaryDiagonal != row){
            return false;
        }

        return result;
    }
}
