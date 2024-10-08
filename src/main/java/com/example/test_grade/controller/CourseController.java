package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.*;
import com.example.test_grade.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //新增课程信息
    @PostMapping
    public Result add(@RequestBody Course course){
        //信息未填写完整
        if(ObjectUtil.isEmpty(course.getName())||ObjectUtil.isEmpty(course.getCredit())||ObjectUtil.isEmpty(course.getTime())||ObjectUtil.isEmpty(course.getPlace())||ObjectUtil.isEmpty(course.getPeriod())||ObjectUtil.isEmpty(course.getClassno())||ObjectUtil.isEmpty(course.getTeacherId())){
            return Result.fail(-1,"请完善输入信息");
        }
        //新增课程名不能重复
        Course course1=courseService.getCourseByName(course.getName());
        if(ObjectUtil.isNotEmpty(course1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }
        courseService.insert(course);
        return Result.success();
    }

    //查询全部课程数据
    @GetMapping
    public Result getAllCourses(){
        List<Course> list=courseService.getCourses();
        return Result.success(list);
    }

    //修改信息
    @PutMapping
    public Result update(@RequestBody Course course){
        if (ObjectUtil.isEmpty(course)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        courseService.updateCourse(course);
        return Result.success();
    }

    //删除课程
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        Course course=courseService.getCourseById(id);
        if(ObjectUtil.isEmpty(course)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        courseService.deleteCourseById(id);
        return Result.success();
    }

    //模糊查询
    @GetMapping("/{name}")
    public Result findCourseByName(@PathVariable String name){
        List<Course> list=courseService.findCourseByName(name);
        return Result.success(list);
    }

    //获取当前登录老师所教的课程
    @GetMapping("/coursebyteacher")
    public Result getCoursesByTeacher(HttpServletRequest request){
        //获取当前登录的教师id
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Course> list=courseService.getCoursesByTeacher(user.getId());
        return Result.success(list);
    }
}
