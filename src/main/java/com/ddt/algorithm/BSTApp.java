package com.ddt.algorithm;

class BSTInt extends BinarySearchTree<Integer> {

    @Override
    public int compare(Integer data, Integer other) {
        return Integer.compare(data, other);
    }

    @Override
    public void displayTree() {

    }
}

class BSTString extends BinarySearchTree<String> {

    @Override
    public int compare(String data, String other) {
        return data.compareTo(other);
    }

    @Override
    public void displayTree() {

    }
}


public class BSTApp {

    public static void main(String[] args) {
        testInt();
        testString();
    }

    private static void testInt() {

        BSTInt tree = new BSTInt();
        tree.insert(10);
        tree.insert(24);
        tree.insert(33);
        tree.insert(22);
        tree.insert(11);
        tree.insert(17);
        tree.insert(40);

        tree.preOrder(tree.getRoot());
        System.out.printf("\n");
        tree.inOrder(tree.getRoot());
        System.out.printf("\n");
        tree.postOrder(tree.getRoot());
        System.out.printf("\n");
    }

    private static void testString() {
        BSTString tree = new BSTString();
        tree.insert("a");
        tree.insert("d");
        tree.insert("c");
        tree.insert("e");
        tree.insert("f");
        tree.insert("g");
        tree.insert("h");


        tree.preOrder(tree.getRoot());
        System.out.printf("\n");
        tree.inOrder(tree.getRoot());
        System.out.printf("\n");
        tree.postOrder(tree.getRoot());
        System.out.printf("\n");
    }
}
