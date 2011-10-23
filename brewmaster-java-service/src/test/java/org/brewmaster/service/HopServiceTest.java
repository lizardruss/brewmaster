package org.brewmaster.service;

import static org.brewmaster.testing.HopFixtures.cascade;
import static org.brewmaster.testing.HopFixtures.centennial;
import static org.brewmaster.testing.HopFixtures.hallertauer;
import static org.brewmaster.testing.HopFixtures.magnum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.brewmaster.domain.Hop;
import org.brewmaster.testing.IntegrationTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class HopServiceTest {

	@Resource
	private IntegrationTestHelper integrationTestHelper;

	@Resource
	private HopService fixture;

	private Hop entity;
	
	private List<Hop> allEntities;

	@Before
	public void setUp() throws Exception {
		allEntities = new LinkedList<Hop>();
		allEntities.add(cascade());
		allEntities.add(centennial());
		allEntities.add(hallertauer());
		allEntities.add(magnum());

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
		Hop entity = new Hop();
		entity.setName("Imaginary");
		
		Hop savedEntity = fixture.save(entity);
		assertNotNull(savedEntity.getId());
	}

	@Test(expected = PersistenceException.class)
	public void testSaveExisting() throws Exception {
		Hop entity = new Hop();
		entity.setName("Cascade");

		fixture.save(entity);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNew() throws Exception {
		Hop entity = new Hop();
		entity.setName("Cascade");

		fixture.update(entity);
	}
	
	@Test
	public void testUpdateExisting() throws Exception {
		entity.setDescription("Crisp Hop used in Sierra Nevada.");
		
		Hop updatedEntity = fixture.update(entity);
		
		assertEquals(entity.getId(), updatedEntity.getId());
		assertEquals(entity.getName(), updatedEntity.getName());
		assertEquals(entity.getDescription(), updatedEntity.getDescription());
		assertEquals(new Long(entity.getVersion() + 1), updatedEntity.getVersion());
	}

	@Test
	public void testDelete() throws Exception {
		Hop loadedEntity = fixture.get(entity.getId());
		assertNotNull(loadedEntity);

		fixture.delete(entity);

		Hop deletedEntity = fixture.get(entity.getId());
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

		Hop loadedEntity = fixture.get(entity.getId());

		assertNotNull(loadedEntity);
		assertNotSame(entity, loadedEntity);
		assertEquals(entity.getDescription(), loadedEntity.getDescription());
		assertEquals(entity.getName(), loadedEntity.getName());
	}

	@Test
	public void testList() throws Exception {
		List<Hop> entities = fixture.list();

		assertNotNull(entities);
		assertEquals(4, entities.size());
	}
}
