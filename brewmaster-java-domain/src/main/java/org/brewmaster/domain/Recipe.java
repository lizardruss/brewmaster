package org.brewmaster.domain;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.brewmaster.persistence.EntityDao;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Recipe implements org.brewmaster.persistence.Entity<Recipe> {

	@Resource
	private EntityDao dao;

	public void create() {
		dao.save(this);
	}

	public void update(Recipe templateEntity) {
		// Update individual properties here.
	}

	public void delete() {
		dao.delete(this);
	}
}
