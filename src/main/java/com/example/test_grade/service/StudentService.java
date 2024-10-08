package com.example.test_grade.service;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Student;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.mapper.StudentMapper;
import com.example.test_grade.mapper.TeacherMapper;
import com.example.test_grade.service.iservice.IStudentService;
import com.example.test_grade.service.iservice.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void register(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public Base login(String name, String password) {
        Student student = studentMapper.findByNameAndPassword(name,password);
        return student;
    }

    @Override
    public Student getStudentByName(String name) {
        return studentMapper.getStudentByName(name);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentMapper.getOne(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.update(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentMapper.getAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        studentMapper.delete(id);
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentMapper.findByName(name);
    }

    @Override
    public List<Student> getStudentIdList(Long id) {
        return studentMapper.getStudentIdList(id);
    }
}
