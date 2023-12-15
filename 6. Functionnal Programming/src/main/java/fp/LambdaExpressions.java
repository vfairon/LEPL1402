package fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class LambdaExpressions {
    /**
     * Return a binary operator that computes the sum of two Integer objects.
     */
    public static Object sumOfIntegers() {
        BinaryOperator<Integer> f = (x, y) -> x + y;

         return f;
    }

    /**
     * Return a predicate that tests whether a String is empty.
     */
    public static Object isEmptyString() {
        Predicate<String> f = s -> s.isEmpty();
         return f;
    }

    /**
     * Return a predicate that tests whether an Integer is an odd number.
     */
    public static Object isOddNumber() {
        Predicate<Integer> f = i ->(i%2 ==1);
         return f;
    }

    /**
     * Return a function that computes the mean of a List of Double objects.
     * If the list is empty, an IllegalArgumentException must be thrown.
     */
    public static Object computeMeanOfListOfDoubles() {

        Function<List<Double>,Double> f = list -> {
            if (list.size() == 0){
                throw new IllegalArgumentException("no bro");
            }
            Double sum = 0.0;
            for (int i = 0; i< list.size(); i++){

                sum += list.get(i);
            }
            return sum/list.size();

        };
         return f;
    }

    /**
     * Remove the even numbers from a list of Integer objects.
     */
    public static void removeEvenNumbers(List<Integer> lst) {
        int i =0;
        while (i<lst.size()) {

            if (lst.get(i)%2==0){
                System.out.println("removing" + lst.get(i));
                lst.remove(i);
            }
            else {
                i++;
            }
        }




    }

    /**
     * Return a function that computes the factorial of an Integer.
     * If the number is zero, the factorial equals 1 by convention.
     * If the number is negative, an IllegalArgumentException must be thrown.
     */
    public static Object computeFactorial() {
        Function<Integer, Integer> f = n -> {
            if (n==0||n==1){
                return 1;
            }
            if (n<0){
                throw new IllegalArgumentException();
            }
            int toreturn = 1;
            for (int i = n; i>0;i--){
                toreturn = toreturn*i;

            }
            return toreturn;
        };
         return f;
    }

    /**
     * Return a function that converts a list of String objects to lower case.
     */
    public static Object listOfStringsToLowerCase() {
        Function<List<String>,List<String>> f = lst ->{
            List<String> newlist = new ArrayList<>();
            for (int i = 0; i < lst.size(); i++){
                newlist.add(lst.get(i).toLowerCase());
            }
            return newlist;
        };
         return f;
    }

    /**
     * Return a function that concatenates two String objects.
     */
    public static Object concatenateStrings() {
        BinaryOperator<String> f = (n1,n2)-> n1 + n2;
         return f;
    }

    public static class MinMaxResult {
        private int minValue;
        private int maxValue;

        MinMaxResult(int minValue,
                     int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        int getMinValue() {
            return minValue;
        }

        int getMaxValue() {
            return maxValue;
        }
    }

    /**
     * Return a function that computes the minimum and maximum values in a list.
     * The content of the Optional must be present if and only if the list is non-empty.
     */
    public static Function<List<Integer>, Optional<MinMaxResult>> computeMinMax() {

        Function<List<Integer>, Optional<MinMaxResult>> f = lst -> {
            if (lst.isEmpty()){
                return Optional.empty();
            }
            int min = 200000;
            int max = -100000;
            for (int i = 0; i < lst.size(); i++){
                if (lst.get(i) < min){
                    min = lst.get(i);
                }
                if (lst.get(i) > max){
                    max = lst.get(i);
                }

            }


            Optional<MinMaxResult> minmaxtoreturn =  Optional.of(new MinMaxResult(min,max));

            return minmaxtoreturn;
        };
         return f;
    }

    /**
     * Return a function that, given a String object and a character, counts
     * the number of occurrences of the character inside the string.
     */
    public static Object countInstancesOfLetter() {
        BiFunction<String,Character,Integer> f = (s,c) -> {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    count++;
                }
            }
            return count;


        };
        return f;
    }
}
