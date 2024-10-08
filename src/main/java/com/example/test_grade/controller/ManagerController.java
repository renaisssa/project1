package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    //修改管理员信息
    @PutMapping
    public Result update(@RequestBody Manager manager){
        if (ObjectUtil.isEmpty(manager)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        managerService.updateManager(manager);
        return Result.success();
    }

    //新增管理员信息
    @PostMapping
    public Result add(@RequestBody Manager manager){
        //信息未填写完整
        if(ObjectUtil.isEmpty(manager.getName())||ObjectUtil.isEmpty(manager.getSex())||ObjectUtil.isEmpty(manager.getAge())){
            return Result.fail(-1,"请完善输入信息");
        }
        //新增管理员name不能重复
        Manager manager1=managerService.getManagerByName(manager.getName());
        if(ObjectUtil.isNotEmpty(manager1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }
        //新增管理员设置初始化密码
        if(ObjectUtil.isEmpty(manager.getPassword())){
            manager.setPassword("123456");
        }
        managerService.addManager(manager);
        return Result.success();
    }

    //查询全部管理员数据
    @GetMapping
    public Result getAllManagers(){
        List<Manager> list=managerService.getManagers();
        return Result.success(list);
    }

    //删除管理员
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        Manager manager=managerService.getManagerById(id);
        if(ObjectUtil.isEmpty(manager)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        managerService.deleteManagerById(id);
        return Result.success();
    }

    //根据姓名模糊查询
    @GetMapping("/{name}")
    public Result findManagerByName(@PathVariable String name){
        List<Manager> list=managerService.findManagerByName(name);
        return Result.success(list);
    }


}
