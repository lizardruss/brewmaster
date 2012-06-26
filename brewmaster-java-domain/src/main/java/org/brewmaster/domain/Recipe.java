package org.brewmaster.domain;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME", columnNames = "name"))
public class Recipe extends BaseEntity {

    @NotNull(errorCode = "recipe.description.null")
    @NotEmpty(errorCode = "recipe.description.empty")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany
    private List<MaltAddition> malts;

    public List<MaltAddition> getMalts() {
        return malts;
    }

    public void setMalts(List<MaltAddition> malts) {
        this.malts = malts;
    }

    @OneToOne
    private Mash mash;

    public Mash getMash() {
        return mash;
    }

    public void setMash(Mash mash) {
        this.mash = mash;
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

    public Double calculateGrainWeight() {
        Double grainWeight = 0.0;
        for (MaltAddition malt : getMalts()) {
            grainWeight += malt.getWeight();
        }
        return grainWeight;
    }
}
