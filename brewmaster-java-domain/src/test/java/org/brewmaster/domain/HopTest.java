package org.brewmaster.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Mocks.xml", "classpath:HopFixtures.xml" })
public class HopTest {

    @Resource(name = "cascade")
	private Hop hop;

	@Test
	public void testValid() {
		assertValid(hop);
	}

	@Test
	public void testDescriptionNullIsInvalid() {
		hop.setDescription(null);
		assertErrorCodes(hop, "hop.description.null");
	}
	
	@Test
	public void testDescriptionEmptyIsInvalid() {
		hop.setDescription("");
		assertErrorCodes(hop, "hop.description.empty");
	}
	
	@Test
	public void testNameNullIsInvalid() {
		hop.setName(null);
		assertErrorCodes(hop, "hop.name.null");
	}
	
	@Test
	public void testNameEmptyIsInvalid() {
		hop.setName("");
		assertErrorCodes(hop, "hop.name.empty");
	}
}
