package kr.co.mz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "kr.co.mz.*.adapter.out.web")
public class JiraRestAdapterConfig {

}
