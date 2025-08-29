package org.example.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapPractice {

    //LinkedHashMap -->preserves insertion order
    public static void main(String[] args) {
        Map<User, Integer> linkedUsermap = new LinkedHashMap<>(2,0.5f,true);
        User user = new User(5, "samay");
        linkedUsermap.put(new User(1, "kim"), 1);
        linkedUsermap.put(new User(2, "Akshay"), 2);
        linkedUsermap.put(user, 4);
        linkedUsermap.put(new User(3, "Rohan"), 3);
        System.out.println(linkedUsermap);
        linkedUsermap.get(user); //accessed so will be at last
        System.out.println(linkedUsermap);
        HashMap<User, Integer> linkedUsermap2 = new HashMap<>();
        linkedUsermap2.put(new User(1, "sam"), 101);
        linkedUsermap2.put(new User(2, "mam"), 201);
        linkedUsermap.merge(
                user,4,(o,n)->null);
        System.out.println("After merge function -> "+ linkedUsermap);
    }

}
