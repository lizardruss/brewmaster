package org.brewmaster.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;

import org.brewmaster.domain.Malt;
import org.brewmaster.testing.IntegrationTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:MaltFixtures.xml" })
public class MaltServiceTest {

	@Resource
	private IntegrationTestHelper integrationTestHelper;

	@Resource
	private MaltService fixture;

	private Malt entity;

    @Resource
	private List<Malt> allEntities;

	@Before
	public void setUp() throws Exception {
		integrationTestHelper.persistNow(allEntities);
		entity = allEntities.get(0);
	}

	@After
	public void tearDown() throws Exception {
		integrationTestHelper.clearDatabase();
	}

	@Test
	public void testSave() throws Exception {
		Malt entity = new Malt();
		entity.setName("Imaginary");

		Malt savedEntity = fixture.save(entity);
		assertNotNull(savedEntity.getId());
	}

	@Test(expected = PersistenceException.class)
	public void testSaveExisting() throws Exception {
		Malt entity = new Malt();
		entity.setName("Crystal 10");

		fixture.save(entity);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateNew() throws Exception {
		Malt entity = new Malt();
		entity.setName("Crystal");

		fixture.update(entity);
	}

	@Test
	public void testUpdateExisting() throws Exception {
		entity.setDescription("Crystal malt for flavor or color.");

		Malt updatedEntity = fixture.update(entity);

		assertEquals(entity.getId(), updatedEntity.getId());
		assertEquals(entity.getName(), updatedEntity.getName());
		assertEquals(entity.getDescription(), updatedEntity.getDescription());
		assertEquals(new Long(entity.getVersion() + 1),
				updatedEntity.getVersion());
	}

	@Test
	public void testDelete() throws Exception {
		Malt loadedEntity = fixture.get(entity.getId());
		assertNotNull(loadedEntity);

		fixture.delete(entity);

		Malt deletedEntity = fixture.get(entity.getId());
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

		Malt loadedEntity = fixture.get(entity.getId());

		assertNotNull(loadedEntity);
		assertNotSame(entity, loadedEntity);
		assertEquals(entity.getDescription(), loadedEntity.getDescription());
		assertEquals(entity.getName(), loadedEntity.getName());
	}

	@Test
	public void testList() throws Exception {
		List<Malt> entities = fixture.list();

		assertNotNull(entities);
		assertEquals(9, entities.size());
	}
}
