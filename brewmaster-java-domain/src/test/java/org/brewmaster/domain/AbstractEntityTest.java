package org.brewmaster.domain;

import static org.springframework.test.util.ReflectionTestUtils.setField;
import static net.sf.oval.testing.Assert.assertValid;
import static net.sf.oval.testing.Assert.assertErrorCodes;

import org.brewmaster.persistence.EntityDao;
import org.brewmaster.validation.IdRequired;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractEntityTest {

	private Mockery context = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private AbstractEntity<?> fixture;

	private EntityDao dao;

	@Before
	public void setUp() throws Exception {
		fixture = new AbstractEntity<Integer>() {
			public void update(Integer templateEntity) {
				// TODO Auto-generated method stub
			}
		};
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
