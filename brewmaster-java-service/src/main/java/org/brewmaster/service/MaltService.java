package org.brewmaster.service;

import org.brewmaster.data.MaltRepository;
import org.brewmaster.domain.Malt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MaltService extends BaseEntityService<Malt> {
    @Resource
    private MaltRepository repository;

    @Override
    protected JpaRepository<Malt, Long> getRepository() {
        return repository;
    }
}
