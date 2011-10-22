package org.brewmaster.service;

import java.util.List;

import javax.annotation.Resource;

import org.brewmaster.persistence.Entity;
import org.brewmaster.persistence.Repository;

public abstract class EntityService<T extends Entity<T>> {
	
	abstract protected Class<T> getEntityClass();
	
	@Resource
	private Repository repository;
	
	public T get(long id)
	{
		return repository.get(getEntityClass(), id);
	}
	
	public List<T> list()
	{
		return repository.list(getEntityClass());
	}
	
	public void create(T entity)
	{
		entity.create();
	}
	
	public void delete(T entity)
	{
		T persistentEntity = get(entity.getId());
		persistentEntity.delete();
	}
	
	public void update(T entity)
	{
		T persistentEntity = get(entity.getId());
		persistentEntity.update(entity);
	}
}
