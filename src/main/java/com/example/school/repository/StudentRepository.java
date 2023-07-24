package com.example.school.repository;
import com.example.school.model.Student;
import java.util.*;
public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudentById(int studentId);
    Student addStudent(Student student);
    List<Student> addMultipleStudents(List<Student> students);
    Student updateStudent(int studentId, Student student);
    void deleteStudent(int studentId);
}