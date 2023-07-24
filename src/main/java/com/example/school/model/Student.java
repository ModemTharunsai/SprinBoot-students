package com.example.school.model;
public class Student {
    private int studentId;
    private String studentName;
    private String gender;
    private int standard;
 public Student(int studentId, String studentName, String gender,int standard) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender= gender;
        this.standard=standard;
    }

    public int getstudentId() {
        return studentId;
    }

    public void setstudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getstudentName() {
        return studentName;
    }

    public void setstudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }
     public int getstandard() {
        return standard;
    }

    public void setstandard(int standard) {
        this.standard = standard;
    }
} 