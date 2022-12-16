package kr.co.mz.jira.container.web.bind;

import java.time.LocalDateTime;
import kr.co.mz.jira.support.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeParamBinder implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(final String dateTime) {
        return DefaultDateTimeConverter.convertDateTime(dateTime);
    }
}





