package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

public class CustomSorting {



    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Ram",6));
        students.add(new Student("Shay",7));
        students.add(new Student("Ram", 7 ));
        students.add(new Student("Shay",4));
        //Collections.sort(students);
        students.sort(
                (student1,student2)-> student1.name.compareTo(student2.name)
        );
        System.out.println(students);



    }
}
