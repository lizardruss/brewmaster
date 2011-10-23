package org.brewmaster.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "name"))
public class Hop extends AbstractEntity<Hop> {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(Hop templateEntity) {
		setDescription(templateEntity.getDescription());
		setName(templateEntity.getName());
	}

}
