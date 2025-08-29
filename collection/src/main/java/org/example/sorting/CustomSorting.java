package org.example.sorting;

import org.example.map.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomSorting {



    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Akshay",6));
        students.add(new Student("Jammy",7));
        students.add(new Student("BoBy", 8 ));
        students.add(new Student("Ram",4));
//        students.add(null);
//        Comparator<String> comp = Comparator.nullsFirst(String::compareTo);
//        students.sort();


        //Collections.sort(students);
//        students.sort(
//                (student1,student2)-> student1.name.compareTo(student2.name)
//        );

        Comparator<Student> namecomparator = (students1,students2)->students1.name.compareTo(students2.name);

      //  namecomparator.thenComparing((students1,students2)-> - (int) (students1.gpa -students2.gpa));
        //Comparison with
//        Comparator<Student> nameComparator = (students1,students2)->{
//            return students1.name.compareTo(students2.name);
//        };
//        Collections.sort(students, namecomparator);
//        System.out.println(students);

        //1.comparing()
        Comparator<Student> byname = Comparator.comparing(Student::getName)
                .thenComparing(Student::getGpa);
        students.sort(byname);
        System.out.println(students);
        //2.thenComparing -- >


        //3.reverse order
        Comparator<Student> reverse = Comparator.reverseOrder();
        students.sort(reverse);
        System.out.println(students);

        //4 revered() //current comparator reversed order
        byname.reversed();
        System.out.println(students);

        Comparator<Student> byNameIgnoreCase =
                Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);
        students.sort(byNameIgnoreCase);
        System.out.println(students);

    }
}
