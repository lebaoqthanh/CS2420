package assign08;

import java.io.IOException;
import java.io.PrintWriter;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{
    /**
     * Represents a node in a binary tree.
     */
    private class BinaryNode {
        // representation of a binary node
        public Type data;
        public BinaryNode leftChild;
        public BinaryNode rightChild;

        // additional member used only for generating DOT format
        public int dotLabel;

        /**
         * Construct a node.
         * @param data held in node
         * @param leftChild of node
         * @param rightChild of node
         */
        public BinaryNode(Type data,
                          BinaryNode leftChild, BinaryNode rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.dotLabel = dotLabelCount++;
        }

        /**
         * @return a string containing all of the edges in the tree rooted at "this"
         *         node, in DOT format
         */
        public String generateDot() {
            String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + data + "|<f2> \"]\n";
            if (leftChild != null) {
                ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
            }
            if (rightChild != null) {
                ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
            }
            return ret;
        }
    } // End of node class

    // representation of a binary search tree
    private BinaryNode root;
    private int size;

    // additional member used only for generating DOT format
    private int dotLabelCount;

    /**
     * Construct an empty BST
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
        dotLabelCount = 0;
    }

    /**
     * @return the item in the BST with the largest data value.
     *
     * COST: O(tree height)
     */
    public Type findMax() {
        // recursive: use this method as a driver, add recursive method to BinaryNode
        // (BASE CASE? RECURSIVE CALL?)

        // iterative:
        // set temp node to root
        // check if temp is null
        // while temp has a right child
        // reset temp to the right child
        // return the data at temp

        return null;
    }

    /*
     * A private recursive method for findMax if you choose recursion
     */
//	private T maxRecursion(BinaryNode node) {
//
//	}

    /**
     * @return the item in the BST with the smallest data value.
     *
     * COST: O(tree height)
     */
    public Type findMin() {
        // like findMax but use left child instead of right child

        return null;
    }

    /**
     * Return true if the item exists in the BST.
     * @param item to search for
     * @return true if item contained
     *
     * COST: O(tree height)
     */
    public boolean contains(Type item) {
        // recursion: use this method as a driver, add recursive method to BinaryNode
        // (BASE CASE? RECURSIVE CALL?)

        // iterative:
        // set temp node to root
        // while temp is not null
        // compare ?? to item (WHAT ARE THE 3 CASES?)

        return false;
    }

    /**
     * Add the new item to the BST such that the order is maintained. Duplicates are
     * not allowed.
     * @param item to add
     *
     * COST: O(tree height)
     */
    public boolean add(Type item) {
        // recursion: use this method as a driver, add recursive method to BinaryNode
        // (BASE CASE? RECURSIVE CALL?)

        // iterative:
        // set temp node to root
        // in a loop
        // compare temp's data to item
        // CASE 1--they are equal: return (do nothing because item is a duplicate)
        // CASE 2--item is bigger: if temp has a right child, advance temp to the right;
        // else set temp's right child to be a new node containing item and return
        // CASE 3--item is smaller: do the opposite of CASE 2 (i.e., go down the left
        // side of the tree)
    }

    /**
     * Find and remove the item from the BST such that that order is maintained. If
     * the BST does not contain the item, do nothing.
     * @param item to remove if present
     *
     * COST: O(tree height)
     */
    public boolean remove(Type item) {
        // iterative:
        // set temp node to root
        // in a loop
        // compare temp's data to item
        // CASE 1--they are equal: do the remove, see CASES A-C below
        // CASE 2--item is bigger: if temp does not have a right child, return (the item
        // is not in the tree);
        // else advance temp to the right
        // CASE 3--item is smaller: if temp does not have a left child, return (the item
        // is not in the tree);
        // else advance temp to the left

        // to remove a node,
        // CASE A--leaf node: simply delete it (i.e., set the parent's child link to
        // null)
        // CASE B--node with one child: adjust its parent's child link to bypass the
        // node and go directly to the node's child
        // CASE C--node with two children: replace the node's data with that of the
        // smallest node of its right subtree (its successor),
        // then remove the successor node (guaranteed to have at most one child)
    }

    /**
     * Write a DOT representation of this BST to file.
     * @param filename of file to write
     */
    public void generateDotFile(String filename) {
        try {
            PrintWriter out = new PrintWriter(filename);
            out.println("digraph Tree {\n\tnode [shape=record]\n");

            if(root == null)
                out.println("");
            else
                out.print(root.generateDot());

            out.println("}");
            out.close();
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
