package org.brewmaster.service;

import org.brewmaster.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseEntityService<T extends BaseEntity> {

    protected abstract JpaRepository<T, Long> getRepository();

	public T get(Long id) {
		return getRepository().findOne(id);
	}

	public Iterable<T> list() {
		return getRepository().findAll();
	}
	
	public Iterable<T> list(Iterable<Long> entityIds) {
		return getRepository().findAll(entityIds);
	}

	@Transactional
	public T save(T entity) {
        getRepository().save(entity);
		return entity;
	}
	@Transactional
	public Iterable<T> save(Iterable<T> entities) {
        getRepository().save(entities);
		return entities;
	}

	@Transactional
	public void delete(T entity) {
		T persistentEntity = get(entity.getId());
        getRepository().delete(persistentEntity);
	}

    @Transactional
    public void delete(Iterable<T> entities) {
        getRepository().delete(entities);
    }
}
