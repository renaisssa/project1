package com.example.test_grade.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.mapper.ManagerMapper;
import com.example.test_grade.mapper.TeacherMapper;
import com.example.test_grade.service.iservice.IManagerService;
import com.example.test_grade.service.iservice.ITeacherService;
import com.example.test_grade.template.BaseServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService extends BaseServiceTemplate<Teacher> implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    protected boolean checkAddInfo(Teacher teacher) {
        return teacher.getName() != null && teacher.getSex() != null && teacher.getAge() != null
                && teacher.getTitle() != null && teacher.getManagerId() != null;
    }

    @Override
    protected boolean isNameDuplicated(Teacher teacher) {
        return teacherMapper.getTeacherByName(teacher.getName()) != null;
    }

    @Override
    protected void setInitialPassword(Teacher teacher) {
        if (teacher.getPassword() == null) {
            teacher.setPassword("123456");
        }
    }

    @Override
    protected void doAdd(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public void doUpdate(Teacher teacher) {
        teacherMapper.update(teacher);
    }

    @Override
    protected void doDeleteById(Long id) {
        teacherMapper.delete(id);
    }

    @Override
    protected List<Teacher> doGetAll() {
        return teacherMapper.getAll();
    }

    @Override
    protected List<Teacher> doFindByName(String name) {
        return teacherMapper.findByName(name);
    }

    @Override
    public Teacher getById(Long id) {
        return teacherMapper.getOne(id);
    }
    @Override
    public void register(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public Base login(String name, String password) {
        Teacher teacher = teacherMapper.findByNameAndPassword(name,password);
        return teacher;
    }

    @Override
    public Teacher getTeacherByName(String name) {
        return teacherMapper.getTeacherByName(name);
    }

//    @Autowired
//    private TeacherMapper teacherMapper;
//

//

//

//
//    @Override
//    public Teacher getTeacherById(Long id) {
//        return teacherMapper.getOne(id);
//    }
//
//    @Override
//    public void updateTeacher(Teacher teacher) {
//        teacherMapper.update(teacher);
//    }
//
//    @Override
//    public List<Teacher> getTeachers() {
//        return teacherMapper.getAll();
//    }
//
//    @Override
//    public void deleteTeacherById(Long id) {
//        teacherMapper.delete(id);
//    }
//
//    @Override
//    public List<Teacher> findTeacherByName(String name) {
//        return teacherMapper.findByName(name);
//    }
//
//    @Override
//    public List<Teacher> getTeacherIdList(Long id) {
//        return teacherMapper.getTeacherIdList(id);
//    }
}
