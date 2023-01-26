package kr.co.mz;

import kr.co.mz.jira.jpa.config.StatisticsJpaDataSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
    "kr.co.mz.*.jpa",
    "kr.co.mz.support"
})
@Import({StatisticsJpaDataSourceConfig.class})
public class StatisticsJpaConfig {

}
