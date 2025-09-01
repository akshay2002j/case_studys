package org.example.entity;

import java.util.Comparator;

public class Student implements Comparable<Student> {

    private static int cnt = 100;

    private final int studentId;
    private String studentName;
    private int studentAge;
    private int studentMarks;


    public Student(String studentName, int studentAge, int studentMarks) {
        this.studentId = cnt++;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentMarks = studentMarks;
    }

    public int getStudentId() {
        return studentId;
    }


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(int studentMarks) {
        this.studentMarks = studentMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                ", studentMarks=" + studentMarks +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.studentId - o.getStudentId();
    }
}
