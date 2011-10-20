package org.brewmaster.domain;

import java.util.List;

public interface ObjectFactory<T> {
	public T get(long id);
	public List<T> list();
}