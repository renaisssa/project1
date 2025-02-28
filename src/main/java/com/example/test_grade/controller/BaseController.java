package com.example.test_grade.controller;

import com.example.test_grade.common.Result;
import com.example.test_grade.entity.Base;
import com.example.test_grade.strategy.IBaseStrategy;
import com.example.test_grade.strategy.StrategyFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


//工厂使用：在 BaseController 类中，通过注入 StrategyFactory 来获取具体的策略对象，而不需要直接创建策略对象，从而将对象的创建和使用分离。
@RestController
@RequestMapping
public class BaseController {
    @Autowired
    private StrategyFactory strategyFactory;
    private Base contextUser;

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody Base user, HttpServletRequest request) {
        if (Objects.isNull(user.getName()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getSign())) {
            return Result.fail(-1, "请完善输入信息");
        }
        Integer sign = user.getSign();
        IBaseStrategy strategy = strategyFactory.getStrategy(sign);
        Result result = strategy.login(user, request);
        if (result.isSuccess()) {
            contextUser = (Base) request.getSession().getAttribute("user");
        }
        return result;
    }

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody Base user) {
        if (Objects.isNull(user.getName()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getSign())) {
            return Result.fail(-1, "请完善输入信息");
        }
        Integer sign = user.getSign();
        IBaseStrategy strategy = strategyFactory.getStrategy(sign);
        return strategy.register(user);
    }

    // 获取个人信息
    @GetMapping("/getUser")
    public Result getUser() {
        if (Objects.isNull(contextUser)) {
            return Result.fail(-1, "用户未登录");
        }
        Integer sign = contextUser.getSign();
        IBaseStrategy strategy = strategyFactory.getStrategy(sign);
        return strategy.getUser(contextUser);
    }

    // 修改密码
    @PostMapping("/changePass")
    public Result changePass(@RequestBody Base base, HttpServletRequest request) {
        Base user = (Base) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            return Result.fail(-1, "用户未登录");
        }
        Integer sign = user.getSign();
        IBaseStrategy strategy = strategyFactory.getStrategy(sign);
        return strategy.changePass(base, request);
    }
}