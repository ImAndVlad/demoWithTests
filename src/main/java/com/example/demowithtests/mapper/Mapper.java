package com.example.demowithtests.mapper;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Employee saveFromDto(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

    EmployeeReadDto readDto(Employee employee);


}
