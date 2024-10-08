package com.example.test_grade.service;

import com.example.test_grade.entity.Course;
import com.example.test_grade.mapper.CourseMapper;
import com.example.test_grade.service.iservice.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> getCourses() {
        return courseMapper.getAll();
    }

    @Override
    public Course getCourseByName(String name) {
        return courseMapper.getCourseByName(name);
    }

    @Override
    public void insert(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.update(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseMapper.getOne(id);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseMapper.delete(id);
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return courseMapper.findByName(name);
    }

    @Override
    public List<Course> getCoursesByTeacher(Long teacherId) {
        return courseMapper.getCoursesByTeacher(teacherId);
    }
}
