package com.example.test_grade.service.iservice;

import com.example.test_grade.entity.Base;
import com.example.test_grade.entity.Manager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IManagerService {
    Base login(String name, String password);
//    public Manager getManagerByName(String name);
//    public List<Manager> findManagerByName(String name);
//
//    List<Manager> getManagerIdList(Long id);
    //    public Manager getManagerById(Long id);
    //    public List<Manager> getManagers();
//    public void addManager(Manager manager);
//    public void updateManager(Manager manager);
//    public void deleteManagerById(Long id);
}
