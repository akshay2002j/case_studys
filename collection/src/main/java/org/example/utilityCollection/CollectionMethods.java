package org.example.utilityCollection;

import java.util.*;

public class CollectionMethods {


    public static void main(String[] args) {


        Queue<Integer> prior = new PriorityQueue<>(); //default capacity is 11
        prior.add(100);
        prior.add(200);
        prior.add(600);
        prior.add(800);
        prior.add(900);
        prior.add(1000);
        prior.add(2000);
        prior.add(1000);
        System.out.println( Collections.frequency(prior, 100));
        System.out.println( Collections.max(prior));
        System.out.println( Collections.min(prior));
       // List<String> list = Collections.nCopies(5,"Akshay");
        List<String> list = new ArrayList<String>();
        list.add("Rohhan");
        list.add("Akshata");
        list.add("preeti");
        System.out.println(list);
        Collections.shuffle(list,new Random()); //list only
        System.out.println(list + " after shuffle");
        Collections.sort(list, Collections.reverseOrder()); //only takes list
        System.out.println(list);
       // Collections.enumeration(list);
        List<String> advocates = new ArrayList<>(List.of("Ana", "Billy", "Denys", "Heather", "Jim", "Nicolai"));
        List<String> advocatesCopy = new ArrayList<>(List.of("Blank","Blank","Blank","Blank","Blank","Blank", "Blank"));
        Collections.copy(advocatesCopy, advocates);//Ana, Billy, Denys, Heather, Jim, Nicolai, Blank
       // System.out.println(advocatesCopy);
        Collections.sort(advocates, (o1, o2) -> o1.length() - o2.length());
        System.out.println(advocates);
        int ind = Collections.binarySearch(advocates,"Jim");
        System.out.println(ind);

    }
}
