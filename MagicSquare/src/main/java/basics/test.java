package basics;
import java.util.Arrays;
public class test {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        int[][] magic = new int[][]
                {{2, 7, 6},
                        {9, 5, 1},
                        {4, 3, 8}};
        System.out.println(isMagicSquare(magic));
    }
    public static boolean isMagicSquare(int [][] matrix) {

        int len = matrix.length;
        int sum = 0;
        int row = 0;

        int[] already = new int[(len)* (len)];


        boolean result = true;

        for (int i = 0; i< len; i++) {
            sum = 0;
            for (int j = 0; j< len; j++){

                // adding unprecedented values & check if > n
                int target_value = matrix[i][j];
                if (!Arrays.stream(already).anyMatch(x -> x == target_value)){
                    already[i*(len)+j] = matrix[i][j];
                }
                if (target_value>len*len + 1){
                    System.out.println(target_value);
                    return false;
                }
                sum += target_value;

            }
            if(sum != row && i != 0){
                return false;
            }
            row = sum;


            //Checking if all values from 1 to n are there :
        }
        if(already[len*len-1]==0){
            return false;
        }


        return result;
    }

}
