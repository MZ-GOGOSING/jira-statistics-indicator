package kr.co.mz.support.converter;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeConverter {

  public static LocalDateTime fromJoda(final org.joda.time.DateTime jodaDateTime) {
    return Optional.ofNullable(jodaDateTime)
        .map(DateTime::toLocalDateTime)
        .map(LocalDateTimeConverter::fromJoda)
        .orElse(null);
  }

  public static LocalDateTime fromJoda(final org.joda.time.LocalDateTime jodaLocalDateTime) {
    return Optional.ofNullable(jodaLocalDateTime)
        .map(source -> LocalDateTime.of(
            source.getYear(),
            source.getMonthOfYear(),
            source.getDayOfMonth(),
            source.getHourOfDay(),
            source.getMinuteOfHour(),
            source.getSecondOfMinute(),
            source.getMillisOfSecond()
        ))
        .orElse(null);
  }
}
