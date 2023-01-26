package kr.co.mz.support.validation;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import kr.co.mz.support.dto.LocalDateRangeQuery;
import org.apache.commons.lang3.math.NumberUtils;

public class BetweenDateValidator implements ConstraintValidator<BetweenDate, LocalDateRangeQuery> {

  private static final String ANY_NULL_CONTAINS_MESSAGE = "시작일과 종료일 모두 지정되어야 합니다.";

  private static final String INVALID_TIME_MESSAGE = "시작일이 종료일 보다 미래일 수 없습니다.";

  private static final String INVALID_EXCEED_RANGE_MESSAGE = "최대 검색 가능 기간은 %d일 입니다.";

  private boolean required;

  private int maximumDateRangeLimit;

  @Override
  public void initialize(final BetweenDate constraint) {
    this.required = constraint.required();
    this.maximumDateRangeLimit = constraint.maximumDateRangeLimit();
  }

  @Override
  public boolean isValid(final LocalDateRangeQuery value, final ConstraintValidatorContext context) {
    if (!this.required || isNull(value)) {
      return Boolean.TRUE;
    }
    return valid(value, context);
  }

  private boolean valid(final LocalDateRangeQuery source, final ConstraintValidatorContext context) {
    final var invalidMessage = getInvalidMessage(source);

    if (invalidMessage.isEmpty()) {
      return Boolean.TRUE;
    }

    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(invalidMessage.get()).addConstraintViolation();

    return Boolean.FALSE;
  }

  private Optional<String> getInvalidMessage(final LocalDateRangeQuery source) {
    return getValidationFunctionEntries()
        .entrySet()
        .stream()
        .filter(entry -> isFalse(entry.getKey().apply(source)))
        .map(Entry::getValue)
        .findFirst();
  }

  private Map<Function<LocalDateRangeQuery, Boolean>, String> getValidationFunctionEntries() {
    final var validationFunctionEntries = new LinkedHashMap<Function<LocalDateRangeQuery, Boolean>, String>();

    validationFunctionEntries.put(this::isAllNotNull, ANY_NULL_CONTAINS_MESSAGE);
    validationFunctionEntries.put(this::isValidTime, INVALID_TIME_MESSAGE);
    validationFunctionEntries.put(
        this::isValidRange,
        String.format(INVALID_EXCEED_RANGE_MESSAGE, this.maximumDateRangeLimit)
    );

    return validationFunctionEntries;
  }

  private Boolean isAllNotNull(final LocalDateRangeQuery value) {
    return allNotNull(value.getStartDate(), value.getEndDate());
  }

  private Boolean isValidTime(final LocalDateRangeQuery value) {
    if (!isAllNotNull(value)) {
      return Boolean.FALSE;
    }

    final var from = value.getStartDate();
    final var to = value.getEndDate();

    final var intervalValue = to.compareTo(from);
    final var comparatorValue = NumberUtils.compare(intervalValue, INTEGER_ZERO);

    return comparatorValue > INTEGER_MINUS_ONE;
  }

  private Boolean isValidRange(final LocalDateRangeQuery value) {
    if (!isAllNotNull(value)) {
      return Boolean.FALSE;
    }

    final var from = value.getStartDate();
    final var to = value.getEndDate();

    final var intervalValue = ChronoUnit.DAYS.between(from, to);

    return Math.abs(intervalValue) <= this.maximumDateRangeLimit;
  }
}
