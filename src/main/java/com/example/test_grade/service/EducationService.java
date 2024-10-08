package com.example.test_grade.service;

import com.example.test_grade.entity.Education;
import com.example.test_grade.mapper.EducationMapper;
import com.example.test_grade.service.iservice.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService implements IEducationService {
    @Autowired
    private EducationMapper educationMapper;

    @Override
    public List<Education> getEducations() {
        return educationMapper.getAll();
    }

    @Override
    public Education getEducationByTeacherId(Long teacherId) {
        return educationMapper.getEducationByTeacherId(teacherId);
    }

    @Override
    public void insert(Education education) {
        educationMapper.insert(education);
    }

    @Override
    public void updateEducation(Education education) {
        educationMapper.update(education);
    }

    @Override
    public void delete(Long studentId,Long teacherId) {
        educationMapper.delete(studentId,teacherId);
    }

    @Override
    public Education getEducation(Long teacherId, Long studentId) {
        return educationMapper.getEducation(teacherId,studentId);
    }

    @Override
    public List<Education> findByName(Long studentId,String teacherName) {
        return educationMapper.findByName(studentId,teacherName);
    }

    @Override
    public List<Education> getOneEducations(Long studentId) {
        return educationMapper.getOne(studentId);
    }

    @Override
    public List<Education> getStudentsByTeacher(Long teacherId) {
        return educationMapper.getStudentsByTeacher(teacherId);
    }
}
