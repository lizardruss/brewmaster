package org.brewmaster.domain;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MaltAddition extends AbstractEntity<MaltAddition> {

    @NotNull(errorCode = "malt.type.null")
    @ManyToOne
    private Malt type;

    public Malt getType() {
        return type;
    }

    public void setType(Malt type) {
        this.type = type;
    }

    @Min(value = 0, errorCode = "malt.weight.zero")
    private Double weight;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
