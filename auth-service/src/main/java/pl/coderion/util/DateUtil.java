package pl.coderion.util;

import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDateTime plusSeconds(LocalDateTime date, long numberOfSeconds) {
        return date.plusSeconds(numberOfSeconds);
    }
}
