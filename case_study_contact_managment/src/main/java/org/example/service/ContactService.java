package org.example.service;

import org.example.entity.Contact;

import java.util.*;

public class ContactService {

    HashMap<String, Contact> contacts = new HashMap<String, Contact>();

    Queue <Contact> recentAdditionQueue = new LinkedList<>();

    Stack<String> searchHistroyStack = new Stack<>();


    //first check if the contact present then add if absent
    public void addContact(Contact contact, ContactBinarySearchTree contactBinarySearchTree) {
        if (contacts.containsKey(contact.getPhone())) {
            System.out.println("Contact already exists");
        }
        else {
            contacts.putIfAbsent(contact.getPhone(), contact);
            recentAdditionQueue.offer(contact);
            contactBinarySearchTree.addContactToTree(contact.getName(), contact.getPhone());
            System.out.println("Contact has been added");
        }
    }

    //search contact by Phone and maintain search history
//    public void searchByPhone(String phone) {
//          Optional<Contact> contact =  Optional.ofNullable(contacts.get(phone));
//          searchHistroyStack.push(phone);
//          if (contact.isPresent()) {
//              System.out.println(contacts.get(phone));
//          }
//          else {
//              System.out.println("Contact not found");
//          }
//    }

    //search contact by name and maintain search history
    public void searchContact(String seachKey,boolean byPhone) {
        searchHistroyStack.push(seachKey);
        //search by Phone
        if (byPhone) {
            Optional.ofNullable(contacts.get(seachKey)).ifPresent(System.out::println);
        }
        else  {
            contacts.values().stream().
                    filter(contact -> contact.getName().equalsIgnoreCase(seachKey))
                    .forEach(System.out::println);
        }


    }


    public void printContacts() {
        Queue<Contact> tempQueue = new PriorityQueue<>(recentAdditionQueue);
        this.printContactInReverseOrder(tempQueue);
    }

    //printing contact in reverse order using recursion
    private void printContactInReverseOrder(Queue<Contact> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Contact contact =  queue.poll();
        printContactInReverseOrder(queue);
        System.out.println("Reverse order: "+ contact);
    }



    //update the contact if present based on phone number
    public  void updateContact(String phone ,Contact contact) {
        if (contacts.containsKey(contact.getPhone())) {
            contacts.computeIfPresent(contact.getPhone(), (key, value) -> contact);
            recentAdditionQueue.stream().filter(contact1 -> contact1.getPhone().equals(phone)).findFirst().map(contact1 -> contact);
//            TODO:[] update to tree
            System.out.println("Contact updated");
        }
        else  {
            System.out.println("Contact not found");
        }
    }

    //delete the Contact from hashMap if present
    public void deleteContact(String phone) {

        if (contacts.containsKey(phone)) {
            contacts.remove(phone);
            //            TODO:[] delete from tree
            System.out.println("Contact deleted");
        }
        else {
            System.out.println("Contact not found");
        }

    }

    //Get the Search History
    public void getSearchHistory(){
        if (searchHistroyStack.isEmpty()){
            System.out.println("No history found");
        }
        else {
            searchHistroyStack.forEach(System.out::println);

        }
    }

    //get all recently added contacts from queue
    public void recentlyAddedContacts(){
        if (!recentAdditionQueue.isEmpty()){
            recentAdditionQueue.forEach(System.out::println);
//
//            for (int i = 0; i < recentAdditionQueue.size(); i++) {
//                System.out.println(recentAdditionQueue.);
//            }
        }
        else {
            System.out.println("No history found");
        }
    }

    public void getAllContacts(){
        contacts.values().forEach(System.out::println);
    }

}
