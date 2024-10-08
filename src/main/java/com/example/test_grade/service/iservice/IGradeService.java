package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Education;
import com.example.test_grade.entity.Grade;

import java.util.List;

public interface IGradeService {

    List<Grade> getOneGrades(Long studentId);

    List<Grade> getGrades();

    List<Grade> findByName(String courseName);

    List<Grade> findByStudentName(String studentName);

    Grade getGrade(Long studentId, Long courseId);

    void insert(Grade grade);

    void updateGrade(Grade grade);

    void delete(Long studentId, Long courseId);

    List<Grade> descAllScore();

    List<Grade> findByNameDesc(String courseName);

    List<Grade> findByStudentNameDesc(String studentName);

    List<Grade> getOneGradesDesc(Long studentId);

    List<Grade> findName(Long studentId, String courseName);

    String getOneGPA(Long studentId);
}
