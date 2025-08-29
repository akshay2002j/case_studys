package org.example.map;

import java.util.*;

public class MapPractice {
    public static void main(String[] args) {

        //HashMap
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "apple");
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(100, "banana");
        map1.put(200, "orange");
        map.putAll(map1);
        // System.out.println(map);
        String val = map.getOrDefault(100, "grapes");
        //  System.out.println(val);
        //map.values().forEach(System.out::println);
        map.putIfAbsent(100, "grapes");
        //replace(k,newValue) -->Replaces value for key only if it exists
        System.out.println( map.replace(100, "banana"));
        //replace(k,oldValue,newValue) --> replaces the matching kay and old vale with new value
        System.out.println(map.replace(100, "banana", "orange"));
        // V remove(key)-->Removes the entry for the given key.
        //map.remove(100);
        //boolean remove(Object key, Object value) -->Removes entry if key is mapped to specific value.
        //System.out.println(map.remove(1001, "orange"));
        //compute()-> Computes a value for the key
        //map.compute(100,(k,v)-> v.toUpperCase());
        //V computeIfAbsent()--> Computes value if key is absent.
        map.computeIfAbsent(201, (k)->"mango");
        //V computeIfPresent(k,func) --> Computes new value if key is present. if faun return null remove key
        map.computeIfPresent(200,(k,v)->"santra");
        System.out.println(map);
        //replaceAll()-->Replaces all values using a function.
       // map.replaceAll((k,v)->"mango");
        System.out.println(map);


        //Custom sorting for treemap
//        TreeMap<User,Integer> treeMap = new TreeMap<>(
//                (user1,user2)-> -(user1.name.compareTo(user2.name))
//        );
//        treeMap.put(new User(1,"Ram"),1);
//        treeMap.put(new User(2,"Shay"),2);
//        treeMap.put(new User(3,"Jay"),3);
//        System.out.println(treeMap);
//        System.out.println( treeMap.descendingKeySet());//only view is provided in descending order
//        System.out.println(treeMap.descendingMap());


//        NavigableMap<Integer,String> navigableMap = new TreeMap<>();
//        navigableMap.put(20,"Akshay");
//        navigableMap.put(25,"Faran");
//        navigableMap.put(30,"Akshay");
//        navigableMap.put(22,"Rohan");
//        System.out.println( navigableMap.descendingMap());
//        System.out.println(navigableMap);
//    }
    }


}
