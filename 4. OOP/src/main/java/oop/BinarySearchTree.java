package oop;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Question:
 *
 * You are asked to retrieve the elements of the binary search tree
 * in decreasing order (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * A Binary Search Tree is a Data Structure that represents
 * a set (no duplicates thus) of integers stored such that
 * for a given node, keys in the left subtree are all smaller that the key in the node
 *                   keys in the right right subtree are larger than the key in the node
 *
 * Your algorithm should have a time complexity of Theta(n)
 * where n is the number of elements in the set.
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class BinarySearchTree {

    private Node root;             // root of BST

    private class Node {

        private int key;           // sorted by key
        private Node left, right , before;  // left and right subtrees

        public Node(int key, Node before) {
            this.key = key;
            this.before = before;
        }
    }


    public BinarySearchTree() {

    }

    public void insert(int key) {

        root = put(root, key, root);

    }

    private Node put(Node x, int key , Node last) {
        Node k;
        if (x == null) {
            return new Node(key,last);
        }

        if (key < x.key) {
            x.left = put(x.left, key,x);



        } else if (key > x.key) {
            x.right = put(x.right, key,x);

        }

        return x;
    }
    public Node Right(Node a){
        while(a.right != null){
            a = a.right;

        }
        return a;
    }

    /**
     * Return the list that contains the elements in decreasing order
     * The complexity should be Theta(n) where n is the number of elements in the set.
     * @return
     */
    public List<Integer> decreasing() {
        List<Integer> d = new ArrayList<Integer>();
        Node act = root;

        act = Right(act);//11
        d.add(act.key);

        while(act != root){
            act = act.before;//10


            if (!d.contains(act.key)){
                d.add(act.key);
            }
            if (act.left != null && !d.contains(act.left.key)){

                act = act.left;

                System.out.println(act.key);

                if (Right(act) == act && act.left != null){
                    d.add(act.key);
                    act = act.left;
                    while(act.left != null && Right(act) == act ) {
                        d.add(act.key);
                        act = act.left;
                    }
                }
                act = Right(act);
                d.add(act.key);
            }


        }


        return d;
    }






}