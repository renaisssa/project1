package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Student;
import com.example.test_grade.entity.Teacher;

import java.util.List;

public interface IStudentService {
    void register(Student student);
    Base login(String name, String password);

    Student getStudentByName(String name);
//
//    Student getStudentById(Long id);
//
//    void updateStudent(Student student);
//
//    List<Student> getStudents();
//
//    void deleteStudentById(Long id);
//
//    List<Student> findStudentByName(String name);
//
//    List<Student> getStudentIdList(Long id);
}
