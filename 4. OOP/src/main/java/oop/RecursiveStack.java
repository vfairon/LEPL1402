package oop;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A Recursive Stack is a stack (LIFO)
 * that is immutable.
 * @param <E>
 */
public class RecursiveStack<E>  implements Iterable<E> {

    final E e;// top

    final RecursiveStack<E> next;

    /**
     * Creates an empty stack
     */
    public RecursiveStack() {
        // TODO
        e = null;
        next = null;
    }

    /**
     * Create a stack with e on top and next as the next stack.
     * The next is unchanged.
     *
     * @param e the element to put on top
     * @param next the following stack
     */
    private RecursiveStack(E e, RecursiveStack<E> next) {
        this.e = e;
        this.next = next;
    }

    /**
     * Creates and return a new stack with e on top and the
     * current stack at the bottom.
     * The current stack is unchanged.
     *
     * @param e the element to place on top
     * @return the new stack
     */
    public RecursiveStack<E> add(E e) {

        return new RecursiveStack<>(e, this);
    }

    /**
     * Returns the element on top of the stack
     *
     * @throws EmptyStackException if the stack is empty
     * @return the element on top of the stack
     */
    public E top() {
        if (size() == 0){
            throw new EmptyStackException ();
        }

        return this.e;
    }

    /**
     * Return the stack with element on top removed.
     * The current stack is unchanged.
     *
     * @return the stack with the top element removed
     */
    public RecursiveStack<E> removeTop() {
        // TODO
        if (size() == 0){
            throw new EmptyStackException ();
        }
         return new RecursiveStack<>(next.e, next.next);
    }

    /**
     * Computes the number of elements in the stack
     *
     * @return the number of element in the stack
     */
    public int size() {

        int size = 0;
        RecursiveStack<E> prochain = next;
        while (prochain != null){
            prochain = prochain.next;
            size += 1;

        }
         return size;
    }

    /**
     * Reverse the stack.
     * The current stack is unchanged.
     *
     * @return a reversed version of the current stack (the top element becomes the bottom one)
     */
    public RecursiveStack<E> reverse() {
        System.out.println("Beginning to reverse");
        if (next == null) return this;
        RecursiveStack<E> Newstack = new RecursiveStack<>();

        RecursiveStack<E> current = this;

        while (current.e != null) {
            Newstack = Newstack.add(current.e);
            current = current.next;

        }
        return Newstack;




    }

    /**
     * Creates a top-down iterator on the stack
     * The delete is not implemented
     *
     * @return the top-down iterator.
     */
    @Override
    public Iterator<E> iterator() {
        // TODO: think about implementing an inner class
         return new IteratorsMethods();
    }
    private class IteratorsMethods implements Iterator<E>{
        private RecursiveStack<E> stack = RecursiveStack.this;
        @Override
        public boolean hasNext(){
            if (stack.next == null){
                return false;
            }
            return true;
        }
        @Override
        public E next(){
            E e = stack.e;
            stack = stack.next;
            return e;
        }
    }



}
