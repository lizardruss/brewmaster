package org.brewmaster.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "name"))
public class Recipe extends AbstractEntity<Recipe> {
	
	@NotNull(errorCode = "recipe.description.null")
	@NotEmpty(errorCode = "recipe.description.empty")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull(errorCode = "recipe.name.null")
	@NotEmpty(errorCode = "recipe.name.empty")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(Recipe templateEntity) {
		setDescription(templateEntity.getDescription());
		setName(templateEntity.getName());
	}
}
