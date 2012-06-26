package org.brewmaster.service;

import org.brewmaster.domain.Recipe;
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
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:RecipeFixtures.xml"})
public class RecipeServiceTest {

    @Resource
    private IntegrationTestHelper integrationTestHelper;

    @Resource
    private RecipeService fixture;

    @Resource(name = "toastedAle")
    private Recipe entity;

    @Resource
    private List<Recipe> allEntities;

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
        Recipe entity = new Recipe();
        entity.setName("Yukon Brewing Arctic Red");
        entity.setDescription("An Irish Red from Alaska.");

        Recipe savedRecipe = fixture.save(entity);
        assertNotNull(savedRecipe.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveExisting() throws Exception {
        Recipe entity = new Recipe();
        entity.setId(this.entity.getId());
        entity.setDescription(this.entity.getDescription());
        entity.setName(this.entity.getName());

        fixture.save(entity);
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
        Iterable<Recipe> entities = fixture.list();

        assertNotNull(entities);
        assertTrue(entities.iterator().hasNext());
    }
}
