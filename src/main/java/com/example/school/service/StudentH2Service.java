package com.example.school.service;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;
import java.util.*;
import com.example.school.service.StudentH2Service;
import com.example.school.model.StudentRowMapper;
@Service
public class StudentH2Service implements StudentRepository{
    @Autowired
    private JdbcTemplate db;
    public StudentH2Service(JdbcTemplate jdbcTemplate){
        this.db = jdbcTemplate;
    }
@Override
public ArrayList<Student>getAllStudents(){
List<Student>studentList=db.query("Select * from STUDENT",new StudentRowMapper());
 ArrayList<Student>students=new ArrayList<>(studentList);
return students;

}
@Override
public Student getStudentById(int studentId){
    try{
Student student=db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(),studentId);
          return student;
       }catch (Exception e){
throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
}
@Override
public Student addStudent(Student student){
    db.update("insert into STUDENT(studentName,gender,standard)values(?,?,?)",student.getstudentName(),student.getgender(),student.getstandard());
   Student savedstudents=db.queryForObject("select * from STUDENT where studentName=? and gender=? and standard=?",new StudentRowMapper(),student.getstudentName(),student.getgender(),student.getstandard());
    return savedstudents;
}
@Override
    public List<Student> addMultipleStudents(List<Student> students) {
        String sql = "INSERT INTO STUDENT (studentName, gender, standard) VALUES (?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for (Student student : students) {
            Object[] args = new Object[]{student.getstudentName(), student.getgender(), student.getstandard()};
            batchArgs.add(args);
        }

        int[] updateCounts = db.batchUpdate(sql, batchArgs);
        List<Student> addedStudents = new ArrayList<>();

        int index = 0;
        for (int updateCount : updateCounts) {
            if (updateCount > 0) {
                addedStudents.add(students.get(index));
            }
            index++;
        }

        return addedStudents;
    }

@Override
public Student updateStudent(int studentId,Student student){
    if(student.getstudentName()!=null){
        db.update("UPDATE STUDENT SET studentName=? WHERE studentId=?",student.getstudentName(),studentId);
    }
    if(student.getgender()!=null){
        db.update("UPDATE Student SET gender=? WHERE studentId=?",student.getgender(),studentId);
    }
    if(student.getstandard()!=0){
        db.update("UPDATE STUDENT SET standard=? WHERE studentId=?",student.getstandard(),studentId);
    }
    return getStudentById(studentId);
    }
@Override
public void deleteStudent(int studentId){
    db.update("DELETE FROM STUDENT WHERE studentId=?",studentId);
}
}