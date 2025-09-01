package org.example.service;

import org.example.entity.Student;
import org.example.exception.UserNotFound;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentService {

    ConcurrentHashMap<Integer, Student> studentMap = new ConcurrentHashMap<>();

    Queue<Student> studentAdmissionQueue = new LinkedList<>();

    private void registerStudent(){
        Student student = studentAdmissionQueue.poll();
        if( student != null) {
            studentMap.put(student.getStudentId(), student);
            System.out.println("Student registered with ID: " + student.getStudentId());
        }
        else {
            System.out.println("Student is already registered");
        }
    }

    public void applyForAdmission(Student student){
            studentAdmissionQueue.offer(student);
            this.registerStudent();

    }

    public Student getStudent(int studentId){
        return studentMap.getOrDefault(studentId, null);
    }

    public void getAllStudentList(){
        studentMap.values().forEach(System.out::println);
    }


    public boolean getStudentById(int studentId){
        Optional<Student> student = Optional.ofNullable(studentMap.get(studentId));
       // System.out.println( student!=null ? student : "No student found with ID: " + studentId);
        if(student.isPresent()){
            System.out.println("Student found with ID: " + studentId);
            return true;
        }
        else{
            System.out.println("Student not found with ID: " + studentId);
            return false;
        }
    }

    public void sortStudent(Comparator<Student> studentComparator){
        studentMap.values().stream().sorted(studentComparator).
                forEach(System.out::println);
    }

    public void getStudentMarks(int studentId){
        Optional<Student> student =Optional.ofNullable(studentMap.get(studentId));
        //System.out.println( student!=null ? "Marks of " + student.getStudentName() + student.getStudentMarks() : "No student found with ID: " + studentId);
        if(student.isPresent()){
            System.out.println("Marks of " + student.get().getStudentName()+ " "+ student.get().getStudentMarks());
        }
        else{
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    public  void studentAnalysis(){
        Student topstudent  =  studentMap.values().stream().max(Comparator.comparingInt(Student::getStudentMarks)).orElseThrow(
                ()-> new UserNotFound("Student Not Found")
        );
        Student lowestStudent = studentMap.values().stream().min(Comparator.comparingInt(Student::getStudentMarks)).orElseThrow(
                ()-> new UserNotFound("Student Not Found")
        );
        List<Student> top3Students = studentMap.values().stream()
                .sorted((student1 , student2)-> -(student1.getStudentMarks()- student2.getStudentMarks())).
                limit(3).toList();
       // int totalMarks =  studentMap.values().stream().map(Student::getStudentMarks).reduce(0,Integer::sum);
       int totalMarks =  studentMap.values().stream().mapToInt(Student::getStudentMarks).sum();
        int size = studentMap.size();
        System.out.println("Average Marks of Class:- " + totalMarks/size);
        System.out.println("Top Student Of Class:- " + topstudent);
        System.out.println("Lowest Student Of Class:- " + lowestStudent);
        System.out.println("Top 3 Student Of Class:- ");
        top3Students.forEach(System.out::println);

    }

    public void updateStudentGrade(int studentId, int grade) {
        Student student = studentMap.computeIfPresent(studentId,(stdId,std)-> {
            std.setStudentMarks(grade);
            return std;
        });
        if (student != null) {
            System.out.println("Student grade updated:- " + student);
        }

    }

}
