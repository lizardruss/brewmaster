package org.brewmaster.domain;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HopTest {

	private Hop hop;

	@Before
	public void setUp() throws Exception {
		hop = new Hop();
		hop.setName("Williamette");
		hop.setDescription("Floral with citrus notes.");
	}

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

	@Test
	public void testUpdate() {
		Hop templateEntity = new Hop();
		templateEntity.setName("Williamette");
		templateEntity.setDescription("Better description.");

		hop.update(templateEntity);

		assertEquals(templateEntity.getName(), hop.getName());
		assertEquals(templateEntity.getDescription(), hop.getDescription());
	}

}
