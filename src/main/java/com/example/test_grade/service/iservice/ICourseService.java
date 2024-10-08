package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Course;
import com.example.test_grade.entity.Teacher;

import java.util.List;

public interface ICourseService {

    List<Course> getCourses();

    Course getCourseByName(String name);

    void insert(Course course);

    void updateCourse(Course course);

    Course getCourseById(Long id);

    void deleteCourseById(Long id);

    List<Course> findCourseByName(String name);

    List<Course> getCoursesByTeacher(Long teacherId);
}
