package kr.co.mz.jira.jpa.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Converter(autoApply = true)
public class IssueKeyConverter implements AttributeConverter<List<String>, String> {

  private static final String ISSUE_KEY_DELIMITER_CHAR = ",";

  private static final String ISSUE_KEY_SPLIT_REGEX = "\\s*,\\s*";

  @Override
  public String convertToDatabaseColumn(final List<String> attribute) {
    return CollectionUtils.emptyIfNull(attribute)
        .stream()
        .sorted()
        .collect(Collectors.joining(ISSUE_KEY_DELIMITER_CHAR));
  }

  @Override
  public List<String> convertToEntityAttribute(final String dbData) {
    return Arrays.stream(StringUtils.defaultString(dbData).split(ISSUE_KEY_SPLIT_REGEX))
        .sorted()
        .collect(Collectors.toList());
  }
}
