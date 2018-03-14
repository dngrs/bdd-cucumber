package utils;

import cucumber.api.Transformer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Igor Odokienko.
 */
public class DateMapper extends Transformer<LocalDate> {

    @Override
    public LocalDate transform(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
