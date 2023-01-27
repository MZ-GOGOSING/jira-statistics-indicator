package kr.co.mz.jira.adapter.out.document.properties;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Getter
@Setter
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "jira.project")
public class JiraProjectProperties {

  @NotEmpty
  private List<String> workflow;
}
