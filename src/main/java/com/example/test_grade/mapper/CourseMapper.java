package com.example.test_grade.mapper;

import com.example.test_grade.entity.Course;
import com.example.test_grade.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from course where id=#{id}")
    Course getOne(@Param("id")Long id);

    @Insert("insert into course(name,credit,time,place,period,classno,teacherId) values (#{name},#{credit},#{time},#{place},#{period},#{classno},#{teacherId})")
    void insert(Course course);

    @Select("select course.*,teacher.name as teacherName from course,teacher where course.teacherId=teacher.id")
    public List<Course> getAll();

    @Update("update course set credit=#{credit},name=#{name},time=#{time},place=#{place},period=#{period},classno=#{classno},teacherId=#{teacherId} where id =#{id}")
    void update(Course course);

    @Delete("delete from course where id =#{id}")
    void delete(@Param("id") Long id);

    @Select("select * from course where name=#{name};")
    Course getCourseByName(@Param("name") String name);

    //模糊查询
    @Select("select course.*,teacher.name as teacherName from course,teacher where course.teacherId=teacher.id and course.name like concat('%',#{name},'%')")
    public List<Course> findByName(@Param("name") String name);

    @Select("select course.*,teacher.name as teacherName from course,teacher where course.teacherId=teacher.id and course.teacherId=#{teacherId}")
    public List<Course> getCoursesByTeacher(@Param("teacherId")Long teacherId);

}
