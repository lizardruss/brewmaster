package org.brewmaster.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RecipeServiceTest {
	
	@Resource
	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBean() throws Exception {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		
		assertNull(dataSource);
	}

}
