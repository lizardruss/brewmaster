package org.brewmaster.persistence;

public interface EntityDao {
	public <E extends Entity<?>> void save(E entity);
	public <E extends Entity<?>> void delete(E entity);
}
