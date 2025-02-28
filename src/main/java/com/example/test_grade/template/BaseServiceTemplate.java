package com.example.test_grade.template;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.common.Result;
import com.example.test_grade.common.ResultCode;
import com.example.test_grade.entity.Base;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// 抽象模板类
public abstract class BaseServiceTemplate<T extends Base> {
    @Autowired
    protected Result result;

    // 新增操作模板方法
    public Result add(T entity) {
        // 信息完整性检查
        if (!checkAddInfo(entity)) {
            return Result.fail(-1, "请完善输入信息");
        }
        // 名称唯一性检查
        if (isNameDuplicated(entity)) {
            return Result.fail(ResultCode.NAME_DUPLICATED);
        }
        // 设置初始化密码
        setInitialPassword(entity);
        // 具体的新增操作
        doAdd(entity);
        return Result.success();
    }

    // 修改操作模板方法
    public Result update(T entity) {
        if (ObjectUtil.isEmpty(entity)) {
            return Result.fail(ResultCode.NOT_FOUND);
        }
        doUpdate(entity);
        return Result.success();
    }

    // 删除操作模板方法
    public Result deleteById(Long id) {
        T entity = getById(id);
        if (ObjectUtil.isEmpty(entity)) {
            return Result.fail(ResultCode.ID_NOT_FOUND);
        }
        doDeleteById(id);
        return Result.success();
    }

    // 查询全部数据模板方法
    public Result getAll() {
        List<T> list = doGetAll();
        return Result.success(list);
    }

    // 根据姓名模糊查询模板方法
    public Result findByName(String name) {
        List<T> list = doFindByName(name);
        return Result.success(list);
    }

    // 检查新增信息完整性，由子类实现
    protected abstract boolean checkAddInfo(T entity);

    // 检查名称是否重复，由子类实现
    protected abstract boolean isNameDuplicated(T entity);

    // 设置初始化密码，由子类实现
    protected abstract void setInitialPassword(T entity);

    // 具体的新增操作，由子类实现
    protected abstract void doAdd(T entity);

    // 具体的修改操作，由子类实现
    protected abstract void doUpdate(T entity);

    // 具体的删除操作，由子类实现
    protected abstract void doDeleteById(Long id);

    // 具体的查询全部数据操作，由子类实现
    protected abstract List<T> doGetAll();

    // 具体的根据姓名模糊查询操作，由子类实现
    protected abstract List<T> doFindByName(String name);

    // 根据 ID 获取实体，由子类实现
    protected abstract T getById(Long id);
}