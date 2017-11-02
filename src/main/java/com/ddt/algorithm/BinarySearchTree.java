package com.ddt.algorithm;


import com.sun.istack.internal.NotNull;

import java.util.Comparator;

public abstract class BinarySearchTree<T> implements Comparator<T> {

    static class Node<T> {

        public T data;
        public Node<T> leftChild;
        public Node<T> rightChild;

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * get tree's root
     *
     * @return root
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * find node
     *
     * @param key node's data
     * @return node
     */
    public Node<T> find(T key) {
        if (root == null) {
            return null;
        }

        Node<T> current = root;
        while (current.data != key) {
            if (compare(current.data, key) > 0) {
                current = root.leftChild; // go left;
            } else {
                current = root.rightChild;
            }

            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * insert data into tree.
     *
     * @param data node's data
     */
    public void insert(T data) {
        Node<T> newNode = new Node<>();
        newNode.data = data;

        if (root == null) {
            root = newNode;
        } else {
            Node<T> current = root;
            Node<T> parent;
            while (true) {
                parent = current;
                if (compare(current.data, data) > 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    /**
     * delete node.
     *
     * @param key node's key
     * @return true if delete successfully, else return false.
     */
    public boolean delete(T key) {
        if (root == null) {
            return false;
        }

        Node<T> current = root;
        Node<T> parent = root;
        boolean isLeftChild = true;

        while (current.data != key) {
            parent = current;
            if (compare(current.data, key) > 0) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }

            if (current == null) {
                return false;
            }
        }

        //found to be deleted node;
        //if no child, simply delete it
        if (current.leftChild == null
                && current.rightChild == null) {
            if (current == root) {
                root = null; // tree is empty
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        }
        // if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        }
        // if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        }
        // two children, so replace with inorder successor
        else {
            Node<T> successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            //connect successor to current's left child
            successor.leftChild = current.leftChild;
        }

        return true;
    }

    /**
     * goes to right child, then right child's left descends
     *
     * @param delNode to be deleted node
     * @return node with next-highest value after delNode
     */
    @NotNull
    private Node<T> getSuccessor(@NotNull Node<T> delNode) {
        Node<T> successorParent = delNode;
        Node<T> successor = delNode;
        Node<T> current = delNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public void preOrder(Node<T> current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrder(current.leftChild);
            preOrder(current.rightChild);
        }
    }

    public void inOrder(Node<T> current) {
        if (current != null) {
            inOrder(current.leftChild);
            System.out.print(current.data + " ");
            inOrder(current.rightChild);
        }
    }


    public void postOrder(Node<T> current) {
        if (current != null) {
            postOrder(current.leftChild);
            postOrder(current.rightChild);
            System.out.print(current.data + " ");
        }
    }

    public abstract void displayTree();

}
