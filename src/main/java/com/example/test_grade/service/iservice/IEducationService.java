package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Course;
import com.example.test_grade.entity.Education;

import java.util.List;

public interface IEducationService {

    List<Education> getEducations();

    Education getEducationByTeacherId(Long teacherId);

    void insert(Education education);

    void updateEducation(Education education);

    void delete(Long studentId,Long teacherId);

    Education getEducation(Long teacherId, Long studentId);

    List<Education> findByName(Long studentId,String teacherName);

    List<Education> getOneEducations(Long studentId);

    List<Education> getStudentsByTeacher(Long teacherId);
}
