package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    void isDeleted(Integer id);

    List<Employee> getAllUsers();

}
