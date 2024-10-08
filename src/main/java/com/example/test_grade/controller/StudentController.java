package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Student;
import com.example.test_grade.entity.Teacher;
import com.example.test_grade.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //修改学生信息
    @PutMapping
    public Result update(@RequestBody Student student){
        if (ObjectUtil.isEmpty(student)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        studentService.updateStudent(student);
        return Result.success();
    }

    //新增学生信息
    @PostMapping
    public Result add(@RequestBody Student student){
        //信息未填写完整
        if(ObjectUtil.isEmpty(student.getName()) ||ObjectUtil.isEmpty(student.getSex()) ||ObjectUtil.isEmpty(student.getAge()) ||ObjectUtil.isEmpty(student.getProfession()) ||ObjectUtil.isEmpty(student.getManagerId())){
            return Result.fail(-1,"请完善输入信息");
        }
        //新增学生name不能重复
        Student student1=studentService.getStudentByName(student.getName());
        if(ObjectUtil.isNotEmpty(student1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }
        //新增学生设置初始化密码
        if(ObjectUtil.isEmpty(student.getPassword())){
            student.setPassword("123456");
        }
        studentService.register(student);
        return Result.success();
    }

    //查询全部学生数据
    @GetMapping
    public Result getAllStudents(){
        List<Student> list=studentService.getStudents();
        return Result.success(list);
    }

    //删除学生
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        Student student=studentService.getStudentById(id);
        if(ObjectUtil.isEmpty(student)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        studentService.deleteStudentById(id);
        return Result.success();
    }

    //根据姓名模糊查询
    @GetMapping("/{name}")
    public Result findStudentByName(@PathVariable String name){
        List<Student> list=studentService.findStudentByName(name);
        return Result.success(list);
    }

}
