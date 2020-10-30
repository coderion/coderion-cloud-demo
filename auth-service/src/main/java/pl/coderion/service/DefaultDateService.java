package pl.coderion.service;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import pl.coderion.configuration.ConstantValues;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultDateService implements DateService {

    private final DateTimeZone timeZone;

    /**
     * Force system-wide timezone to ensure consistent
     * dates over all servers, independently from the region
     * the server is running.
     */
    public DefaultDateService(final DateTimeZone timeZone) {
        super();
        this.timeZone = checkNotNull(timeZone);

        System.setProperty("user.timezone", timeZone.getID());
        TimeZone.setDefault(timeZone.toTimeZone());
        DateTimeZone.setDefault(timeZone);
    }

    @Override
    public DateTime now() {
        return DateTime.now(timeZone);
    }

    @Override
    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(getSystemTimezone()).toInstant());
    }

    private ZoneId getSystemTimezone() {
        return timeZone.toTimeZone().toZoneId();
    }

    private ZoneId getClientTimezone() {
        return ZoneId.of(ConstantValues.DEFAULT_TIMEZONE);
    }
}