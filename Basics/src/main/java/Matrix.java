package basics;

public class Matrix {

    /**
     * Create a matrix from a String.
     *
     * The string if formatted as follow:
     *  - Each row of the matrix is separated by a newline
     *    character \n
     *  - Each element of the rows are separated by a space
     *
     *  @param s The input String
     *  @return The matrix represented by the String
     */
    public static int[][] buildFrom(String s) {
        String[] temp = s.split("\n");
        int[][] to_return = new int[temp.length][temp[0].split(" ").length];

        for (int i =0; i< temp.length; i++){
            String[] line_string = temp[i].split(" ");
            int[] line_int = new int[line_string.length];

            for(int j = 0; j< line_string.length; j++){
                line_int[j] = Integer.valueOf(line_string[j]);
            }
            to_return[i] = line_int;
        }
        return to_return;
    }


    /**
     * Compute the sum of the element in a matrix
     *
     * @param matrix The input matrix
     * @return The sum of the element in matrix
     */
    public static int sum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    /**
     * Compute the transpose of a matrix
     *
     * @param matrix The input matrix
     * @return A new matrix that is the transpose of matrix
     */
    public static int[][] transpose(int[][] matrix) {
        int[][] transposed = new int[matrix[0].length][matrix.length];

        for (int i = 0; i< matrix.length; i++){

            for (int j = 0; j<matrix[0].length; j++){
                transposed[i][j] = matrix[j][i];
            }
        }
         return transposed;
    }

    /**
     * Compute the product of two matrix
     *
     * @param matrix1 A n x m matrix
     * @param matrix2 A m x k matrix
     * @return The n x k matrix product of matrix1 and matrix2
     */
    public static int[][] product(int[][] matrix1, int[][] matrix2) {
        int[][] product = new int[matrix1.length][matrix2[0].length];
        for (int n = 0; n < matrix1.length; n ++){
            for (int k = 0; k < matrix2[0].length; k ++){
                int sum = 0;
                for (int m = 0; m< matrix2.length; m ++){
                    sum += matrix1[n][m] * matrix2[m][k];
                }
                product[n][k] = sum;
            }
        }
         return product;
    }
}