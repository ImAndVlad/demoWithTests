package com.example.demowithtests.service.networkService;

import com.example.demowithtests.domain.Network;

import java.util.List;

public interface NetworkService {

    Network create(Network network);

    List<Network> getAll();

    Network getById(Integer id);

    Network updateById(Integer id, Network network);

    void removeById(Integer id);
}
