package org.brewmaster.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Mash extends AbstractEntity<Mash> {

    @ManyToOne
    private MashTun mashTun;

    public MashTun getMashTun() {
        return mashTun;
    }

    public void setMashTun(MashTun mashTun) {
        this.mashTun = mashTun;
    }

    @OneToMany
    @OrderColumn(name="order")
    //@JoinColumn(name="mash_id", nullable=false)
    private List<MashStep> steps;

    public List<MashStep> getSteps() {
        return steps;
    }

    public void setSteps(List<MashStep> steps) {
        this.steps = steps;
    }

    private Double thickness;

    public Double getThickness() {
        return thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }
}
