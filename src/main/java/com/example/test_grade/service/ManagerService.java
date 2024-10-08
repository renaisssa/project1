package com.example.test_grade.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import com.example.test_grade.mapper.ManagerMapper;
import com.example.test_grade.service.iservice.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public class ManagerService implements IManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager getManagerById(Long id) {
        return managerMapper.getOne(id);
    }

    @Override
    public Manager getManagerByName(String name) {
        return managerMapper.getManagerByName(name);
    }

    @Override
    public List<Manager> getManagers() {
        return managerMapper.getAll();
    }


    @Override
    public void addManager(Manager manager) {
        managerMapper.insert(manager);
    }

    @Override
    public void updateManager(Manager manager) {
        managerMapper.update(manager);
    }

    @Override
    public void deleteManagerById(Long id) {
        managerMapper.delete(id);
    }

    @Override
    public Base login(String name, String password) {
        Manager manager = managerMapper.findByNameAndPassword(name,password);
        return manager;
    }

    @Override
    public List<Manager> findManagerByName(String name) {
        return managerMapper.findByName(name);
    }

    @Override
    public List<Manager> getManagerIdList(Long id) {
        return managerMapper.getManagerIdList(id);
    }

}
