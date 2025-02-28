package com.example.test_grade.strategy;

import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

// 教师策略类
@Component
public class TeacherStrategy implements IBaseStrategy {
    @Autowired
    private TeacherService teacherService;

    @Override
    public Result login(Base user, HttpServletRequest request) {
        Base muser = teacherService.login(user.getName(), user.getPassword());
        if (Objects.isNull(muser)) {
            return Result.fail(-1, "用户名或密码或角色错误,请重新输入");
        }
        request.getSession().setAttribute("user", muser);
        return Result.success(muser);
    }

    @Override
    public Result register(Base user) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(user, teacher);
        if (Objects.nonNull(teacherService.getTeacherByName(user.getName()))) {
            return Result.fail(-1, "用户名已存在");
        }
        teacherService.register(teacher);
        return Result.success();
    }

    @Override
    public Result getUser(Base user) {
        Teacher teacher = teacherService.getById(user.getId());
        if (Objects.isNull(teacher)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        return Result.success(teacher);
    }

    @Override
    public Result changePass(Base base, HttpServletRequest request) {
        Base user = (Base) request.getSession().getAttribute("user");
        String oldPassword = base.getPassword();
        if (!user.getPassword().equals(oldPassword)) {
            return Result.fail(-1, "原密码输入错误");
        }
        String newPassword = base.getNewpassword();
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(user, teacher);
        teacher.setPassword(newPassword);
        teacherService.doUpdate(teacher);
        request.getSession().setAttribute("user", null);
        return Result.success();
    }
}