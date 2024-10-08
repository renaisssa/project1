package com.example.test_grade.mapper;

import com.example.test_grade.entity.Student;
import com.example.test_grade.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("select * from student where id=#{id}")
    Student getOne(@Param("id")Long id);

    @Insert("insert into student(name,password,sex,age,profession,managerId) values (#{name},#{password},#{sex},#{age},#{profession},#{managerId})")
    void insert(Student student);

    //与管理员表连接操作，获取管理员号对应的管理员姓名
    @Select("select student.*,manager.name as managerName from student,manager where student.managerId=manager.id")
    public List<Student> getAll();

    @Update("update student set age=#{age},name=#{name},sex=#{sex},profession=#{profession},password=#{password},managerId=#{managerId} where id =#{id}")
    void update(Student student);

    @Delete("delete from student where id =#{id}")
    void delete(@Param("id") Long id);

    @Select("select * from student where name=#{name} and password = #{password}")
    Student findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from student where name=#{name};")
    Student getStudentByName(@Param("name") String name);

    //模糊查询（并于管理员表关联显示管理员姓名）
    @Select("select student.*,manager.name as managerName from student,manager where student.managerId=manager.id and student.name like concat('%',#{name},'%')")
    public List<Student> findByName(@Param("name") String name);
    @Select("select * from student where id=#{id}")
    public List<Student> getStudentIdList(@Param("id") Long id);

}
