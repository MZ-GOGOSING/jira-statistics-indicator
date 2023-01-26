package kr.co.mz;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
@ComponentScan(basePackages = {"kr.co.mz.*.rest"})
public class JiraRestClientConfig {

}
