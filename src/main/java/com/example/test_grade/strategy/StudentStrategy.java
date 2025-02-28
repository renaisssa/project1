package com.example.test_grade.strategy;

import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Student;
import com.example.test_grade.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

// 学生策略类
@Component
public class StudentStrategy implements IBaseStrategy {
    @Autowired
    private StudentService studentService;

    @Override
    public Result login(Base user, HttpServletRequest request) {
        Base muser = studentService.login(user.getName(), user.getPassword());
        if (Objects.isNull(muser)) {
            return Result.fail(-1, "用户名或密码或角色错误,请重新输入");
        }
        request.getSession().setAttribute("user", muser);
        return Result.success(muser);
    }

    @Override
    public Result register(Base user) {
        Student student = new Student();
        BeanUtils.copyProperties(user, student);
        if (Objects.nonNull(studentService.getStudentByName(user.getName()))) {
            return Result.fail(-1, "用户名已存在");
        }
        studentService.register(student);
        return Result.success();
    }

    @Override
    public Result getUser(Base user) {
        Student student = studentService.getById(user.getId());
        if (Objects.isNull(student)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        return Result.success(student);
    }

    @Override
    public Result changePass(Base base, HttpServletRequest request) {
        Base user = (Base) request.getSession().getAttribute("user");
        String oldPassword = base.getPassword();
        if (!user.getPassword().equals(oldPassword)) {
            return Result.fail(-1, "原密码输入错误");
        }
        String newPassword = base.getNewpassword();
        Student student = new Student();
        BeanUtils.copyProperties(user, student);
        student.setPassword(newPassword);
        studentService.doUpdate(student);
        request.getSession().setAttribute("user", null);
        return Result.success();
    }
}
