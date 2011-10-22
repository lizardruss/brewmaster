package org.brewmaster.domain;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.brewmaster.persistence.EntityDao;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Recipe implements org.brewmaster.persistence.Entity<Recipe> {

	@Resource
	private EntityDao dao;

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void create() {
		dao.save(this);
	}

	public void update(Recipe templateEntity) {
		setDescription(templateEntity.getDescription());
		setName(templateEntity.getName());
	}

	public void delete() {
		dao.delete(this);
	}
}
