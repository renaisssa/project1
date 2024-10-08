package com.example.test_grade.mapper;

import com.example.test_grade.entity.Education;
import com.example.test_grade.entity.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GradeMapper {
    //grade表与course表和student表连接，获取某学生的成绩信息
    @Select("select grade.*,course.name as courseName,student.name as studentName \n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and grade.studentId=#{studentId}")
    public List<Grade> getOne(@Param("studentId")Long studentId);

    @Insert("insert into grade(studentId,courseId,type,regularscore,volumescore,score,gradepoint) values (#{studentId},#{courseId},#{type},#{regularscore},#{volumescore},#{score},#{gradepoint})")
    void insert(Grade grade);
    //grade表与course表和student表连接，获取全部学生的成绩信息
    @Select("select grade.*,course.name as courseName,student.name as studentName \n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id")
    public List<Grade> getAll();

    @Update("update grade set type=#{type},regularscore=#{regularscore},volumescore=#{volumescore},score=#{score},gradepoint=#{gradepoint} where studentId =#{studentId} and courseId=#{courseId}")
    void update(Grade grade);

    @Delete("delete from grade where studentId =#{studentId} and courseId=#{courseId}")
    void delete(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    @Select("select * from education where teacherId=#{teacherId};")
    Education getEducationByTeacherId(@Param("teacherId") Long teacherId);

    @Select("select * from grade where studentId=#{studentId} and courseId =#{courseId};")
    Grade getGrade(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    //模糊查询
    @Select("select grade.*,course.name as courseName,student.name as studentName\n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and course.name like concat('%',#{courseName},'%')")
    public List<Grade> findByName(@Param("courseName") String courseName);

    //模糊查询
    @Select("select grade.*,course.name as courseName,student.name as studentName\n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and student.name like concat('%',#{studentName},'%')")
    public List<Grade> findByStudentName(@Param("studentName") String studentName);
    @Select("select grade.*,course.name as courseName,student.name as studentName \n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id \n"+
            "order by grade.score desc ")
    public List<Grade> descAllScore();

    //模糊查询
    @Select("select grade.*,course.name as courseName,student.name as studentName\n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and course.name like concat('%',#{courseName},'%') \n"+
            "order by grade.score desc")
    public List<Grade> findByNameDesc(@Param("courseName") String courseName);

    //模糊查询
    @Select("select grade.*,course.name as courseName,student.name as studentName\n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and student.name like concat('%',#{studentName},'%') \n" +
            "order by grade.score desc")
    public List<Grade> findByStudentNameDesc(@Param("studentName") String studentName);

    @Select("select grade.*,course.name as courseName,student.name as studentName \n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and grade.studentId=#{studentId} \n"+
            "order by grade.score desc")
    public List<Grade> getOneDesc(@Param("studentId")Long studentId);

    @Select("select grade.*,course.name as courseName,student.name as studentName \n" +
            "from grade,course,student \n" +
            "where grade.courseId=course.id and grade.studentId=student.id and grade.studentId=#{studentId} and course.name like concat('%',#{courseName},'%')")
    public List<Grade> findName(@Param("studentId")Long studentId,@Param("courseName") String courseName);

    //使用聚合函数算出平均学分绩点
    @Select("SELECT sum(grade.gradepoint*course.credit)/sum(course.credit)\n" +
            "FROM grade,course,student\n" +
            "WHERE grade.courseId=course.id and grade.studentId=student.id and studentId=#{studentId};")
    public String getGPA(@Param("studentId")Long studentId);
}
