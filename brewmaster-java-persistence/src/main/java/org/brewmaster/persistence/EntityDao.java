package org.brewmaster.persistence;

public interface EntityDao {
	public <E> void save(E entity);
	public <E> void delete(E entity);
	public <E> void merge(E entity);
}
