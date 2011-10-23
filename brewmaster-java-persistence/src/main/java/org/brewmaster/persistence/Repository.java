package org.brewmaster.persistence;

import java.util.List;

public interface Repository {
	public <E extends Entity<?>> E get(Class<E> clazz, Long id);
	public <E extends Entity<?>> E get(E entity);
	public <E extends Entity<?>> List<E> list(Class<E> clazz);
	public <E extends Entity<?>> List<E> list(List<E> entities);
}
