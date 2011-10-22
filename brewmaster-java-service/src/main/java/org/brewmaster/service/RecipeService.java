package org.brewmaster.service;

import org.brewmaster.domain.Recipe;
import org.springframework.stereotype.Service;

@Service
public class RecipeService extends EntityService<Recipe>{

	@Override
	protected Class<Recipe> getEntityClass() {
		return Recipe.class;
	}

}
