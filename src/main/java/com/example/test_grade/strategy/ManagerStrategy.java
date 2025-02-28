package com.example.test_grade.strategy;

import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

// 管理员策略类
@Component
public class ManagerStrategy implements IBaseStrategy {
    @Autowired
    private ManagerService managerService;

    @Override
    public Result login(Base user, HttpServletRequest request) {
        Base muser = managerService.login(user.getName(), user.getPassword());
        if (Objects.isNull(muser)) {
            return Result.fail(-1, "用户名或密码或角色错误,请重新输入");
        }
        request.getSession().setAttribute("user", muser);
        return Result.success(muser);
    }

    @Override
    public Result register(Base user) {
        // 管理员不支持注册
        return Result.fail(-1, "管理员不支持注册");
    }

    @Override
    public Result getUser(Base user) {
        Manager manager = managerService.getById(user.getId());
        if (Objects.isNull(manager)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        return Result.success(manager);
    }

    @Override
    public Result changePass(Base base, HttpServletRequest request) {
        Base user = (Base) request.getSession().getAttribute("user");
        String oldPassword = base.getPassword();
        if (!user.getPassword().equals(oldPassword)) {
            return Result.fail(-1, "原密码输入错误");
        }
        String newPassword = base.getNewpassword();
        Manager manager = new Manager();
        BeanUtils.copyProperties(user, manager);
        manager.setPassword(newPassword);
        managerService.doUpdate(manager);
        request.getSession().setAttribute("user", null);
        return Result.success();
    }
}
