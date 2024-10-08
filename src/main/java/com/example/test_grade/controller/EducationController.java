package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.*;
import com.example.test_grade.mapper.StudentMapper;
import com.example.test_grade.service.EducationService;
import com.example.test_grade.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    //查询当前学生全部评价数据
    @GetMapping
    public Result getOneEducations(HttpServletRequest request){
        //获取当前登录的学生id
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Education> list=educationService.getOneEducations(user.getId());
        return Result.success(list);
    }

    //新增信息
    @PostMapping
    public Result add(@RequestBody Education education,HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        education.setStudentId(user.getId());
        //信息未填写完整
        if(ObjectUtil.isEmpty(education.getTeacherId())||ObjectUtil.isEmpty(education.getComment())){
            return Result.fail(-1,"请完善输入信息");
        }
        //新增教师id和学生id不能同时重复
        Education education1=educationService.getEducation(education.getTeacherId(),education.getStudentId());
        if(ObjectUtil.isNotEmpty(education1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }

        educationService.insert(education);
        return Result.success();
    }

    //修改信息
    @PutMapping
    public Result update(@RequestBody Education education){
        if (ObjectUtil.isEmpty(education)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        educationService.updateEducation(education);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{studentId}/{teacherId}")
    public Result delete(@PathVariable("teacherId") Long teacherId,@PathVariable("studentId") Long studentId){
        Education education=educationService.getEducation(teacherId,studentId);
        if(ObjectUtil.isEmpty(education)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        educationService.delete(studentId,teacherId);
        return Result.success();
    }

    //根据老师姓名模糊查询
    @GetMapping("/{teacherName}")
    public Result findByteacherName(@PathVariable String teacherName,HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Education> list=educationService.findByName(user.getId(),teacherName);
        return Result.success(list);
    }

    //获取老师教的学生
    @GetMapping("/studentbyteacher")
    public Result getStudentsByTeacher(HttpServletRequest request){
        //获取当前登录的教师id
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Education> list=educationService.getStudentsByTeacher(user.getId());
        return Result.success(list);
    }
}
