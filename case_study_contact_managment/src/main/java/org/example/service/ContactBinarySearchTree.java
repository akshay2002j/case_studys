package org.example.service;


class Node {
    String name;
    String phone;
    Node left;
    Node right;

    public Node(String name, String phone) {
        this.name = name;
        this.phone = phone;
        left = null;
        right = null;
    }

}
public class ContactBinarySearchTree {

        Node root;
        public ContactBinarySearchTree() {
            root = null;
        }

       public void addContactToTree(String name, String phone) {
            root = insertRec(root, name, phone);
        }

       //insert to end of tree depending on condition
        private Node insertRec(Node root, String name, String phone) {
            if (root == null) {
                root = new Node(name, phone);
                return root;
            }
            if (name.compareTo(root.name) < 0) {
                root.left = insertRec(root.left, name, phone);
            } else {
                root.right = insertRec(root.right, name, phone);
            }
            return root;
        }

        public void displayAlphabetically() {
           if (root == null) {
                System.out.println("No contact information");

            }
            else {
                this.displayInorder(root);
            }
        }

        private void displayInorder(Node root) {
            if (root == null) {
                return;
            }
            displayInorder(root.left);
            System.out.println( "Name:- "+ root.name + ", " + "Phone Number:- "+ root.phone);
            displayInorder(root.right);
        }





}
