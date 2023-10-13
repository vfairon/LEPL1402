package complexity;

public class MaximumSumSubarray {

    /**
     * Class representing a sub-array in an array. It is defined by the start
     * and end position (both inclusive) of the sub-array in the array
     */
    public static class ArrayIndex {
        int start;
        int end;

        public ArrayIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof ArrayIndex) {
                ArrayIndex o = (ArrayIndex) other;
                return o.start == this.start && o.end == this.end;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.start, this.end);
        }
    }

    /**
     * Finds the contiguous sub-array for which the sum of its elements is maximal.
     * If multiple sub-arrays have the same maximal sum, returns the one that starts at
     * the lowest index.
     * <p>
     * For example, in the sub-array [100,100,-1,-1000000,-1,300,10,13,-2], the methods returns
     * (4, 5).
     *
     * @param array A non-empty array filled with non-zero integers (which might be negative)
     * @return The position of the array for which the sum of its element is maximal (if there
     * is a tie, the one that starts the earliest is returned)
     */
        public static ArrayIndex maximumSumSubarray(int[] array) {
            int max_ending_here = 0;
            int max_previous = 0;
            int _start = 0;
            int start = 0;
            int end = -1;

            for(int i=0; i<array.length; i++) {
                max_ending_here += array[i];

                if(max_ending_here<0){
                    _start = i+1;
                    max_ending_here = 0;
                }
                if (max_ending_here > max_previous){
                    end = i;
                    start = _start;
                    max_previous = max_ending_here;
                }
            }
            return new ArrayIndex(start,end);
        }

}
