package org.brewmaster.testing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		Connection c = ((Session) entityManager.getDelegate()).connection();
	    Statement s = c.createStatement();
	    s.execute("SET DATABASE REFERENTIAL INTEGRITY FALSE");
	    Set<String> tables = new HashSet<String>();
	    ResultSet rs = s.executeQuery("select table_name " +
	        "from INFORMATION_SCHEMA.system_tables " +
	        "where table_type='TABLE' and table_schem='PUBLIC'");
	    while (rs.next()) {
	        if (!rs.getString(1).startsWith("DUAL_")) {
	            tables.add(rs.getString(1));
	        }
	    }
	    rs.close();
	    for (String table : tables) {
	        s.executeUpdate("DELETE FROM " + table);
	    }
	    s.execute("SET DATABASE REFERENTIAL INTEGRITY TRUE");
	    s.close();
	}
}
