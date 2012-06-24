package org.brewmaster.service;

import org.brewmaster.domain.Malt;
import org.brewmaster.testing.IntegrationTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test(expected = DataIntegrityViolationException.class)
	public void testSaveExisting() throws Exception {
		Malt entity = new Malt();
		entity.setName("Crystal 10");

		fixture.save(entity);
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
		Iterable<Malt> entities = fixture.list();

		assertNotNull(entities);
		assertTrue(entities.iterator().hasNext());
	}
}
