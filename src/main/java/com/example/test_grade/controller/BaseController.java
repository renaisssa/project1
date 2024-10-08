package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.*;
import com.example.test_grade.service.EducationService;
import com.example.test_grade.service.ManagerService;
import com.example.test_grade.service.StudentService;
import com.example.test_grade.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BaseController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private EducationService educationService;

    private Base contextuser;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Base user, HttpServletRequest request){
        if(ObjectUtil.isEmpty(user.getName())||ObjectUtil.isEmpty(user.getPassword())||ObjectUtil.isEmpty(user.getSign())){
            return Result.fail(-1,"请完善输入信息");
        }
        Integer sign = user.getSign();
        Base muser=new Base();
        //管理员
        if(sign==1) {
            muser =managerService.login(user.getName(),user.getPassword());
            if(ObjectUtil.isEmpty(muser)){
                return Result.fail(-1,"用户名或密码或角色错误,请重新输入");
            }
        }
        //教师
        if(sign==2){
            muser=teacherService.login(user.getName(),user.getPassword());
            if(ObjectUtil.isEmpty(muser)){
                return Result.fail(-1,"用户名或密码或角色错误,请重新输入");
            }
        }
        //学生
        if(sign==3){
            muser=studentService.login(user.getName(),user.getPassword());
            if(ObjectUtil.isEmpty(muser)){
                return Result.fail(-1,"用户名或密码或角色错误,请重新输入");
            }
        }
        //在session中将用户信息存一下,并传给全局变量contextuser
        request.getSession().setAttribute("user",muser);
        contextuser=(Base) request.getSession().getAttribute("user");

        return Result.success(muser);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody Base user){
        if(ObjectUtil.isEmpty(user.getName())||ObjectUtil.isEmpty(user.getPassword())||ObjectUtil.isEmpty(user.getSign())){
            return Result.fail(-1,"请完善输入信息");
        }
        Integer sign = user.getSign();
        //教师
        if(sign==2){
            Teacher teacher=new Teacher();
            BeanUtils.copyProperties(user,teacher);

            if(ObjectUtil.isNotEmpty(teacherService.getTeacherByName(user.getName()))) {
                return Result.fail(-1, "用户名已存在");
            }
            teacherService.register(teacher);
        }
        //学生
        if(sign==3){
            Student student=new Student();
            BeanUtils.copyProperties(user,student);

            if(ObjectUtil.isNotEmpty(studentService.getStudentByName(user.getName()))) {
                return Result.fail(-1, "用户名已存在");
            }
            studentService.register(student);

        }
        return Result.success();
    }

    //获取个人信息
    @GetMapping("/getUser")
    public Result getUser(){
        Integer sign = contextuser.getSign();
        //管理员
        if(sign==1) {
            Manager manager =managerService.getManagerById(contextuser.getId());
            if(ObjectUtil.isEmpty(manager)){
                return Result.fail(ResultCode.NOT_FOUND);
            }
            return Result.success(manager);
        }
        //教师
        if(sign==2){
            Teacher teacher =teacherService.getTeacherById(contextuser.getId());
            if(ObjectUtil.isEmpty(teacher)){
                return Result.fail(ResultCode.NOT_FOUND);
            }
            return Result.success(teacher);
        }
        //学生
        if(sign==3) {
            Student student =studentService.getStudentById(contextuser.getId());
            if(ObjectUtil.isEmpty(student)){
                return Result.fail(ResultCode.NOT_FOUND);
            }
            return Result.success(student);
        }
        return Result.success(new Base());
    }

    //修改密码
    @PostMapping("/changePass")
    public Result changePass(@RequestBody Base base, HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        Integer sign = user.getSign();

        String oldPassword=base.getPassword();

        if(!user.getPassword().equals(oldPassword)){
            return Result.fail(-1,"原密码输入错误");
        }

        String newPassword=base.getNewpassword();
        //管理员
        if(sign==1) {
            Manager manager=new Manager();
            BeanUtils.copyProperties(user,manager);
            manager.setPassword(newPassword);
            managerService.updateManager(manager);
        }
        //教师
        if(sign==2){
            Teacher teacher=new Teacher();
            BeanUtils.copyProperties(user,teacher);
            teacher.setPassword(newPassword);
            teacherService.updateTeacher(teacher);
        }
        //学生
        if(sign==3) {
            Student student=new Student();
            BeanUtils.copyProperties(user,student);
            student.setPassword(newPassword);
            studentService.updateStudent(student);
        }
        request.getSession().setAttribute("user",null);

        return Result.success();
    }
}
