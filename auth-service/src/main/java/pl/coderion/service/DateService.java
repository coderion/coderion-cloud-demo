package pl.coderion.service;

import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateService {

    /**
     * @return current date at the moment of the call
     */
    DateTime now();

    /**
     * Converts LocalDateTime to Date object
     * @param localDateTime
     * @return
     */
    Date toDate(LocalDateTime localDateTime);
}
