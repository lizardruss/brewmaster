package org.brewmaster.domain;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import org.brewmaster.validation.IdRequired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Configurable
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue
	@NotNull(errorCode = "entity.id.null", profiles = {IdRequired.PROFILE})
	@Min(value = 1, errorCode = "entity.id.min", profiles = {IdRequired.PROFILE})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
