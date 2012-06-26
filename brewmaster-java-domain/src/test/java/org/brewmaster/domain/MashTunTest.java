package org.brewmaster.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Mocks.xml", "classpath:MashTunFixtures.xml"})
public class MashTunTest {

    @Resource(name = "rubbermaidCooler")
    private MashTun mashTun;

    @Test
    public void testValid() {
        assertValid(mashTun);
    }

    @Test
    public void testThermalMassNullIsInvalid() {
        mashTun.setThermalMass(null);
        assertErrorCodes(mashTun, "mashtun.thermalmass.null");
    }

    @Test
    public void testThermalMassZeroIsInvalid() {
        mashTun.setThermalMass(0.0);
        assertErrorCodes(mashTun, "mashtun.thermalmass.assert");
    }

    @Test
    public void testThermalMassNegativeIsInvalid() {
        mashTun.setThermalMass(-1.0);
        assertErrorCodes(mashTun, "mashtun.thermalmass.assert");
    }
}
