package org.brewmaster.persistence;

public interface EntityDao {
	public void save(Object entity);
	public void delete(Object entity);
}
