package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);

    @Query("select e from Employee e where e.country = ?1")
    Page<Employee> findByCountry(String country, Pageable pageable);

    @Query("select e from Employee e where e.name = ?1")
    Page<Employee> findByName(String name, Pageable pageable);

    // get pagination of email(Finds any values that end with ".com")
//    @Query("select e from Employee e where e.email like %?1")
//    Page<Employee> findByEmail(String email, Pageable pageable);

}
