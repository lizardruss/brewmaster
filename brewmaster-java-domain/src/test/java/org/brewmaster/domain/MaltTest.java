package org.brewmaster.domain;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MaltTest {
	
	private Malt malt;

	@Before
	public void setUp() throws Exception {
		malt = new Malt();
		malt.setName("Maris Otter");
		malt.setDescription("The go to malt for british style ales.");
	}
	
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

	@Test
	public void testUpdate() {
		Malt templateEntity = new Malt();
		templateEntity.setName("Maris Otter");
		templateEntity.setDescription("Malt grown by otters name Maris.");
		
		malt.update(templateEntity);
		
		assertEquals(templateEntity.getName(), malt.getName());
		assertEquals(templateEntity.getDescription(), malt.getDescription());
	}

}
