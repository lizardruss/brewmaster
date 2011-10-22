package org.brewmaster.persistence;

public interface Entity<T> {
	public Long getId();
	public void create();
	public void update(T templateEntity);
	public void delete();
}
