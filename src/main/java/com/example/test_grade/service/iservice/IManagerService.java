package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IManagerService {
    public Manager getManagerById(Long id);
    public Manager getManagerByName(String name);
    public List<Manager> getManagers();
    public void addManager(Manager manager);
    public void updateManager(Manager manager);
    public void deleteManagerById(Long id);

    Base login(String name, String password);
    public List<Manager> findManagerByName(String name);

    List<Manager> getManagerIdList(Long id);
}
