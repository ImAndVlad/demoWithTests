package com.example.demowithtests.mapper;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Network;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.NetworkDto;
import com.example.demowithtests.dto.NetworkReadDto;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Employee saveFromDto(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);

    EmployeeReadDto readDto(Employee employee);

    Network saveNetDto(NetworkDto networkDto);

    NetworkDto toNetDto(Network network);

    NetworkReadDto readNetDto(Network network);

}
