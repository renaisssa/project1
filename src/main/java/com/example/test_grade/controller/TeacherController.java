package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    //修改教师信息
    @PutMapping
    public Result update(@RequestBody Teacher teacher){
        if (ObjectUtil.isEmpty(teacher)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        teacherService.updateTeacher(teacher);
        return Result.success();
    }

    //新增教师信息
    @PostMapping
    public Result add(@RequestBody Teacher teacher){
        //信息未填写完整
        if(ObjectUtil.isEmpty(teacher.getName())||ObjectUtil.isEmpty(teacher.getSex())||ObjectUtil.isEmpty(teacher.getAge())||ObjectUtil.isEmpty(teacher.getTitle())||ObjectUtil.isEmpty(teacher.getManagerId())){
            return Result.fail(-1,"请完善输入信息");
        }
        //新增教师name不能重复
        Teacher teacher1=teacherService.getTeacherByName(teacher.getName());
        if(ObjectUtil.isNotEmpty(teacher1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }
        //新增教师设置初始化密码
        if(ObjectUtil.isEmpty(teacher.getPassword())){
            teacher.setPassword("123456");
        }
        teacherService.register(teacher);
        return Result.success();
    }

    //查询全部教师数据
    @GetMapping
    public Result getAllTeachers(){
        List<Teacher> list=teacherService.getTeachers();
        return Result.success(list);
    }

    //删除教师
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        Teacher teacher=teacherService.getTeacherById(id);
        if(ObjectUtil.isEmpty(teacher)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        teacherService.deleteTeacherById(id);
        return Result.success();
    }

    //根据姓名模糊查询
    @GetMapping("/{name}")
    public Result findTeacherByName(@PathVariable String name){
        List<Teacher> list=teacherService.findTeacherByName(name);
        return Result.success(list);
    }

}
