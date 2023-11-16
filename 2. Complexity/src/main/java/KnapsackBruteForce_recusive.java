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

    public static int knapsackrecusrive(Item[] items, int capacity, int index){


        int with = 0;
        int without = 0;

        if (index == items.length || capacity == 0){//If cant take anything anymore : return the max !
            return Math.max(with,without);
        }
        if (items[index].weight <= capacity){ // if we can take this item check calculate its value taken
            with = items[index].value + knapsackrecusrive(items, capacity - items[index].weight, index +1);
        }
        without = knapsackrecusrive(items, capacity, index +1); // calculate value without taking it




        return Math.max(with,without); // return the max with and without taking it
    }
    public static int knapsack(Item[] items, int capacity) {
        int num = 0;
        num = knapsackrecusrive(items,capacity, 0);

         return num;
    }


}