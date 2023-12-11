package parallelization;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        int row;
        List<Integer> columns;



        public void setColumns(List<Integer> c){
            columns = c;
        }
        public void setrow(int r){
            row = r;
        }

        public int getRow(){
            return row;
        }
        public List<Integer> getColumns(){
            return columns;
        }

        public Result merge(Result with){
            if (getRow() == -1){
                columns = with.getColumns();
                row = with.getRow();
            }
            else {
                if (columns.size() < with.getColumns().size()){
                    columns = with.getColumns();
                    row = with.getRow();
                }
            }
            return this;
        }
    }

    public static Result FindInRow(int[] row, int value, int row_index){
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i =0; i< row.length;i++){
            if (row[i] == value){
                indexes.add(i);
            }
        }
        Result res = new Result();
        res.setColumns(indexes);
        res.setrow(row_index);
        return res;

    }

    /**
     * This method finds the row that contains the most number of occurrences of a
     * certain value and returns the row index of that row and the column indexes
     * (in increasing order) where the value occurs in that row.
     * The matrix is represented by a two-dimensional array.
     *
     * Example: if the method is called with the value "3" and the following matrix
     *     (1   3  2  -4)          // <- there is a "3" in column 1
     *     (-3  4  5  -2)
     *     (3   0  3   2)          // <- there is a "3" in column 0 and column 2
     * then the result of the search is:
     *      row=2 , columns=[0,2]
     * because row 2 contains the most number of occurrences of "3" (in columns 0 and 2).
     *
     * Your solution MUST use a thread pool to accelerate the operation.
     *
     * @param matrix a rectangular matrix
     * @param value the value to find
     * @param poolSize the thread pool size
     * @return the result of the search
     *
     * You can assume that there is exactly one row in the matrix with the
     * most number of occurrences of the value.
     * Catch exceptions and ignore them.
     */

    public static Result findValue(int[][] matrix, int value, int poolSize) {

        Result res_final = new Result();
        res_final.setrow(-1);


        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        Stack<Future<Result>> stack_futurs = new Stack<Future<Result>>();
        for (int i =0; i < matrix.length; i++){
            int finalI = i;
            stack_futurs.add(executor.submit(() ->FindInRow(matrix[finalI],value, finalI)));
        }

        while(!stack_futurs.empty()){
            try {
                Result res = stack_futurs.pop().get();
                res_final = res_final.merge(res);
                System.out.println(res_final.getColumns());


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

        // TODO
        // Hint:
        // One row of the matrix -> One future.

         return res_final;
    }
}
