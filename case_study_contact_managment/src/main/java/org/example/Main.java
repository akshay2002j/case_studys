package org.example;

import org.example.entity.Contact;
import org.example.service.ContactBinarySearchTree;
import org.example.service.ContactService;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            ContactService contactService = new ContactService();
            ContactBinarySearchTree contactBinarySearchTree = new ContactBinarySearchTree();
            boolean flag = true;

            do {
                System.out.println("-------------------------------------Welcome to the Contacts Management System-----------------------------------------------------");

                System.out.println("What would you like to do?");
                System.out.println("1. Add Contact");
                System.out.println("2. Remove Contact");
                System.out.println("3. Display All Contacts Alphabetically");
                System.out.println("4. Update Contact");
                System.out.println("5. Search Contact");
                System.out.println("6. See Search History");
                System.out.println("7. See Recently Added Contacts");
                System.out.println("8. All contact in reverse order");
                System.out.println("9. Get all Contacts:-");
                System.out.println("10. Exit");


                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter Contact Name:");
                        String name = sc.nextLine();
                        System.out.println("Enter Contact Phone:");
                        String phone = sc.nextLine();
                        contactService.addContact(new Contact(name, phone), contactBinarySearchTree);

                    }
                    case 2 -> {
                        System.out.println("Enter Contact Phone Number:");
                        String phone = sc.nextLine();
                        contactService.deleteContact(phone,contactBinarySearchTree);
                    }
                    case 3 -> {
                        contactBinarySearchTree.displayAlphabetically();
                    }
                    case 4 -> {
                        System.out.println("Enter Contact Phone Number to Update Name:");
                        String phone = sc.nextLine();
                        System.out.println("Enter Contact Name:");
                        String name = sc.nextLine();
                        contactService.updateContact(phone, new Contact(name, phone),contactBinarySearchTree);
                    }
                    case 5 -> {
                        System.out.println("Search By Name Or Phone Number:- 1 -> for Name 2 -> for Phone Number");
                        try {
                            int choiceNum = sc.nextInt();
                            sc.nextLine();
                            if (choiceNum == 1) {
                                System.out.println("Enter Contact Name:");
                                String name = sc.nextLine();
                                contactService.searchContact(name, false);
                            } else if (choiceNum == 2) {
                                System.out.println("Enter Contact Phone Number:");
                                String phone = sc.nextLine();
                                contactService.searchContact(phone, true);
                            } else {
                                System.out.print("Enter valid Choice");

                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Please enter a valid Choice");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    case 6 -> {
                        System.out.println("Search History :-");
                        contactService.getSearchHistory();
                    }
                    case 7 -> {
                        System.out.println("Recently Added Contacts");
                        contactService.recentlyAddedContacts();
                    }
                    case 8 -> {
                        System.out.println("All contacts in reverse order");
                        contactService.printContacts();
                    }
                    case 9 -> {
                        System.out.println("All Contacts :-");
                      contactService.getAllContacts();
                    }
                    case 10 ->{
                        flag = false;
                        System.out.println("Byy See you later...!!!");
                    }

                }


            }
            while (flag);

        } catch (InputMismatchException exception) {
            System.out.println("Please enter a valid Choice");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}