package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapPractice {
    public static void main(String[] args) {
        //HashMap

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"apple");
        HashMap<Integer,String> map1 = new HashMap<>();
        map1.put(100,"banana");
        map1.put(200,"orange");
        map.putAll(map1);
        System.out.println(map);
        String val =  map.getOrDefault(100,"grapes");
        System.out.println(val);
        //map.values().forEach(System.out::println);
        HashMap<User,String> userMap = new HashMap<>();
        userMap.put(new User(1,"kim"),"kim"); //-->stores according to hashCode of object
        userMap.put(new User(2,"kim"),"tom");
        userMap.put(new User(3,"kim"),"gerry");
        System.out.println(userMap);

        //LinkedHashMap -->preserves insertion order

        Map<User,Integer> linkedUsermap = new LinkedHashMap<>(10,0.5f,true);
        User user = new User(5,"samay");
        linkedUsermap.put(new User(1,"kim"),1);
        linkedUsermap.put(new User(2,"Akshay"),2);
        linkedUsermap.put(user,4);
        linkedUsermap.put(new User(3,"Rohan"),3);
        System.out.println(linkedUsermap);
        linkedUsermap.get(user); //accessed so will be at last
        System.out.println(linkedUsermap);


        //Custom sorting for treemap
        TreeMap<User,Integer> treeMap = new TreeMap<>(
                (user1,user2)-> -(user1.name.compareTo(user2.name))
        );
        treeMap.put(new User(1,"Ram"),1);
        treeMap.put(new User(2,"Shay"),2);
        treeMap.put(new User(3,"Jay"),3);
        System.out.println(treeMap);

    }
}
