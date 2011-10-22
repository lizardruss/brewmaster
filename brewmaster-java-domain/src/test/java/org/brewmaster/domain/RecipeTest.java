package org.brewmaster.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.brewmaster.persistence.EntityDao;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecipeTest {

	private Mockery context = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private Recipe fixture;

	private EntityDao dao;

	@Before
	public void setUp() throws Exception {
		fixture = new Recipe();

		dao = context.mock(EntityDao.class);
		setField(fixture, "dao", dao);
	}

	@After
	public void tearDown() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void testCreate() {
		context.checking(new Expectations() {
			{
				oneOf(dao).save(fixture);
			}
		});

		fixture.create();
	}

	@Test
	public void testUpdate() {
		final Recipe mockRecipe = context.mock(Recipe.class);
		final String name = "Pilsner Urquell";
		final String description = "The original lager.";

		context.checking(new Expectations() {
			{
				oneOf(mockRecipe).getDescription();
				will(returnValue(description));

				oneOf(mockRecipe).getName();
				will(returnValue(name));
			}
		});

		assertNull(fixture.getDescription());
		assertNull(fixture.getName());

		fixture.update(mockRecipe);

		assertEquals(description, fixture.getDescription());
		assertEquals(name, fixture.getName());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {
			{
				oneOf(dao).delete(fixture);
			}
		});

		fixture.delete();
	}

}
