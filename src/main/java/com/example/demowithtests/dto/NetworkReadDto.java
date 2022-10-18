package com.example.demowithtests.dto;

import com.example.demowithtests.util.DateMapper;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class NetworkReadDto {

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String name;

    public String date = DateMapper.asString(Date.from(Instant.now()));
}
