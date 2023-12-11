package oop;
import java.util.Optional;

/**
 * In this exercise, you will implement some methods for a circular linked-list.
 * A circular linked-list is a linked-list for which the last element has a successor: the
 * first element.
 * For example, if the list is 4 -> 5 -> 2, then 4 is the first element, and 2 points towards 4.
 *                             ^         |
 *                             |         |
 *                             -----------
 * 
 * We ask you to implement two methods; enqueue and remove which, respectively, add an element at the end of the queue, and
 * removes an element at a given index. The time complexity of each method is note in their specifications.
 */
public class CircularLinkedList {

    public static class Node {
        public int value;
        public Optional<Node> next;

        public Node(int value) {
            this.value = value;
            this.next = Optional.empty();
        }
        
        public void setNext(Node next) {
            this.next = Optional.of(next);
        }
        
        public boolean hasNext() {
            return this.next.isPresent();
        }
    }
    
    public Optional<Node> first;
    public Optional<Node> last;
    public int size;

    public CircularLinkedList() {//initialised this way
        this.first = Optional.empty();
        this.last = Optional.empty();
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public Optional<Node> getFirst() {
        return this.first;
    }

    public Optional<Node> getLast() {
        return this.last;
    }
    
    public void enqueue(int value) {

        if (isEmpty()){
            Node toadd = new Node(value);

            toadd.setNext(toadd);

            this.last = Optional.of(toadd);
            this.first = Optional.of(toadd);
            this.size += 1;
        }
        else {
            Node toadd = new Node(value);

            //first : set next of new last to first element. using current last elem

            toadd.setNext(getLast().get().next.get());//cuz optional object. have to get its value

            //then : set link of last to the new last (toadd)
            getLast().get().setNext(toadd);

            //finally : set the variable "last" to toadd
            this.last = Optional.of(toadd);

            this.size += 1;

        }
    }
    
    public int remove(int index) {
        if (index > size - 1){
            return -1;
        }
        size -=  1;


        if (index == 0){

            last.get().setNext(first.get().next.get());
            first = first.get().next;

        }
        else {
            int i = 1;
            Optional<Node> prev = first;
            Optional<Node> current = first.get().next;
            Optional<Node> next = current.get().next;


            while (i<= index) {
                if (i == index){
                    prev.get().setNext(next.get());
                    return 0;
                }

                prev = current;
                current = next;
                next = next.get().next;

                i+=1;
            }
        }
        return 0;




    }
}
