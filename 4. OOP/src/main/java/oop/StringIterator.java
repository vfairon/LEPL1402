package oop;


import java.util.Iterator;

/**
 * Question:
 *
 * You are asked to implement an IterableString allowing
 * to iterate on the successive chars of a given string
 *
 *
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class StringIterator {

    /**
     * Factory method
     * @param s the string on which to iterate
     * @return a new instance of your implementation of an IterableString allowing to iterate on s
     */
    public static IterableString makeIterableString(String s) {
        // TODO return an instance of your class that implements the interface
        return new MyImplementationStringIterator(s);

    }

    /**
     * An IterableString permit to iterate on each character of a
     * string one by one from left to right
     */
    public interface IterableString extends Iterable<Character> {


    }


    // TODO implement the interface IterableString as an (inner) class

    private static class MyImplementationStringIterator implements IterableString {
        String s;
        MyImplementationStringIterator(String s){
            this.s = s;

        }



        @Override
        public Iterator<Character> iterator() {

            return new Iterator<Character>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    if (i <s.length()){
                        return true;
                    }
                    return false;
                }

                @Override
                public Character next() {
                    int a = i;
                    i = i +1;
                    return s.charAt(a);
                }
            };


        }


    }


}
