package org.brewmaster.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Mocks.xml", "classpath:RecipeFixtures.xml"})
public class RecipeTest {

    @Resource(name = "toastedAle")
    private Recipe recipe;

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
}
