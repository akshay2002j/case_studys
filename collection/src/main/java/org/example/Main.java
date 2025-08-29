package org.example;

import java.lang.classfile.attribute.LineNumberTableAttribute;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.random.RandomGenerator;

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
        list.add(null);
        System.out.println(list.get(2));
        list.add(3,3); //it shift all elements to left
        System.out.println(list);

        ListIterator listIterator = list.listIterator();
//
//        while(listIterator.hasNext()){
//            System.out.println(listIterator.next());
//
//        }


        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(101);
        linkedList.add(102);
        linkedList.add(103);
        linkedList.add(104);
        //why the add first method calls linkFirst method ??? doubt
        linkedList.addFirst(100);
        linkedList.removeFirstOccurrence(104);
        linkedList.remove(Integer.valueOf(104));
        System.out.println(linkedList);
        System.out.println(linkedList.pollFirst());


        //vector
        Vector<Object> vector = new Vector<>();
        vector.add("hii");
        vector.add("hey");
        vector.add(101);
        System.out.println(vector);
        vector.remove(Integer.valueOf(101));
        //vector.removeElement(101);
        System.out.println(vector);
        System.out.println(vector.capacity() + " this is capacity of vector");
        vector.trimToSize();
        System.out.println(vector);
        System.out.println(vector.capacity() + " this is capacity of vector");


        /// STACK ---> LIFO order maintain has all list and vector methods

        Stack<String> stack = new Stack<>();
        stack.push("Akshay");
        stack.push("welcom");
        stack.push("hey");
        System.out.println(stack);
        System.out.println(stack.capacity() + " this is capacity of stack");
        stack.remove(1);
        System.out.println(stack);
        System.out.println(stack.get(1));

        //-->allows for concurrent read and write operations on list
         List<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();



     /*   for (int i = 0; i < 100; i++) {
            copyOnWriteArrayList.add(new Random().nextDouble());
        }

        for (Object o : copyOnWriteArrayList) {
            System.out.println(o);
            copyOnWriteArrayList.add(new  Random().nextDouble());
        }
*/



    }
}

