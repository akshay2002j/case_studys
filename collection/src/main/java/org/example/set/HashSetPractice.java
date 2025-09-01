package org.example.set;

import com.sun.source.tree.Tree;

import java.util.*;

public class HashSetPractice {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Akshay");
        set.add("Rohhan");
        set.add("Akshata");
        set.add("Ram");
        set.add("Akshay");
        set.remove("Akshay");
        System.out.println(set);

        Set<String> treSet = new TreeSet<>();
        treSet.add("Akshay");
        treSet.add("Ram");
        treSet.add("Akshay");
        treSet.add("rohan");
        treSet.add("prashant");
        System.out.println(treSet);

        NavigableSet<String> navigableSet = new TreeSet<>(treSet);
        System.out.println(navigableSet);

        System.out.println(navigableSet.subSet("Akshay",true ,"prashant",true));
        treSet.retainAll(navigableSet); //Union from both

        treSet.removeIf((val)->val.equals("Akshay"));
        System.out.println(treSet);
         Object [] arr = treSet.toArray();
//         for (Object o : arr) {
//             System.out.println(o.toString());
//         }
         Iterator<String>  itr = treSet.iterator();


        HashSet<String> hashSet = new LinkedHashSet<>(treSet);
        System.out.println(hashSet);


    }
}
