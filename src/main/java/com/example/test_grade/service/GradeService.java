package com.example.test_grade.service;

import com.example.test_grade.entity.Grade;
import com.example.test_grade.mapper.GradeMapper;
import com.example.test_grade.service.iservice.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService implements IGradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> getOneGrades(Long studentId) {
        return gradeMapper.getOne(studentId);
    }

    @Override
    public List<Grade> getGrades() {
        return gradeMapper.getAll();
    }

    @Override
    public List<Grade> findByName(String courseName) {
        return gradeMapper.findByName(courseName);
    }

    @Override
    public List<Grade> findByStudentName(String studentName) {
        return gradeMapper.findByStudentName(studentName);
    }

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        return gradeMapper.getGrade(studentId,courseId);
    }

    @Override
    public void insert(Grade grade) {
        gradeMapper.insert(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.update(grade);
    }

    @Override
    public void delete(Long studentId, Long courseId) {
        gradeMapper.delete(studentId,courseId);
    }

    @Override
    public List<Grade> descAllScore() {
        return gradeMapper.descAllScore();
    }

    @Override
    public List<Grade> findByNameDesc(String courseName) {
        return gradeMapper.findByNameDesc(courseName);
    }

    @Override
    public List<Grade> findByStudentNameDesc(String studentName) {
        return gradeMapper.findByStudentNameDesc(studentName);
    }

    @Override
    public List<Grade> getOneGradesDesc(Long studentId) {
        return gradeMapper.getOneDesc(studentId);
    }

    @Override
    public List<Grade> findName(Long studentId, String courseName) {
        return gradeMapper.findName(studentId,courseName);
    }

    @Override
    public String getOneGPA(Long studentId) {
        return gradeMapper.getGPA(studentId);
    }
}
