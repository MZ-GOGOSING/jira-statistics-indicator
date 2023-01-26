package kr.co.mz.jira;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "kr.co.mz.jira.document",
    "kr.co.mz.jira.support"
})
public class MicrosoftDocumentConfig {

}
