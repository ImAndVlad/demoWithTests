package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.mapper.Mapper;
import com.example.demowithtests.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final Mapper mapper;


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('delevopers:write')")
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave) {
        log.debug("saveEmployee() Controller - start");
        var employee = mapper.saveFromDto(requestForSave);
        log.debug("saveFromDto() Controller - to dto - start");
        var dto = mapper.toDto(service.create(employee));
        log.debug("saveEmployee() Controller - end");
        return dto;
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('delevopers:read')")
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('delevopers:read')")
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
        var dto = mapper.readDto(employee);
        dto.date = service.getDate();
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;
    }

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('delevopers:write')")
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {

        return service.updateById(id, employee);
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('delevopers:write')")
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('delevopers:write')")
    public void removeAllUsers() {
        service.removeAll();
    }

    // get pagination of country
//    @GetMapping("/users/country")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "This is endpoint returned a list employees by country(pagination).",
//            description = "Create request to read a list employees by country", tags = {"Employee"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
//            @ApiResponse(responseCode = "400", description = "Invalid input"),
//            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
//            @ApiResponse(responseCode = "409", description = "Employee already exists")})
//    public Page<Employee> findByCountry(@RequestParam(required = false) String country,
//                                        @RequestParam(defaultValue = "0") int page,
//                                        @RequestParam(defaultValue = "3") int size) {
//        Pageable paging = PageRequest.of(page, size, Sort.by("id").ascending());
//
//        return service.findByCountry(country, paging);
//    }

    // get pagination of name
    @GetMapping("/users/name")
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findByName(@RequestParam(required = false) String name,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "") List<String> sortList,
                                        @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {

        return service.findByNameString(name, page, size, sortList, sortOrder.toString());
    }

    // get pagination of email(Finds any values that end with ".com")
//    @Override
//    public Page<Employee> findByEmail(String email, int page, int size, List<String> sortList, Sort.Direction sortOrder) {
//        return service.findByEmail(email, page, size, sortList, sortOrder.toString());
//    }

//    @GetMapping("/users/e")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> getEmail() {
//        return service.getByEmail();
//    }

    @PutMapping("/users/{empId}/network/{netId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeReadDto addNetworkToEmployee(
            @PathVariable Integer empId,
            @PathVariable Integer netId
    ) {
        var dto = mapper.readDto(service.networkToEmployee(empId, netId));
        dto.date = service.getDate();
        return dto;
    }
}
