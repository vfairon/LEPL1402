    package complexity;

    public class KnapsackBruteForce {

        public static void main(String[] args) {
            Item[] items = {
                    new Item(60, 10),
                    new Item(100, 20),
                    new Item(120, 30)
            };
            int capacity = 50;

            int maxValue = knapsack(items, capacity);
            System.out.println("Maximum value: " + maxValue);
        }

        static class Item {
            int value;
            int weight;

            Item(int value, int weight) {
                this.value = value;
                this.weight = weight;
            }
        }

        /**
         * Returns the maximum value that can be put in a knapsack with the given capacity.
         * Each item can only be selected once. If you pack an item it consumes its weight in the capacity
         * Your algorithm should implement a brute-force appraoch with a time comlexity
         * of O(2^n) where n is the number of items.
         * @param items
         * @param capacity
         * @return
         */


        public static int knapsack(Item[] items, int capacity) {
            // Create a table to store the maximum value that can be achieved for each weight capacity and number of items taken.
            // We need that capacity cuz we need to consider weight 0 and 0 items for the -1 in the loop

            int[][] maxValueTable = new int[items.length + 1][capacity + 1];

            // Initialize the table.
            for (int i = 0; i < maxValueTable.length; i++) {
                for (int j = 0; j < maxValueTable[0].length; j++) {
                    maxValueTable[i][j] = 0;
                }
            }

            // Iterate over the number of items (i) taken and the weight capacity(j).
            for (int i = 1; i <= items.length; i++) {
                for (int j = 1; j <= capacity; j++) {
                    // If the current item's weight is greater than the current weight capacity, then the maximum value that can be achieved
                    // is the same as the maximum value that can be achieved without the current item.
                    if (items[i - 1].weight > j) {
                        maxValueTable[i][j] = maxValueTable[i - 1][j];
                    }
                    // Otherwise, the maximum value that can be achieved is the greater of the following:
                    // 1. The maximum value that can be achieved without the current item.
                    // 2. The maximum value that can be achieved without the current item, plus the value of the current item.

                    else {
                        maxValueTable[i][j] = Math.max(maxValueTable[i - 1][j], maxValueTable[i - 1][j - items[i - 1].weight] + items[i - 1].value);
                    }
                }
            }

            // Return the maximum value that can be achieved for the given weight capacity and number of items taken into account.
            return maxValueTable[items.length][capacity];
        }




    }