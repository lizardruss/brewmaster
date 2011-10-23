package org.brewmaster.persistence.jpa;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.brewmaster.persistence.Entity;
import org.brewmaster.persistence.Repository;

public class JpaRepository implements Repository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public <E extends Entity<?>> E get(Class<E> clazz, Long id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public <E extends Entity<?>> E get(E entity) {
		return (E) get(entity.getClass(), entity.getId());
	}

	public <E extends Entity<?>> List<E> list(Class<E> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> query = criteriaBuilder.createQuery(clazz);
		Root<E> root = query.from(clazz);
		query.select(root);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Entity<?>> List<E> list(List<E> entities) {
		
		// Map the objects by class type.
		Map<Class<E>, List<E>> classEntitiesMap = new HashMap<Class<E>, List<E>>();
		for (E entity : entities)
		{
			Class<E> entityClass = (Class<E>) entity.getClass();
			List<E> classEntities = classEntitiesMap.get(entityClass);
			if (classEntities == null)
			{
				classEntities = new LinkedList<E>();
				classEntitiesMap.put(entityClass, classEntities);
			}
			classEntities.add(entity);
		}
		
		LinkedList<E> results = new LinkedList<E>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		for (Entry<Class<E>, List<E>> entry : classEntitiesMap.entrySet())
		{
			// Collect IDs for the where clause.
			Collection<Object> ids = new LinkedList<Object>();
			for (E entity : entry.getValue())
			{
				ids.add(entity.getId());
			}
			
			// Create criteria query.
			CriteriaQuery<E> query = criteriaBuilder.createQuery(entry.getKey());
			Root<E> root = query.from(entry.getKey());
			query.where(root.get("id").in(ids));
			
			// Put the results in a collection.
			results.addAll(entityManager.createQuery(query).getResultList());
		}
		
		return results;
	}
}
