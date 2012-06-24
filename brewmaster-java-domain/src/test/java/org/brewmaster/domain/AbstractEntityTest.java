package org.brewmaster.domain;

import static org.springframework.test.util.ReflectionTestUtils.setField;
import static net.sf.oval.testing.Assert.assertValid;
import static net.sf.oval.testing.Assert.assertErrorCodes;

import org.brewmaster.persistence.EntityDao;
import org.brewmaster.validation.IdRequired;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Mocks.xml" })
public class AbstractEntityTest {

    @Resource
	private Mockery context;

	private AbstractEntity fixture;

    @Resource(name="dao")
	private EntityDao dao;

	@Before
	public void setUp() throws Exception {
		fixture = new AbstractEntity<Integer>() {};
		fixture.setId(1L);

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

		fixture.save();
	}

    @Test
	public void testUpdate() {
        final AbstractEntity template = new AbstractEntity() {};

        context.checking(new Expectations() {
			{
				oneOf(dao).merge(template);
			}
		});

		fixture.update(template);
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
