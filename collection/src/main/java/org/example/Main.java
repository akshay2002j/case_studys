package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list.get(2));
        list.add(3,3); //it shift all elements to left
        System.out.println(list);


        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(101);
        linkedList.add(102);
        linkedList.add(103);
        linkedList.add(104);
        //why the add first method calls linkFirst method ??? doubt
        linkedList.addFirst(100);
        linkedList.removeFirstOccurrence(104);
        System.out.println(linkedList);




    }
}