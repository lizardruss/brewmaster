package org.brewmaster.domain;

@SuppressWarnings("rawtypes")
public class Range<T extends Comparable> {

	private final T from;
	
	public T getFrom() {
		return from;
	}

	private final T to;
	
	public T getTo() {
		return to;
	}

	public Range(T from, T to)
	{
		this.from = from;
		this.to = to;
	}
	
	@SuppressWarnings("unchecked")
	public boolean contains(T value)
	{
		return from.compareTo(value) <= 0 && to.compareTo(value) >= 0;
	}
}
