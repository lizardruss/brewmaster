package org.brewmaster.service;

import org.brewmaster.data.RecipeRepository;
import org.brewmaster.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecipeService extends BaseEntityService<Recipe> {
    @Resource
    private RecipeRepository repository;

    @Override
    protected JpaRepository<Recipe, Long> getRepository() {
        return repository;
    }
}
