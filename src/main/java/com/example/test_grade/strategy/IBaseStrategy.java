package com.example.test_grade.strategy;

import com.example.test_grade.common.Result;
import com.example.test_grade.entity.Base;
import jakarta.servlet.http.HttpServletRequest;

//将BaseController中if...else...修改为策略模式
public interface IBaseStrategy {
    Result login(Base user, HttpServletRequest request);
    Result register(Base user);
    Result getUser(Base user);
    Result changePass(Base base, HttpServletRequest request);
}
