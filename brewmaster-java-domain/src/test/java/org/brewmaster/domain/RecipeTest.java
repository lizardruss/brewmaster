package org.brewmaster.domain;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RecipeTest {
	
	private Recipe recipe;

	@Before
	public void setUp() throws Exception {
		recipe = new Recipe();
		recipe.setName("Krampus Red");
		recipe.setDescription("Yukon Brewing's Arctic Red clone.");
	}
	

	@Test
	public void testValid() {
		assertValid(recipe);
	}

	@Test
	public void testDescriptionNullIsInvalid() {
		recipe.setDescription(null);
		assertErrorCodes(recipe, "recipe.description.null");
	}
	
	@Test
	public void testDescriptionEmptyIsInvalid() {
		recipe.setDescription("");
		assertErrorCodes(recipe, "recipe.description.empty");
	}
	
	@Test
	public void testNameNullIsInvalid() {
		recipe.setName(null);
		assertErrorCodes(recipe, "recipe.name.null");
	}
	
	@Test
	public void testNameEmptyIsInvalid() {
		recipe.setName("");
		assertErrorCodes(recipe, "recipe.name.empty");
	}

	@Test
	public void testUpdate() {
		Recipe templateEntity = new Recipe();
		templateEntity.setName("Krampus Red");
		templateEntity.setDescription("My favorite recipe.");
		
		recipe.update(templateEntity);
		
		assertEquals(templateEntity.getName(), recipe.getName());
		assertEquals(templateEntity.getDescription(), recipe.getDescription());
	}

}
