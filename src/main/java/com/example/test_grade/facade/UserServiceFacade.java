package com.example.test_grade.facade;

import com.example.test_grade.common.Result;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.entity.Student;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.service.ManagerService;
import com.example.test_grade.service.StudentService;
import com.example.test_grade.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//外观模式：为子系统中的一组接口提供一个统一的高层接口，使得子系统更容易使用。我们可以创建一个门面类，将不同角色的操作封装起来，对外提供一个统一的接口。
@Service
public class UserServiceFacade {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    // 新增管理员
    public Result addManager(Manager manager) {
        return managerService.add(manager);
    }

    // 修改管理员
    public Result updateManager(Manager manager) {
        return managerService.update(manager);
    }

    // 删除管理员
    public Result deleteManager(Long id) {
        return managerService.deleteById(id);
    }

    // 查询全部管理员
    public Result getAllManagers() {
        return managerService.getAll();
    }

    // 根据姓名模糊查询管理员
    public Result findManagersByName(String name) {
        return managerService.findByName(name);
    }

    // 新增学生
    public Result addStudent(Student student) {
        return studentService.add(student);
    }

    // 修改学生
    public Result updateStudent(Student student) {
        return studentService.update(student);
    }

    // 删除学生
    public Result deleteStudent(Long id) {
        return studentService.deleteById(id);
    }

    // 查询全部学生
    public Result getAllStudents() {
        return studentService.getAll();
    }

    // 根据姓名模糊查询学生
    public Result findStudentsByName(String name) {
        return studentService.findByName(name);
    }

    // 新增教师
    public Result addTeacher(Teacher teacher) {
        return teacherService.add(teacher);
    }

    // 修改教师
    public Result updateTeacher(Teacher teacher) {
        return teacherService.update(teacher);
    }

    // 删除教师
    public Result deleteTeacher(Long id) {
        return teacherService.deleteById(id);
    }

    // 查询全部教师
    public Result getAllTeachers() {
        return teacherService.getAll();
    }

    // 根据姓名模糊查询教师
    public Result findTeachersByName(String name) {
        return teacherService.findByName(name);
    }
}