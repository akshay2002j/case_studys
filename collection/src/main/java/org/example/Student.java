package org.example;

public class Student implements Comparable<Student> {
    String name;
    double gpa;

    public Student(String name, int gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student other) {
        return (int) (this.gpa - other.gpa); // Natural order:--> by gpa
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
