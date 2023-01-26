package kr.co.mz;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.co.mz.*.adapter.in.web"})
public class WebAdapterConfig {

}
