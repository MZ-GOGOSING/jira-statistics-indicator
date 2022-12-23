package kr.co.mz.jira.jpa.converter;

import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Converter(autoApply = true)
public class IssueLabelConverter implements AttributeConverter<Set<String>, String> {

  private static final String ISSUE_KEY_DELIMITER_CHAR = ",";

  private static final String ISSUE_KEY_SPLIT_REGEX = "\\s*,\\s*";

  @Override
  public String convertToDatabaseColumn(final Set<String> attribute) {
    return String.join(
        ISSUE_KEY_DELIMITER_CHAR,
        CollectionUtils.emptyIfNull(attribute)
    );
  }

  @Override
  public Set<String> convertToEntityAttribute(final String dbData) {
    return Set.of(StringUtils.defaultString(dbData).split(ISSUE_KEY_SPLIT_REGEX));
  }
}
