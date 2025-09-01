package org.example;

import org.example.entity.Student;
import org.example.service.StudentService;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        boolean flag = true;

        do {
            try {
                System.out.println("Who you are Staff or Student..?");
                String choice = sc.nextLine();

                switch (choice) {
                    case "Staff" -> {
                        System.out.println("Please select one of the following:- ");
                        System.out.println("1. Get Student List:- ");
                        System.out.println("2. Get Reports of Class:- ");
                        System.out.println("3. Sort Student:- ");
                        System.out.println("4. Get Student By Id:- ");
                        System.out.println("5. Update Student Grades:- ");
                        System.out.println("6. Exit Program:- ");

                        int staffChoice = sc.nextInt();

                        switch (staffChoice) {
                            case 1 -> {
                                studentService.getAllStudentList();

                            }
                            case 2 -> {
                                studentService.studentAnalysis();
                            }
                            case 3 -> {
                                System.out.println("Please Entry you Filter");
                                System.out.println("1. By Name");
                                System.out.println("2. By Marks");
                                System.out.println("3. By Age");
                                int sortChoice = sc.nextInt();
                                switch (sortChoice) {
                                    case 1 -> {
                                        Comparator<Student> sortByName = Comparator.comparing(Student::getStudentName);
                                        studentService.sortStudent(sortByName);
                                    }
                                    case 2 -> {
                                        Comparator<Student> sortByMarks = Comparator.comparing(Student::getStudentMarks);
                                        studentService.sortStudent(sortByMarks);
                                    }
                                    case 3 -> {
                                        Comparator<Student> sortByAge = Comparator.comparing(Student::getStudentAge);
                                        studentService.sortStudent(sortByAge);
                                    }
                                }

                            }
                            case 4 -> {
                                System.out.println("Enter Student Id:");
                                int studentId = sc.nextInt();
                                studentService.getStudentById(studentId);
                            }
                            case 5 -> {
                                System.out.println("Enter Student Id: ");
                                int studentId = sc.nextInt();
                                System.out.println("Enter Student new Grades: ");
                                int neeGrades = sc.nextInt();
                                studentService.updateStudentGrade(studentId, neeGrades);
                            }
                            case 6 -> {
                                flag = false;
                                System.out.println("Program has been terminated");
                            }

                        }
                    }
                    case "Student" -> {
                        System.out.println("Please select one of the following:");
                        System.out.println("1. Apply for admission");
                        System.out.println("2. Get Marks");
                        System.out.println("3. Exit Program:- ");

                        int studentChoice = sc.nextInt();
                        sc.nextLine();
                        switch (studentChoice) {
                            case 1 -> {
                                System.out.println("Enter Student Name");
                                String studentName = sc.nextLine();
                                System.out.println("Enter Student Age");
                                int studentAge = sc.nextInt();
                                System.out.println("Enter Student Marks");
                                int marks = sc.nextInt();
                                studentService.applyForAdmission(new Student(studentName, studentAge, marks));
                            }
                            case 2 -> {

                                System.out.println("Enter Student Id");
                                int studentId = sc.nextInt();
                                studentService.getStudentMarks(studentId);
                            }
                            case 3 -> {
                                flag = false;
                                System.out.println("Program has been terminated");

                            }
                        }
                    }
                }
                sc.nextLine();
            }
            catch (InputMismatchException inputMismatchException) {
                System.out.println("Please enter a valid Input");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        while (flag);




    }
}