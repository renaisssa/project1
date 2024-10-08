package com.example.test_grade.mapper;

import com.example.test_grade.entity.Manager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ManagerMapper {
    @Select("select * from manager where id=#{id}")
    Manager getOne(@Param("id")Long id);

    @Insert("insert into manager(name,password,sex,age) values (#{name},#{password},#{sex},#{age})")
    void insert(Manager manager);

    @Select("select * from manager")
    public List<Manager> getAll();

    @Update("update manager set age=#{age},name=#{name},sex=#{sex},password=#{password} where id =#{id}")
    void update(Manager manager);

    @Delete("delete from manager where id =#{id}")
    void delete(@Param("id") Long id);

    @Select("select * from manager where name=#{name} and password = #{password}")
    Manager findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from manager where name=#{name};")
    Manager getManagerByName(@Param("name") String name);

    //模糊查询
    @Select("select * from manager where name like concat('%',#{name},'%')")
    public List<Manager> findByName(@Param("name") String name);

    @Select("select * from manager where id=#{id}")
    public List<Manager> getManagerIdList(@Param("id") Long id);
}
