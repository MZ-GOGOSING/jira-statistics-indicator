package kr.co.mz.jira;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {
    "kr.co.mz.jira.api",
    "kr.co.mz.jira.support"
})
public class JiraApiConfig {

}
