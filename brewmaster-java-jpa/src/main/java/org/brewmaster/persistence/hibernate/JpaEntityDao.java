package org.brewmaster.persistence.hibernate;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.brewmaster.persistence.EntityDao;

public class JpaEntityDao extends JpaRepository implements EntityDao {

	@Resource
	private EntityManager entityManager;
	
	public void save(Object entity) {
		entityManager.persist(entity);
	}

	public void delete(Object entity) {
		entityManager.remove(entity);
	}
}
