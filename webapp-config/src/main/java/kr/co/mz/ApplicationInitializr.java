package kr.co.mz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
	WebAdapterConfig.class,
	ApplicationConfig.class,
	StatisticsPersistenceAdapterConfig.class,
	JiraRestAdapterConfig.class,
	StatisticsJpaConfig.class,
	JiraRestClientConfig.class
})
public class ApplicationInitializr {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationInitializr.class, args);
	}
}
