package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Network;
import com.example.demowithtests.repository.NetworkRepository;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.DateMapper;
import com.example.demowithtests.util.SortList;
import com.example.demowithtests.util.exception.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;
    private final NetworkRepository networkRepository;
    private final SortList sort;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        var employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
//                .orElseThrow(ResourceNotFoundException::new);
         /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/
        return employee;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setSurname(employee.getSurname());
                    entity.setEmail(employee.getEmail());
                    entity.setAddresses(employee.getAddresses());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = repository.findById(id)
               // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        repository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();

    }

    @Override
    public String getDate() {
        return DateMapper.asString(Date.from(Instant.now()));
    }

//    @Override
//    public Page<Employee> findByCountry(String country, Pageable pageable) {
//        return repository.findByCountry(country, pageable);
//    }

    @Override
    public Page<Employee> findByNameString(String name, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return repository.findByName(name, pageable);
    }

//    @Override
//    public Page<Employee> findByEmail(String email, int page, int size, List<String> sortList, String sortOrder) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.createSortOrder(sortList, sortOrder)));
//
//        return repository.findByEmail(email, pageable);
//    }

//    @Override
//    public List<String> getByEmail() {
//        List<Employee> employeesList = repository.findAll();
//
//        return employeesList.stream()
//                .map(Employee::getEmail)
//                .filter(c -> c.endsWith(".com"))
//                .sorted(Comparator.naturalOrder())
//                .collect(Collectors.toList());
//    }


    @Override
    public Employee networkToEmployee(Integer empId, Integer netId) {
        Set<Network> networkSet = null;
        var employee = repository.findById(empId).get();
        var network = networkRepository.findById(netId).get();

        networkSet = employee.getNetworks();
        networkSet.add(network);
        employee.setNetworks(networkSet);

        return repository.save(employee);
    }
}
