package org.brewmaster.domain;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;

import javax.persistence.Entity;

@Entity
public class MashStep extends BaseEntity {

    @NotNull(errorCode = "mashstep.infusion.null")
    @AssertValid(errorCode = "mashstep.infusion.valid")
    private Infusion infusion;

    public Infusion getInfusion() {
        return infusion;
    }

    public void setInfusion(Infusion infusion) {
        this.infusion = infusion;
    }

    @NotNegative(errorCode = "mashstep.initialTemperature.negative")
    @NotNull(errorCode = "mashstep.initialTemperature.null")
    private Double initialTemperature;

    public Double getInitialTemperature() {
        return initialTemperature;
    }

    public void setInitialTemperature(Double initialTemperature) {
        this.initialTemperature = initialTemperature;
    }

    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @NotNegative(errorCode = "mashstep.initialTemperature.negative")
    @NotNull(errorCode = "mashstep.initialTemperature.null")
    private Double recordedTemperature;

    public Double getRecordedTemperature() {
        return recordedTemperature;
    }

    public void setRecordedTemperature(Double recordedTemperature) {
        this.recordedTemperature = recordedTemperature;
    }

    @NotNegative(errorCode = "mashstep.initialTemperature.negative")
    @NotNull(errorCode = "mashstep.initialTemperature.null")
    private Double targetTemperature;

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Double calculateInfusionTemperature(MashTun mashTun, Double grainWeight) {
        Double infusionTemperature = 0.0;
        Boolean firstInfusion = getOrder() == 0;
        if (firstInfusion) {
            // (Td - Tm)
            Double temperatureDelta = getTargetTemperature() - getInitialTemperature();

            // (ThM + .4Wg)
            Double mashTunThermalMass = mashTun.getThermalMass();
            Double maltThermalMass = grainWeight * TemperatureContants.THERMAL_MASS_MALT;
            Double totalThermalMass = mashTunThermalMass + maltThermalMass;

            // Ti = ((Td - Tm)(ThM + .4Wg))/(2.08635 * Vi)
            Double infusionVolume = getInfusion().getVolume();
            Double infusionTemperatureOffset = (temperatureDelta * totalThermalMass) / (TemperatureContants.WEIGHT_ONE_QUART_POUNDS * infusionVolume);
            infusionTemperature = targetTemperature + infusionTemperatureOffset;
        }
        return infusionTemperature;
    }
}
