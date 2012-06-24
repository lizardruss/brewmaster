package org.brewmaster.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Mocks.xml", "classpath:MashStepFixtures.xml", "classpath:MashTunFixtures.xml" })
public class MashStepTest {

    @Resource(name = "singleInfusion80To152")
    private MashStep fixture;

    @Resource(name = "rubbermaidCooler")
    private MashTun mashTun;

    @Test
    public void testCalculateInfusionTemperaturePreheatedMashTun()
    {
        MashTun preheatedMashTun = new MashTun();
        preheatedMashTun.setThermalMass(0.0);

        Double infusionTemperature = fixture.calculateInfusionTemperature(preheatedMashTun, 10.0);
        assertEquals((Double) 161.2026745272845, infusionTemperature);
    }

    @Test
    public void testCalculateInfusionTemperature10PoundsGrain()
    {
        Double infusionTemperature = fixture.calculateInfusionTemperature(mashTun, 10.0);
        assertEquals((Double) 168.27723057013444, infusionTemperature);
    }

    @Test
    public void testCalculateInfusionTemperature15PoundsGrain()
    {
        Double infusionTemperature = fixture.calculateInfusionTemperature(mashTun, 15.0);
        assertEquals((Double) 172.87856783377669, infusionTemperature);
    }

    @Test
    public void testCalculateInfusionTemperatureTargetTemperature150()
    {
        fixture.setTargetTemperature(150.0);

        Double infusionTemperature = fixture.calculateInfusionTemperature(mashTun, 10.0);
        assertEquals((Double) 165.8250852765196, infusionTemperature);
    }
}
