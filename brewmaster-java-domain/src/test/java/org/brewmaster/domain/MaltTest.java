package org.brewmaster.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Mocks.xml", "classpath:MaltFixtures.xml" })
public class MaltTest {

    @Resource(name="marisOtter")
	private Malt malt;
	
	@Test
	public void testValid() {
		assertValid(malt);
	}

	@Test
	public void testDescriptionNullIsInvalid() {
		malt.setDescription(null);
		assertErrorCodes(malt, "malt.description.null");
	}
	
	@Test
	public void testDescriptionEmptyIsInvalid() {
		malt.setDescription("");
		assertErrorCodes(malt, "malt.description.empty");
	}
	
	@Test
	public void testNameNullIsInvalid() {
		malt.setName(null);
		assertErrorCodes(malt, "malt.name.null");
	}
	
	@Test
	public void testNameEmptyIsInvalid() {
		malt.setName("");
		assertErrorCodes(malt, "malt.name.empty");
	}
}
