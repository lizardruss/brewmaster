package org.brewmaster.domain;

import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.brewmaster.persistence.EntityDao;
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

}
