package kr.co.mz.jira.rest.client;

import com.atlassian.jira.rest.client.api.MetadataRestClient;
import com.atlassian.jira.rest.client.api.domain.Field;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
public class MetadataRestClientComponent {

  private final MetadataRestClient metadataRestClient;

  public Set<Field> loadAll() {
    final var fields = metadataRestClient.getFields().claim();

    return StreamSupport
        .stream(fields.spliterator(), false)
        .collect(Collectors.toSet());
  }
}
