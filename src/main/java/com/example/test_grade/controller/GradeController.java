package com.example.test_grade.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Course;
import com.example.test_grade.entity.Education;
import com.example.test_grade.entity.Grade;
import com.example.test_grade.service.GradeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    //查询当前登录学生个人成绩数据
    @GetMapping
    public Result getOneGrades(HttpServletRequest request){
        //从session中获取当前登录的学生
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Grade> list=gradeService.getOneGrades(user.getId());
        return Result.success(list);
    }

    //查询全部成绩数据
    @GetMapping("/all")
    public Result getAllGrades(){
        List<Grade> list=gradeService.getGrades();
        return Result.success(list);
    }
    //根据课程名模糊查询
    @GetMapping("/course/{courseName}")
    public Result findBycourseName(@PathVariable String courseName){
        List<Grade> list=gradeService.findByName(courseName);
        return Result.success(list);
    }
    //根据学生姓名模糊查询
    @GetMapping("/student/{studentName}")
    public Result findBystudentName(@PathVariable String studentName){
        List<Grade> list=gradeService.findByStudentName(studentName);
        return Result.success(list);
    }

    //新增信息
    @PostMapping
    public Result add(@RequestBody Grade grade){
        //信息未填写完整
        if(ObjectUtil.isEmpty(grade.getStudentId())||ObjectUtil.isEmpty(grade.getCourseId())||ObjectUtil.isEmpty(grade.getType())||ObjectUtil.isEmpty(grade.getRegularscore())||ObjectUtil.isEmpty(grade.getVolumescore())){
            return Result.fail(-1,"请完善输入信息");
        }
        if(grade.getRegularscore()>100||grade.getVolumescore()>100||grade.getRegularscore()<0||grade.getVolumescore()<0){
            return Result.fail(-1,"成绩取值应在0-100之间");
        }
        //新增学生id和课程id不能同时重复
        Grade grade1=gradeService.getGrade(grade.getStudentId(),grade.getCourseId());
        if(ObjectUtil.isNotEmpty(grade1)){
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }

        Double score=grade.getRegularscore()*0.3+grade.getVolumescore()*0.7;

        //设置总分
        if(ObjectUtil.isEmpty(grade.getScore())){
            grade.setScore(score);
        }
        //设置绩点
        if(ObjectUtil.isEmpty(grade.getGradepoint())){
            if(score>=90&&score<=100){
                grade.setGradepoint("4.0");
            } else if (score>=85&&score<=89) {
                grade.setGradepoint("3.7");
            } else if (score>=82&&score<=84) {
                grade.setGradepoint("3.3");
            } else if (score>=78&&score<=81) {
                grade.setGradepoint("3.0");
            } else if (score>=75&&score<=77) {
                grade.setGradepoint("2.7");
            } else if (score>=72&&score<=74) {
                grade.setGradepoint("2.3");
            } else if (score>=68&&score<=71) {
                grade.setGradepoint("2.0");
            } else if (score>=64&&score<=67) {
                grade.setGradepoint("1.5");
            } else if (score>=60&&score<=63) {
                grade.setGradepoint("1.0");
            }else {
                grade.setGradepoint("0");
            }
        }
        gradeService.insert(grade);
        return Result.success();
    }

    //修改信息
    @PutMapping
    public Result update(@RequestBody Grade grade){
        if (ObjectUtil.isEmpty(grade)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }

        Double score=grade.getRegularscore()*0.3+grade.getVolumescore()*0.7;

        grade.setScore(score);
        //设置绩点
        if(score>=90&&score<=100){
                grade.setGradepoint("4.0");
            } else if (score>=85&&score<=89) {
                grade.setGradepoint("3.7");
            } else if (score>=82&&score<=84) {
                grade.setGradepoint("3.3");
            } else if (score>=78&&score<=81) {
                grade.setGradepoint("3.0");
            } else if (score>=75&&score<=77) {
                grade.setGradepoint("2.7");
            } else if (score>=72&&score<=74) {
                grade.setGradepoint("2.3");
            } else if (score>=68&&score<=71) {
                grade.setGradepoint("2.0");
            } else if (score>=64&&score<=67) {
                grade.setGradepoint("1.5");
            } else if (score>=60&&score<=63) {
                grade.setGradepoint("1.0");
            }else {
                grade.setGradepoint("0");
            }
        gradeService.updateGrade(grade);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{studentId}/{courseId}")
    public Result delete(@PathVariable("studentId") Long studentId,@PathVariable("courseId") Long courseId){
        Grade grade=gradeService.getGrade(studentId,courseId);
        if(ObjectUtil.isEmpty(grade)){
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        gradeService.delete(studentId,courseId);
        return Result.success();
    }

    //按成绩降序排序
    @GetMapping("/desc")
    public Result descAllScore(){
        List<Grade> list=gradeService.descAllScore();
        return Result.success(list);
    }
    //根据课程名模糊查询降序
    @GetMapping("/course/desc/{courseName}")
    public Result findBycourseNameDesc(@PathVariable String courseName){
        List<Grade> list=gradeService.findByNameDesc(courseName);
        return Result.success(list);
    }
    //根据学生姓名模糊查询降序
    @GetMapping("/student/desc/{studentName}")
    public Result findBystudentNameDesc(@PathVariable String studentName){
        List<Grade> list=gradeService.findByStudentNameDesc(studentName);
        return Result.success(list);
    }

    //查询当前学生个人成绩数据降序
    @GetMapping("/oneDesc")
    public Result getOneGradesDesc(HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Grade> list=gradeService.getOneGradesDesc(user.getId());
        return Result.success(list);
    }

    //根据课程名模糊查询个人成绩
    @GetMapping("/{courseName}")
    public Result findName(@PathVariable String courseName,HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        List<Grade> list=gradeService.findName(user.getId(),courseName);
        return Result.success(list);
    }

    //获取平均学分绩点
    @GetMapping("/gpa")
    public Result getGPA(HttpServletRequest request){
        Base user=(Base) request.getSession().getAttribute("user");
        if(ObjectUtil.isEmpty(user)){
            return Result.fail(-1,"登陆已失效");
        }
        String gpa=gradeService.getOneGPA(user.getId());
        return Result.success(gpa);
    }
}
