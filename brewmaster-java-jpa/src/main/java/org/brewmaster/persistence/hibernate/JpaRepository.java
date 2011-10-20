package org.brewmaster.persistence.hibernate;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.brewmaster.Repository;

public class JpaRepository implements Repository {

	@Resource
	private EntityManager entityManager;

	public <T> T get(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	public <T> List<T> list(Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
		return entityManager.createQuery(query).getResultList();
	}
}
