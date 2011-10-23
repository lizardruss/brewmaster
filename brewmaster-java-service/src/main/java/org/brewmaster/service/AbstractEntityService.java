package org.brewmaster.service;

import java.util.List;

import javax.annotation.Resource;

import org.brewmaster.persistence.Entity;
import org.brewmaster.persistence.Repository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractEntityService<T extends Entity<T>> {

	abstract protected Class<T> getEntityClass();

	@Resource
	private Repository repository;

	@Transactional(readOnly = true)
	public T get(Long id) {
		return repository.get(getEntityClass(), id);
	}

	@Transactional(readOnly = true)
	public List<T> list() {
		return repository.list(getEntityClass());
	}
	
	@Transactional(readOnly = true)
	public List<T> list(List<T> entities) {
		return repository.list(entities);
	}

	@Transactional
	public T save(T entity) {
		entity.save();
		return entity;
	}

	@Transactional
	public T update(T entity) {
		T managedEntity = get(entity.getId());
		managedEntity.update(entity);
		return managedEntity;
	}

	@Transactional
	public void delete(T entity) {
		T persistentEntity = get(entity.getId());
		persistentEntity.delete();
	}

	@Transactional
	public void delete(List<T> entities) {
		List<T> persistentEntities = repository.list(entities);
		for (T entity : persistentEntities)
		{
			entity.delete();
		}
	}
}
