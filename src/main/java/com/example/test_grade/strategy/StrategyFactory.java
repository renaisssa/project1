package com.example.test_grade.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// 策略工厂类
//工厂模式:工厂类：定义了 StrategyFactory 类，该类负责根据用户的角色类型（sign）创建并返回相应的策略对象。
@Component
public class StrategyFactory {
    @Autowired
    private ApplicationContext applicationContext;

    public IBaseStrategy getStrategy(Integer sign) {
        switch (sign) {
            case 1:
                return applicationContext.getBean(ManagerStrategy.class);
            case 2:
                return applicationContext.getBean(TeacherStrategy.class);
            case 3:
                return applicationContext.getBean(StudentStrategy.class);
            default:
                throw new IllegalArgumentException("不支持的角色类型: " + sign);
        }
    }
}