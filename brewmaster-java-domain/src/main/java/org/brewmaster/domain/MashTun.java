package org.brewmaster.domain;

import net.sf.oval.constraint.*;
import net.sf.oval.constraint.Range;

import javax.persistence.Entity;

@Entity
public class MashTun extends AbstractEntity<MashTun> {

    @Assert(expr = "_value > 0", errorCode = "mashtun.thermalmass.assert", lang = "javascript")
    @NotNull(errorCode = "mashtun.thermalmass.null")
    private Double thermalMass;

    public Double getThermalMass() {
        return thermalMass;
    }

    public void setThermalMass(Double thermalMass) {
        this.thermalMass = thermalMass;
    }
}
