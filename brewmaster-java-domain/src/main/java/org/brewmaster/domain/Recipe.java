package org.brewmaster.domain;

import javax.annotation.Resource;

import org.brewmaster.persistence.EntityDao;
import org.hibernate.annotations.Entity;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Recipe implements org.brewmaster.domain.Entity<Recipe> {

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
