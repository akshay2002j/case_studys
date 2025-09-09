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

        public void updateContactTree(String name, String phone) {

            if (root == null) {
                System.out.println("No contact information");
            }
            else {
                this.updateInorder(root,name,phone);
            }
        }

        private void updateInorder(Node root, String name, String phone) {
            if (root == null) {
                return;
            }
            updateInorder(root.left, name, phone);
            // Check current node
            if (phone.equalsIgnoreCase(root.phone)) {
                root.name = name;
                return;
            }
            updateInorder(root.right, name, phone);
        }

        public void deleteContact(String phone) {

            if (root == null) {
                System.out.println("No contact information");
            }
            else {
               root  =  this.deleteRec(root,phone);
            }
        }

        private Node deleteRec(Node root, String phone) {
            if (root == null) {
                return null;
            }

            if (root.phone.compareTo(phone)<0){
                root.left = deleteRec(root.left,phone);
            }
            else if (root.phone.compareTo(phone)>0){
                root.right = deleteRec(root.right,phone);
            }
            else {

                if (root.left == null ) {
                    return root.right;
                }
                else if (root.right == null ) {
                    return root.left;
                }
                // Case 2: Node with two children
                // Find the in-order successor (minimum value in the right subtree)
                Node minNode = findMin(root.right);

                // Copy the in-order successor's data to this node
                root.phone = minNode.phone;
                root.name = minNode.name; // Copy other data as well

                // Delete the in-order successor from the right subtree
                root.right = deleteRec(root.right, minNode.phone);
            }
            return root;
        }

    // Finds the node with the minimum value in a subtree
    private Node findMin(Node root) {
        Node current = root;
        // Traverse down the left side of the tree to find the minimum value
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


}
