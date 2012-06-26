package org.brewmaster.service;

import org.brewmaster.domain.Hop;
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
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:HopFixtures.xml"})
public class HopServiceTest {

    @Resource
    private IntegrationTestHelper integrationTestHelper;

    @Resource
    private HopService fixture;

    private Hop entity;

    @Resource
    private List<Hop> allEntities;

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
        Hop entity = new Hop();
        entity.setName("Imaginary");

        Hop savedEntity = fixture.save(entity);
        assertNotNull(savedEntity.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveExisting() throws Exception {
        Hop entity = new Hop();
        entity.setName("Cascade");

        fixture.save(entity);
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
        Iterable<Hop> entities = fixture.list();

        assertNotNull(entities);
        assertTrue(entities.iterator().hasNext());
    }
}
