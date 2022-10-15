package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    String getDate();

    Page<Employee> findByCountry(String country, Pageable pageable);

    /**
     * It returns a page of employees with the given name.
     *
     * @param name The name of the employee you want to search for.
     * @param page The page number to be returned.
     * @param size The number of records per page.
     * @param sortList A list of strings that represent the sort fields.
     * @param sortOrder "asc" or "desc"
     * @return A Page<Employee> object.
     */
    Page<Employee> findByNameString (String name, int page, int size, List<String> sortList, String sortOrder);


    /**
     * Find all employees with the given email address, and return them in a page of size, starting at page,
     * sorted by the given sortList in the given sortOrder.
     *
     * @param email The email address of the employee.
     * @param page The page number to retrieve.
     * @param size The number of records per page.
     * @param sortList A list of fields to sort by.
     * @param sortOrder The sort order, either "asc" or "desc".
     * @return A Page<Employee> object.
     */
//    Page<Employee> findByEmail(String email, int page, int size, List<String> sortList, String sortOrder);

//    List<String> getByEmail();
}
