package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * BinarySearchTree implements a SortedSet.
 * It supports typical binary search tree operations including insertion, deletion, traversal, and iteration.
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private class BinaryNode {
        public Type data;
        public BinaryNode leftChild;
        public BinaryNode rightChild;
        public int dotLabel;

        public BinaryNode(Type data, BinaryNode leftChild, BinaryNode rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.dotLabel = dotLabelCount++;
        }

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
    }

    private BinaryNode root;
    private int size;
    private int dotLabelCount;

    public BinarySearchTree() {
        root = null;
        size = 0;
        dotLabelCount = 0;
    }

    public Type findMax() {
        if (root == null) return null;
        BinaryNode temp = root;
        while (temp.rightChild != null) {
            temp = temp.rightChild;
        }
        return temp.data;
    }

    public Type findMin() {
        if (root == null) return null;
        BinaryNode temp = root;
        while (temp.leftChild != null) {
            temp = temp.leftChild;
        }
        return temp.data;
    }

    public boolean contains(Type item) {
        BinaryNode temp = root;
        while (temp != null) {
            int cmp = item.compareTo(temp.data);
            if (cmp == 0) return true;
            temp = (cmp < 0) ? temp.leftChild : temp.rightChild;
        }
        return false;
    }

    public boolean add(Type item) {
        if (root == null) {
            root = new BinaryNode(item, null, null);
            size++;
            return true;
        }
        BinaryNode temp = root;
        while (true) {
            int cmp = item.compareTo(temp.data);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (temp.leftChild == null) {
                    temp.leftChild = new BinaryNode(item, null, null);
                    size++;
                    return true;
                }
                temp = temp.leftChild;
            } else {
                if (temp.rightChild == null) {
                    temp.rightChild = new BinaryNode(item, null, null);
                    size++;
                    return true;
                }
                temp = temp.rightChild;
            }
        }
    }

    public void addAll(Collection<? extends Type> items) {
        for (Type item : items) {
            add(item);
        }
    }

    public boolean remove(Type item) {
        int initialSize = size;
        root = removeRecursive(root, item);
        return size < initialSize; // Return true only if an item was removed
    }

    private BinaryNode removeRecursive(BinaryNode node, Type item) {
        if (node == null) return null;

        int cmp = item.compareTo(node.data);
        if (cmp < 0) {
            node.leftChild = removeRecursive(node.leftChild, item);
        } else if (cmp > 0) {
            node.rightChild = removeRecursive(node.rightChild, item);
        } else {
            size--; // Decrement size when an element is removed

            if (node.leftChild == null) return node.rightChild;
            if (node.rightChild == null) return node.leftChild;

            // Node has two children: Find the in-order successor
            BinaryNode successor = findMinNode(node.rightChild);
            node.data = successor.data;
            node.rightChild = removeRecursive(node.rightChild, successor.data);
        }
        return node;
    }

    private BinaryNode findMinNode(BinaryNode node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public Type first() {
        Type min = findMin();
        if (min == null) throw new NoSuchElementException();
        return min;
    }

    public Type last() {
        Type max = findMax();
        if (max == null) throw new NoSuchElementException();
        return max;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Iterator<Type> iterator() {
        return new Iterator<Type>() {
            private Stack<BinaryNode> stack = new Stack<>();
            private BinaryNode lastVisited = null;
            private boolean canRemove = false;

            { pushLeft(root); }

            private void pushLeft(BinaryNode node) {
                while (node != null) {
                    stack.push(node);
                    node = node.leftChild;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Type next() {
                if (!hasNext()) throw new NoSuchElementException();
                lastVisited = stack.pop();
                pushLeft(lastVisited.rightChild);
                canRemove = true;
                return lastVisited.data;
            }

            public void remove() {
                if (!canRemove) throw new IllegalStateException("next() must be called before remove()");
                BinarySearchTree.this.remove(lastVisited.data);
                canRemove = false;
            }
        };
    }

    public void generateDotFile(String filename) {
        try {
            PrintWriter out = new PrintWriter(filename);
            out.println("digraph Tree {\n\tnode [shape=record]\n");
            if (root != null) out.print(root.generateDot());
            out.println("}");
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
