package oop;


/**
 * Question:
 *
 * You are asked to clean a increasing sorted linked List (see the )
 * Cleaning the linkedList means keeping only one occurrence of each value.
 *
 * For instance cleaning: 3,3,3,4,5,5,6,6,6,7,9,9,9,9,10,10
 * Gives: 3,4,5,6,7,9,10
 *
 * Your algorithm should execute in Theta(n)
 * where n are the number of elements in the original list
 *
 */
public class CleanLinkedList {

    Node first = null;
    Node last = null;

    class Node {
        int v;
        Node next;
        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }
    public void add(int v) {
        if (first == null && last == null){
            Node toadd = new Node(v,null);
            first = toadd;
            last = toadd;
        }
        else{
            Node toadd = new Node(v,null);
            last.next = toadd;
            last = toadd;
        }
        
    }

    public void add(int ... values) {
        for (int v: values) {
            add(v);
        }
    }


    /**
     * Given the increasingly sorted list, it removes the duplicates
     * @return an increasingly sorted list containing the same set
     *         of elements as list but without duplicates.
     */
    public CleanLinkedList clean() {
        CleanLinkedList toreturn = new CleanLinkedList();

        Node current = first;
        Node next = first.next;

        toreturn.add(current.v);


        while (next != null){

            if (toreturn.last.v != current.v){
                toreturn.add(current.v);
            }
            current = next;
            next = current.next;
        }


         return toreturn;
    }





}

