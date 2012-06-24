package org.brewmaster.domain;

import org.brewmaster.validation.IdRequired;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static net.sf.oval.testing.Assert.assertErrorCodes;
import static net.sf.oval.testing.Assert.assertValid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Mocks.xml" })
public class BaseEntityTest {

    @Resource
	private Mockery context;

	private BaseEntity fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new BaseEntity() {};
		fixture.setId(1L);
	}

	@After
	public void tearDown() throws Exception {
		context.assertIsSatisfied();
	}

	@Test
	public void testValidIdWhenIdRequired() {
		fixture.setId(1L);

		assertValid(fixture, IdRequired.PROFILE);
	}

	@Test
	public void testNullIdInvalidWhenIdRequired() {
		fixture.setId(null);

		assertErrorCodes(fixture, IdRequired.PROFILE,
				new String[] { "entity.id.null" });
	}

	@Test
	public void testZeroIdInvalidWhenIdRequired() {
		fixture.setId(0L);

		assertErrorCodes(fixture, IdRequired.PROFILE,
				new String[] { "entity.id.min" });
	}

	@Test
	public void testNegativeIdInvalidWhenIdRequired() {
		fixture.setId(-1L);

		assertErrorCodes(fixture, IdRequired.PROFILE,
				new String[] { "entity.id.min" });
	}

	@Test
	public void testNullIdValidWhenIdNotRequired() {
		fixture.setId(null);

		assertValid(fixture);
	}

	@Test
	public void testZeroIdValidWhenIdNotRequired() {
		fixture.setId(0L);

		assertValid(fixture);
	}

	@Test
	public void testNegativeIdValidWhenIdNotRequired() {
		fixture.setId(-1L);

		assertValid(fixture);
	}
}
