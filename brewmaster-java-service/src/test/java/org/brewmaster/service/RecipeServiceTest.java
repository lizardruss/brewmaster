package org.brewmaster.service;

import static org.brewmaster.testing.RecipeFixtures.hatePorter;
import static org.brewmaster.testing.RecipeFixtures.krampusRed;
import static org.brewmaster.testing.RecipeFixtures.pilsenerUrquelle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.brewmaster.domain.Recipe;
import org.brewmaster.testing.IntegrationTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RecipeServiceTest {

	@Resource
	private IntegrationTestHelper integrationTestHelper;

	@Resource
	private RecipeService fixture;

	private Recipe entity;
	
	private List<Recipe> allEntities;

	@Before
	public void setUp() throws Exception {
		allEntities = new LinkedList<Recipe>();
		allEntities.add(pilsenerUrquelle());
		allEntities.add(krampusRed());
		allEntities.add(hatePorter());

		integrationTestHelper.persistNow(allEntities);

		entity = allEntities.get(0);
	}
	
	@After
	public void tearDown() throws Exception
	{
		integrationTestHelper.clearDatabase();
	}
	
	@Test
	public void testSave() throws Exception {
		Recipe entity = new Recipe();
		entity.setName("Yukon Brewing Arctic Red");
		entity.setDescription("An Irish Red from Alaska.");

		Recipe savedRecipe = fixture.save(entity);
		assertNotNull(savedRecipe.getId());
	}

	@Test(expected = PersistenceException.class)
	public void testSaveExisting() throws Exception {
		Recipe entity = new Recipe();
		entity.setId(this.entity.getId());
		entity.setDescription(this.entity.getDescription());
		entity.setName(this.entity.getName());

		fixture.save(entity);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNew() throws Exception {
		Recipe entity = new Recipe();
		entity.setName("Some new recipe.");
		entity.setDescription("OMG!! So exciting new recipe.. blah!");
		
		fixture.update(entity);
	}
	
	@Test
	public void testUpdateExisting() throws Exception {
		entity.setDescription("A clone of the original lager.");
		
		Recipe updatedEntity = fixture.update(entity);
		
		assertEquals(entity.getId(), updatedEntity.getId());
		assertEquals(entity.getName(), updatedEntity.getName());
		assertEquals(entity.getDescription(), updatedEntity.getDescription());
		assertEquals(new Long(entity.getVersion() + 1), updatedEntity.getVersion());
	}

	@Test
	public void testDelete() throws Exception {
		Recipe loadedEntity = fixture.get(entity.getId());
		assertNotNull(loadedEntity);

		fixture.delete(entity);

		Recipe deletedEntity = fixture.get(entity.getId());
		assertNull(deletedEntity);
	}
	
	@Test
	public void testDeleteList() throws Exception {
		fixture.delete(allEntities);
	}

	@Test
	public void testGet() throws Exception {
		assertNotNull(entity.getId());
		assertFalse(entity.getId().equals(0L));

		Recipe loadedEntity = fixture.get(entity.getId());

		assertNotNull(loadedEntity);
		assertNotSame(entity, loadedEntity);
		assertEquals(entity.getDescription(), loadedEntity.getDescription());
		assertEquals(entity.getName(), loadedEntity.getName());
	}

	@Test
	public void testList() throws Exception {
		List<Recipe> entities = fixture.list();

		assertNotNull(entities);
		assertEquals(3, entities.size());
	}
}
