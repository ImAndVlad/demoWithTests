package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.read.EmployeeReadDto;
import com.example.demowithtests.dto.read.EmployeeReadStatusDto;
import com.example.demowithtests.dto.save.EmployeeDto;
import com.example.demowithtests.dto.save.EmployeeSaveAccessDto;
import com.example.demowithtests.dto.save.EmployeeSaveHourDto;
import com.example.demowithtests.dto.update.EmployeeUpdateDto;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.EmployeeConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller {

    private final Service service;
    private final EmployeeConverter converter;
    private final Repository repository;


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.toDto(service.create(employee));

        return dto;
    }

    // save employee
    @PostMapping("/users/hour")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeSaveHourDto saveHourEmployee(@RequestBody @Valid EmployeeSaveHourDto requestForSave) {

        log.debug("saveHourEmployee() Controller - start");
        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.saveDto(service.create(employee));
        log.debug("saveHourEmployee() Controller - end");

        return dto;
    }

    // save employee
    @PostMapping("/users/access")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeSaveAccessDto saveAccessEmployee(@RequestBody @Valid EmployeeSaveAccessDto requestForSave) {

        log.debug("saveAccessEmployee() Controller - start");
        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.saveTwoDto(service.create(employee));
        log.debug("saveAccessEmployee() Controller - end");

        return dto;
    }


    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description = "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toReadDto(employee);
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;
    }

    // new №1 read
    @GetMapping("/usersAccess/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadStatusDto getById(@PathVariable Integer id) {
        log.debug("getById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toReadStatusDto(employee);
        log.debug("getById() Controller - end: name = {}", dto.name);
        return dto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody @Valid Employee employee) {

        return service.updateById(id, employee);
    }

    @PutMapping("/usersUp/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdateDto updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        log.debug("updateEmployee() Controller - start: id = {}", id);
        service.updateById(id, employee);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toUpdateDto(employee);
        log.debug("updateEmployee() Controller - end: name = {}", dto.name);
        return dto;
    }


    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }
}
