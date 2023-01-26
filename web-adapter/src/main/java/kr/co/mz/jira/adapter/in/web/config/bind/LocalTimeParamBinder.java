package kr.co.mz.jira.adapter.in.web.config.bind;

import java.time.LocalTime;
import kr.co.mz.support.converter.DefaultDateTimeConverter;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeParamBinder implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(final String time) {
        return DefaultDateTimeConverter.convertTime(time);
    }
}
