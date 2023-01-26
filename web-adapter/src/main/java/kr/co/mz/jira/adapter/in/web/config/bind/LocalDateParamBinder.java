package kr.co.mz.jira.adapter.in.web.config.bind;

import java.time.LocalDate;
import kr.co.mz.support.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class LocalDateParamBinder implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(final String date) {
        return DefaultDateTimeConverter.convertDate(date);
    }
}
