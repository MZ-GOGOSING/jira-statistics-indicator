package kr.co.mz.jira.jpa.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration(exclude = {
	DataSourceAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class,
	HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = {StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_PACKAGE},
	transactionManagerRef = StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_TRANSACTION_MANAGER,
	entityManagerFactoryRef = StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY)
@EntityScan(basePackages = {StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_PACKAGE})
public class StatisticsJpaDataSourceConfig {

	public static final String STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY = "statisticsPersistenceEntityManagerFactory";
	public static final String STATISTICS_PERSISTENCE_JPA_PROPERTIES = "statisticsPersistenceJpaProperties";
	public static final String STATISTICS_PERSISTENCE_HIBERNATE_PROPERTIES = "statisticsPersistenceHibernateProperties";
	public static final String STATISTICS_PERSISTENCE_DATA_SOURCE = "statisticsPersistenceDataSource";
	public static final String STATISTICS_PERSISTENCE_TRANSACTION_MANAGER = "statisticsPersistenceTransactionManager";
	public static final String STATISTICS_PERSISTENCE_UNIT = "statisticsPersistence";
	public static final String STATISTICS_PERSISTENCE_PACKAGE = "kr.co.mz.jira.jpa";
	public static final String STATISTICS_PERSISTENCE_JDBC_TEMPLATE = "statisticsPersistenceJdbcTemplate";

	@Primary
	@Bean(name = STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean statisticsPersistenceEntityManagerFactory() {
		return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),
			statisticsPersistenceJpaProperties().getProperties(), null)
			.dataSource(statisticsPersistenceDataSource())
			.properties(statisticsPersistenceHibernateProperties()
				.determineHibernateProperties(statisticsPersistenceJpaProperties().getProperties(),
					new HibernateSettings()))
			.persistenceUnit(STATISTICS_PERSISTENCE_UNIT)
			.packages(STATISTICS_PERSISTENCE_PACKAGE)
			.build();
	}

	@Primary
	@Bean(name = STATISTICS_PERSISTENCE_JPA_PROPERTIES)
	@ConfigurationProperties(prefix = "statistics.persistence.jpa")
	public JpaProperties statisticsPersistenceJpaProperties() {
		return new JpaProperties();
	}

	@Primary
	@Bean(name = STATISTICS_PERSISTENCE_HIBERNATE_PROPERTIES)
	@ConfigurationProperties(prefix = "statistics.persistence.jpa.hibernate")
	public HibernateProperties statisticsPersistenceHibernateProperties() {
		return new HibernateProperties();
	}

	@Primary
	@Bean
	@Qualifier(STATISTICS_PERSISTENCE_DATA_SOURCE)
	@ConfigurationProperties("statistics.persistence.datasource")
	public DataSource statisticsPersistenceDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = STATISTICS_PERSISTENCE_TRANSACTION_MANAGER)
	public PlatformTransactionManager statisticsPersistenceTransactionManager(
		final @Autowired @Qualifier(STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY) EntityManagerFactory statisticsJpaEntityManagerFactory
	) {
		return new JpaTransactionManager(statisticsJpaEntityManagerFactory);
	}

	@Primary
	@Bean(name = STATISTICS_PERSISTENCE_JDBC_TEMPLATE)
	public JdbcTemplate statisticsPersistenceJdbcTemplate(
		final @Qualifier(STATISTICS_PERSISTENCE_DATA_SOURCE) DataSource dataSource
	) {
		return new JdbcTemplate(dataSource);
	}
}
