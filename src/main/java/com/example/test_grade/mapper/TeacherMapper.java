package com.example.test_grade.mapper;

import com.example.test_grade.entity.Manager;
import com.example.test_grade.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where id=#{id}")
    Teacher getOne(@Param("id")Long id);

    @Insert("insert into teacher(name,password,sex,age,title,managerId) values (#{name},#{password},#{sex},#{age},#{title},#{managerId})")
    void insert(Teacher teacher);

    //与管理员表连接操作，获取管理员号对应的管理员姓名
    @Select("select teacher.*,manager.name as managerName from teacher,manager where teacher.managerId=manager.id")
    public List<Teacher> getAll();

    @Update("update teacher set age=#{age},name=#{name},sex=#{sex},password=#{password},title=#{title},managerId=#{managerId} where id =#{id}")
    void update(Teacher teacher);

    @Delete("delete from teacher where id =#{id}")
    void delete(@Param("id") Long id);

    @Select("select * from teacher where name=#{name} and password = #{password}")
    Teacher findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from teacher where name=#{name};")
    Teacher getTeacherByName(@Param("name") String name);

    //模糊查询（并于管理员表关联显示管理员姓名）
    @Select("select teacher.*,manager.name as managerName from teacher,manager where teacher.managerId=manager.id and teacher.name like concat('%',#{name},'%')")
    public List<Teacher> findByName(@Param("name") String name);
    @Select("select * from teacher where id=#{id}")
    public List<Teacher> getTeacherIdList(@Param("id") Long id);
}
