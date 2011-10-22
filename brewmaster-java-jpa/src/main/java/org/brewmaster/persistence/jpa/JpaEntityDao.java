package org.brewmaster.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.brewmaster.persistence.EntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEntityDao extends JpaRepository implements EntityDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Object entity) {
		entityManager.persist(entity);
	}

	public void delete(Object entity) {
		entityManager.remove(entity);
	}
}
