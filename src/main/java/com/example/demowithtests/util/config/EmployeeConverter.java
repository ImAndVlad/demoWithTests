package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.read.EmployeeHourDto;
import com.example.demowithtests.dto.read.EmployeeReadDto;
import com.example.demowithtests.dto.read.EmployeeReadStatusDto;
import com.example.demowithtests.dto.save.EmployeeDto;
import com.example.demowithtests.dto.save.EmployeeSaveAccessDto;
import com.example.demowithtests.dto.save.EmployeeSaveHourDto;
import com.example.demowithtests.dto.update.EmployeeUpdateDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    private final MapperFacade mapperFacade;

    public EmployeeConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public EmployeeDto toDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeDto.class);
    }

    public EmployeeReadDto toReadDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadDto.class);
    }

    public Employee fromDto(EmployeeDto dto) {
        return mapperFacade.map(dto, Employee.class);
    }

    // save employee hour
    public EmployeeSaveHourDto saveDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeSaveHourDto.class);
    }

    public EmployeeSaveAccessDto saveTwoDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeSaveAccessDto.class);
    }

    public EmployeeHourDto hourEmployee(Employee entity) {
        return mapperFacade.map(entity, EmployeeHourDto.class);
    }

    // new read
    public EmployeeReadStatusDto toReadStatusDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadStatusDto.class);
    }

    public EmployeeUpdateDto toUpdateDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeUpdateDto.class);
    }
}
