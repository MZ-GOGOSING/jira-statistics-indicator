package kr.co.mz.jira.container.web.bind;

import java.time.LocalDate;
import kr.co.mz.jira.support.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class LocalDateParamBinder implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(final String date) {
        return DefaultDateTimeConverter.convertDate(date);
    }
}
