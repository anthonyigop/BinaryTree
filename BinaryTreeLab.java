/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package binarytreelab;

import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class BinaryTreeLab {

    class Node {

        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
    Node root;

    public BinaryTreeLab() {
        root = null;
    }

    //
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    //
    public String search(int key) {
        return searchRec(root, key) ? "Key found" : "Key not found";
    }

    private boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        return key < root.data ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    //
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    //
    public void mirror() {
        root = createMirror(root);
    }

    private Node createMirror(Node root) {
        if (root == null) {
            return null;
        }
        Node left = createMirror(root.left);
        Node right = createMirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //
    public int countLeafNodes() {
        return countLeaves(root);
    }

    private int countLeaves(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return countLeaves(root.left) + countLeaves(root.right);
    }
    
    //
    public boolean isValidBST() {
        return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;
        return validateBST(root.left, min, root.data) && validateBST(root.right, root.data, max);
    }
    
     public void inOrder() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryTreeLab tree = new BinaryTreeLab();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Binary Tree Operations ---");
            System.out.println("1. Insert Node");
            System.out.println("2. Search for Key");
            System.out.println("3. Display In-order Traversal");
            System.out.println("4. Get Height of Tree");
            System.out.println("5. Count Leaf Nodes");
            System.out.println("6. Mirror the Tree");
            System.out.println("7. Validate as BST");
            System.out.println("8. Exit");
            System.out.print("Choose a number: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a value to insert: ");
                    int value = scanner.nextInt();
                    tree.insert(value);
                    System.out.println("Value inserted.");
                    break;
                case 2:
                    System.out.print("Enter a key to search: ");
                    int key = scanner.nextInt();
                    System.out.println(tree.search(key));
                    break;
                case 3:
                    System.out.println("In-order Traversal:");
                    tree.inOrder();
                    break;
                case 4:
                    System.out.println("Height of the Tree: " + tree.getHeight());
                    break;
                case 5:
                    System.out.println("Total Leaf Nodes: " + tree.countLeafNodes());
                    break;
                case 6:
                    tree.mirror();
                    System.out.println("Tree mirrored. Perform an in-order traversal to see the changes.");
                    break;
                case 7:
                    System.out.println("Is the Tree a Valid BST? " + tree.isValidBST());
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);

        scanner.close();
    }

}
