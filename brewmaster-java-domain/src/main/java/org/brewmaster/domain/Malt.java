package org.brewmaster.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "name"))
public class Malt extends AbstractEntity<Malt> {
	
	@NotNull(errorCode = "malt.description.null")
	@NotEmpty(errorCode = "malt.description.empty")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull(errorCode = "malt.name.null")
	@NotEmpty(errorCode = "malt.name.empty")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void update(Malt templateEntity) {
		setName(templateEntity.getName());
		setDescription(templateEntity.getDescription());
	}
}
