package kr.co.mz.jira.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticsJpaQueryDslConfig {

	@PersistenceContext(unitName = StatisticsJpaDataSourceConfig.STATISTICS_PERSISTENCE_UNIT)
	private EntityManager statisticsJpaEntityManager;

	@Bean
	public JPAQueryFactory statisticsJpaQueryFactory() {
		return new JPAQueryFactory(statisticsJpaEntityManager);
	}
}
