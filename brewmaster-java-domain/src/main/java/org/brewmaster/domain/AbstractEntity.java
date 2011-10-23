package org.brewmaster.domain;

import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.brewmaster.persistence.Entity;
import org.brewmaster.persistence.EntityDao;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@MappedSuperclass
abstract public class AbstractEntity<T> implements Entity<T> {
	@Resource
	@Transient
	private EntityDao dao;
	
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void save() {
		dao.save(this);
	}
	
	public void delete() {
		dao.delete(this);
	}
}
