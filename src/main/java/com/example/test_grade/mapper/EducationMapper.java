package com.example.test_grade.mapper;

import com.example.test_grade.entity.Course;
import com.example.test_grade.entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EducationMapper {
    @Select("select education.*,teacher.name as teacherName,student.name as studentName \n" +
            "from education,teacher,student \n" +
            "where education.teacherId=teacher.id and education.studentId=student.id and education.studentId=#{studentId}")
    public List<Education> getOne(@Param("studentId")Long studentId);

    @Insert("insert into education(teacherId,studentId,comment) values (#{teacherId},#{studentId},#{comment})")
    void insert(Education education);

    @Select("select education.*,teacher.name as teacherName,student.name as studentName \n" +
            "from education,teacher,student \n" +
            "where education.teacherId=teacher.id and education.studentId=student.id")
    public List<Education> getAll();

    @Update("update education set comment=#{comment} where studentId =#{studentId} and teacherId=#{teacherId}")
    void update(Education education);

    @Delete("delete from education where studentId =#{studentId} and teacherId=#{teacherId}")
    void delete(@Param("studentId") Long studentId,@Param("teacherId") Long teacherId);

    @Select("select * from education where teacherId=#{teacherId};")
    Education getEducationByTeacherId(@Param("teacherId") Long teacherId);

    @Select("select * from education where teacherId=#{teacherId} and studentId =#{studentId};")
    Education getEducation(@Param("teacherId") Long teacherId,@Param("studentId") Long studentId);

    //模糊查询
    @Select("select education.*,teacher.name as teacherName,student.name as studentName\n" +
            "from education,teacher,student \n" +
            "where education.teacherId=teacher.id and education.studentId=student.id and education.studentId=#{studentId} and teacher.name like concat('%',#{teacherName},'%')")
    public List<Education> findByName(@Param("studentId")Long studentId,@Param("teacherName") String teacherName);

    @Select("select education.*,teacher.name as teacherName,student.name as studentName \n" +
            "from education,teacher,student \n" +
            "where education.teacherId=teacher.id and education.studentId=student.id and education.teacherId=#{teacherId}")
    public List<Education> getStudentsByTeacher(@Param("teacherId")Long teacherId);
}
