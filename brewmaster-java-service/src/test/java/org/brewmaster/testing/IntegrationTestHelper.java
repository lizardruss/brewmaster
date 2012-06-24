package org.brewmaster.testing;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class IntegrationTestHelper {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

	@PostConstruct
	public void generateSchema() throws Exception {
		Ejb3Configuration configuration = new Ejb3Configuration();
		Ejb3Configuration configuredConfiguration = configuration.configure(
				entityManagerFactoryBean.getPersistenceUnitInfo(),
				entityManagerFactoryBean.getJpaPropertyMap());

		Configuration hibernateConfiguration = configuredConfiguration
				.getHibernateConfiguration();
		SchemaExport schemaExport = new SchemaExport(hibernateConfiguration);
		schemaExport.setOutputFile("src/main/resources/schema.sql");
		schemaExport.create(true, false);
	}
	
	@Transactional
	public <T> T persistNow(T entity) {
		entityManager.persist(entity);
		entityManager.clear();
		return entity;
	}

	@Transactional
	public <T> void persistNow(List<T> entities) {
		for (T entity : entities) {
			entityManager.persist(entity);
		}
		entityManager.clear();
	}

	@Transactional
	@SuppressWarnings("deprecation")
	public void clearDatabase() throws Exception {
        Session session = ((Session) entityManager.getDelegate());
        session.createSQLQuery("SET DATABASE REFERENTIAL INTEGRITY FALSE").executeUpdate();
        List<String> tables = session.createSQLQuery("select table_name " +
	        "from INFORMATION_SCHEMA.system_tables " +
	        "where table_type='TABLE' and table_schem='PUBLIC'").list();
        for (String table : tables) {
            session.createSQLQuery("DELETE FROM " + table).executeUpdate();
        }
        session.createSQLQuery("SET DATABASE REFERENTIAL INTEGRITY TRUE").executeUpdate();
	}
}
