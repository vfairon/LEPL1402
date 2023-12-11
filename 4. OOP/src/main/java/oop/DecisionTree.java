package oop;

public abstract class DecisionTree {

    /**
     * Predicts the label of the input "features" in this decision tree
     * The prediction is done by following a path from the root to a leaf node
     * which contains the predicted label.
     * The path followed is fixed by the values of a boolean feature array.
     * Each node contains the index of a feature to test.
     * If the feature at that index is true, the left child is followed,
     * otherwise the right one is followed.
     *
     * Consider the following decision tree:
     *
     *                4
     *               / \
     *              2   T
     *             / \
     *            F   T
     *
     * [F,T,F,F,T] would receive the prediction label T and the path followed would be:
     *                4
     *               /
     *              2
     *               \
     *                T
     *
     * [F,T,T,F,F] would receive the prediction label T and the path followed would be:
     *                 4
     *                  \
     *                   T
     *
     * @param features the boolean input features that are used to determine the path to follow
     *
     * @return the boolean label of the leaf node reached by following the path from the
     *         root to the leaf determined by the value of the features and the tests of the
     *         decision tree.
     */
    public abstract boolean predict(boolean [] features);


    /**
     * Merge two decision trees such that the resulting prediction is operated as follows:
     * 1) the feature at featureIndex is tested,
     * 2) if it is true the predict decision is delegated to the left subtree,
     *    otherwise it is delegated to the right subtree
     * @param featureIndex the feature to be tested
     * @param left the left subtree
     * @param right the right subtree
     * @return a new decision tree such that the left tree is used for the prediction if
     *         the feature at featureIndex is true, the right tree is used otherwise
     */


    // So what we want to do is the following. We need to create a fnct in which for the split node function sends a data structure
    // that explores the left if true and right sinon; For that we can create a class Node  that will have a right, left, feature index values
    // it will represent a node... The predict function will be implemented in that class since it will need to have the right and left values
    // The predict function will recursively check for index value and turn left or right. note that the value it checks is the index value of the
    // right and left node. It will do that untill it reaches an Endpoint that will just sends its value

    public static DecisionTree splitNode(int featureIndex, DecisionTree left, DecisionTree right) {
        return new Node(featureIndex,left,right);
    }

    private static class Node extends DecisionTree{
        DecisionTree left, right;
        int featureIndex;

        Node(int featureIndex,DecisionTree left, DecisionTree right) {
            this.left = left;
            this.right = right;
            this.featureIndex =featureIndex;
        }
        @Override
        public  boolean predict(boolean[] features){
            if (features[featureIndex]){
                return this.left.predict(features);
            }
            else{
                return this.right.predict(features);
            }
        }
    }
        private static class EndNode extends DecisionTree{
        boolean value;
        EndNode(boolean value){
            this.value = value;

        }
        @Override
        public boolean predict(boolean[] features) {
            return value;
        }
    }


    /**
     * Return a decision tree that always predicts label
     * @param label the label to be predicted
     * @return a decision tree that always predicts label
     */
    public static DecisionTree decisionNode(boolean label) {
         return new EndNode(label);
    }

}
