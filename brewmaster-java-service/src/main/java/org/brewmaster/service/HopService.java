package org.brewmaster.service;

import org.brewmaster.data.HopRepository;
import org.brewmaster.domain.Hop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HopService extends BaseEntityService<Hop> {
    @Resource
    private HopRepository repository;

    @Override
    protected JpaRepository<Hop, Long> getRepository() {
        return repository;
    }
}
