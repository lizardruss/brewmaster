package org.brewmaster.persistence.jpa;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.brewmaster.persistence.Repository;

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
