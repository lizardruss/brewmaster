package org.brewmaster.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.brewmaster.persistence.Entity;
import org.brewmaster.persistence.EntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEntityDao extends JpaRepository implements EntityDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <E extends Entity<?>> void save(E entity) {
		entityManager.persist(entity);
	}

	@Override
	public <E extends Entity<?>> void delete(E entity) {
		entityManager.remove(entity);
	}
}
