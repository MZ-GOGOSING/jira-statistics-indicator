package kr.co.mz.jira;

import kr.co.mz.jira.container.WebappConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
	WebappConfig.class,
	WebAdapterConfig.class,
	ApplicationConfig.class,
	StatisticsPersistenceAdapterConfig.class,
	JiraApiAdapterConfig.class,
	SpreadsheetDocumentAdapterConfig.class,
	StatisticsJpaConfig.class,
	JiraApiConfig.class,
	MicrosoftDocumentConfig.class
})
public class ApplicationInitializr {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationInitializr.class, args);
	}
}
