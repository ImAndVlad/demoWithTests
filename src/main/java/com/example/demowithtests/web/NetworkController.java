package com.example.demowithtests.web;

import com.example.demowithtests.domain.Network;
import com.example.demowithtests.dto.NetworkDto;
import com.example.demowithtests.dto.NetworkReadDto;
import com.example.demowithtests.mapper.Mapper;
import com.example.demowithtests.service.NetworkService.NetworkService;
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
@Tag(name = "Network", description = "Network API")
public class NetworkController {

    private final NetworkService service;
    private final Mapper mapper;

    @PostMapping("/networks")
    @ResponseStatus(HttpStatus.CREATED)
    public NetworkDto saveNetwork(@RequestBody @Valid NetworkDto requestForSave) {
        var network = mapper.saveNetDto(requestForSave);
        var dto = mapper.toNetDto(service.create(network));
        return dto;
    }

    @GetMapping("/networks")
    @ResponseStatus(HttpStatus.OK)
    public List<Network> getAllNetworks() {
        return service.getAll();
    }

    @GetMapping("/networks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NetworkReadDto getNetworkById(@PathVariable Integer id) {
        var network = service.getById(id);
        var dto = mapper.readNetDto(network);
        return dto;
    }

    @PutMapping("/networks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Network refreshNetwork(@PathVariable("id") Integer id, @RequestBody Network network) {

        return service.updateById(id, network);
    }

    @PatchMapping("/networks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeNetworkById(@PathVariable Integer id) {
        service.removeById(id);
    }
}
