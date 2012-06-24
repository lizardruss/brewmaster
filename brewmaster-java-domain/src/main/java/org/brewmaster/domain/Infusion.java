package org.brewmaster.domain;

import net.sf.oval.constraint.Min;

import javax.persistence.Embeddable;

@Embeddable
public class Infusion {

    @Min(value = 0, errorCode = "infusion.temperature.zero")
    private Double temperature;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Min(value = 0, errorCode = "infusion.volume.zero")
    private Double volume;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
