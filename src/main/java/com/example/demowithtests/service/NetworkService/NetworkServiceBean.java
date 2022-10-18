package com.example.demowithtests.service.NetworkService;

import com.example.demowithtests.domain.Network;
import com.example.demowithtests.repository.NetworkRepository;
import com.example.demowithtests.util.exception.ResourceWasDeletedException;
import lombok.AllArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class NetworkServiceBean implements NetworkService {

    private final NetworkRepository repository;

    @Override
    public Network create(Network network) {
        return repository.save(network);
    }

    @Override
    public List<Network> getAll() {
        return repository.findAll();
    }

    @Override
    public Network getById(Integer id) {
        var network = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Network not found with id = " + id));

        return network;
    }

    @Override
    public Network updateById(Integer id, Network network) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(network.getName());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Network not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Network network = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Network not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //network.setIsDeleted(true);
        repository.delete(network);
        //repository.save(network);
    }
}
