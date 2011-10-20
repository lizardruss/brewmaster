package org.brewmaster.persistence;

public interface Entity<T> {
	public void create();
	public void update(T templateEntity);
	public void delete();
}
