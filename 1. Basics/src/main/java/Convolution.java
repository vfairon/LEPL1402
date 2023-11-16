package basics;

public class Convolution {

    /**
     *
     * @param input is a n1 x m1 non-null rectangular matrix with at least 3 rows and 3 cols
     * @param kernel is a 3 x 3 square matrix
     * @return a matrix M with dimension (n1-2) x (m1-2) with
     *         M[i][j] = sum_{k in 0..2, l in 0..2} input[i+k][j+l]*kernel[k][l]
     */
    public static int [][] convolution(int [][] input, int [][] kernel) {
        int n = input.length;
        int m = input[0].length;
        int sum = 0;

        int[][] M = new int[n-2][m-2];
        //Iterating untill M matrix cannot hold anymore (if n = 7, n-2 = 5 elements here goes from 0->4 so ok)
        for (int i = 0; i<n-2;i++){
            for (int j = 0 ; j<m-2 ; j++){
                for (int k=0; k < 3; k++){
                    for (int l=0; l<3; l++){
                        sum += input[i+k][j+l]*kernel[k][l];
                    }
                }
                M[i][j] = sum;
                sum = 0;
            }
        }
        return M;
    }
}
