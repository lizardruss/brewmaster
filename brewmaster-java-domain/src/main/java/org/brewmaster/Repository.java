package org.brewmaster;

import java.util.List;

public interface Repository {
	public <T> T get(Class<T> clazz, Object id);
	public <T> List<T> list(Class<T> clazz);
}
