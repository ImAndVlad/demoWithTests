package com.example.demowithtests.dto.save;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// save employee
public class EmployeeSaveAccessDto {

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    @Email
    @NotNull
    @Schema(description = "Email address of an employee.", example = "billys@mail.com", required = true)
    public String email;

    @NotNull
    @Schema(description = "Status access employee.", example = "true", required = true)
    public boolean access = true;

    public String status = "Save employee. Employee access - admin";
}
