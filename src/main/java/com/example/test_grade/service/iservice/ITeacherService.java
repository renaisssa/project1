package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.entity.Teacher;

import java.util.List;

public interface ITeacherService {
    void register(Teacher teacher);
    Base login(String name, String password);
    public Teacher getTeacherByName(String name);


//    Teacher getTeacherById(Long id);

//
//    void updateTeacher(Teacher teacher);
//
//    List<Teacher> getTeachers();
//
//    void deleteTeacherById(Long id);
//
//    List<Teacher> findTeacherByName(String name);
//
//    List<Teacher> getTeacherIdList(Long id);

}
