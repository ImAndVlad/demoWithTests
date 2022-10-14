package com.example.demowithtests.dto;

import com.example.demowithtests.util.DateMapper;

import java.time.Instant;
import java.util.Date;


public class AddressDto {

    public Long id;

    public Boolean addressHasActive = Boolean.TRUE;

    public String country;

    public String city;

    public String street;

    public String date = DateMapper.asString(Date.from(Instant.now()));

}
