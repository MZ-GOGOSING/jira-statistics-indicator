package kr.co.mz.jira.container.jta.datasource;

import static kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY;
import static kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_HIBERNATE_PROPERTIES;
import static kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_JPA_PROPERTIES;
import static kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_PACKAGE;
import static kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_UNIT;
import static kr.co.mz.jira.support.jta.JtaDataSourceConfig.JTA_PERSISTENCE_TRANSACTION_MANAGER;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import kr.co.mz.jira.support.jta.JtaDataSourceConfig;
import lombok.RequiredArgsConstructor;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = {
	DataSourceAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class,
	HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = {STATISTICS_PERSISTENCE_PACKAGE},
	transactionManagerRef = JTA_PERSISTENCE_TRANSACTION_MANAGER,
	entityManagerFactoryRef = STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY
)
@DependsOn(JTA_PERSISTENCE_TRANSACTION_MANAGER)
@EntityScan(basePackages = {STATISTICS_PERSISTENCE_PACKAGE})
public class XAStatisticsJpaDataSourceConfig extends JtaDataSourceConfig {

	private static final int STATISTICS_PERSISTENCE_XA_DATA_SOURCE_POOL_SIZE = 5;
	private static final String STATISTICS_PERSISTENCE_XA_PROPERTIES = "statisticsPersistenceXaProperties";
	private static final String STATISTICS_PERSISTENCE_XA_DATA_SOURCE = "statisticsPersistenceXaDataSource";
	private static final String STATISTICS_PERSISTENCE_UNIQUE_RESOURCE_NAME = "XA.STATISTICS.PERSISTENCE";
	private static final String STATISTICS_XA_DATA_SOURCE_CLASS_NAME = "org.mariadb.jdbc.MariaDbDataSource";

	private final JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = STATISTICS_PERSISTENCE_XA_PROPERTIES)
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.statistics.xa-properties")
	public Properties statisticsPersistenceXaProperties() {
		return new Properties();
	}

	@Bean(name = STATISTICS_PERSISTENCE_JPA_PROPERTIES)
	@ConfigurationProperties(prefix = "statistics.persistence.jpa")
	public JpaProperties statisticsPersistenceJpaProperties() {
		return new JpaProperties();
	}

	@Bean(name = STATISTICS_PERSISTENCE_HIBERNATE_PROPERTIES)
	@ConfigurationProperties(prefix = "statistics.persistence.jpa.hibernate")
	public HibernateProperties statisticsPersistenceHibernateProperties() {
		return new HibernateProperties();
	}

	@Override
	@Bean(name = STATISTICS_PERSISTENCE_XA_DATA_SOURCE)
	@ConfigurationProperties("statistics.persistence.datasource")
	public DataSource dataSource(
		final @Qualifier(STATISTICS_PERSISTENCE_XA_PROPERTIES) Properties properties
	) {
		final var atomikosDataSourceBean = new AtomikosDataSourceBean();

		atomikosDataSourceBean.setPoolSize(STATISTICS_PERSISTENCE_XA_DATA_SOURCE_POOL_SIZE);
		atomikosDataSourceBean.setUniqueResourceName(STATISTICS_PERSISTENCE_UNIQUE_RESOURCE_NAME);
		atomikosDataSourceBean.setXaDataSourceClassName(STATISTICS_XA_DATA_SOURCE_CLASS_NAME);
		atomikosDataSourceBean.setXaProperties(properties);

		return atomikosDataSourceBean;
	}

	@Override
	@Bean(name = STATISTICS_PERSISTENCE_ENTITY_MANAGER_FACTORY)
	@DependsOn(JTA_PERSISTENCE_TRANSACTION_MANAGER)
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
		final @Qualifier(STATISTICS_PERSISTENCE_XA_DATA_SOURCE) DataSource dataSource
	) {
		final var localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan(STATISTICS_PERSISTENCE_PACKAGE);
		localContainerEntityManagerFactoryBean.setPersistenceUnitName(STATISTICS_PERSISTENCE_UNIT);
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaPropertyMap());

		return localContainerEntityManagerFactoryBean;
	}

	@Override
	protected Map<String, Object> jpaPropertyMap() {
		final var propertiesMap = statisticsPersistenceHibernateProperties().determineHibernateProperties(
			statisticsPersistenceJpaProperties().getProperties(),
			new HibernateSettings()
		);

		propertiesMap.putAll(super.jpaPropertyMap());

		return propertiesMap;
	}
}
