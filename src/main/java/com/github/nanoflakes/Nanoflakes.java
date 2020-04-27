package com.github.nanoflakes;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Utility methods and constants related to Nanoflakes.
 */
public class Nanoflakes {
    /**
     * How much of the nanoflake is occupied by the timestamp of the ID, in bits.
     */
    public static final long TIMESTAMP_BITS = 14;
    /**
     * How much of an nanoflake is occupied by the generator ID, in bits.
     */
    public static final long GENERATOR_ID_BITS = 10;
    /**
     * How much of an nanoflake is occupied by sequence number, in bits.
     */
    public static final long SEQUENCE_BITS = 12;
    /**
     * The maximum ID possible for a generator.
     */
    public static final long MAX_GENERATOR_ID = 1023;
    /**
     * The max sequence value of a snowflake.
     */
    public static final long MAX_SEQUENCE = 4095;
    /**
     * The value used for generator id-based shifts.
     */
    public static final long GENERATOR_ID_SHIFT = SEQUENCE_BITS;
    /**
     * The value used for timestamp-based shifts.
     */
    public static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + GENERATOR_ID_SHIFT;

    /**
     * Creates a local generator.
     * @param epoch base epoch
     * @param generatorId the generator id.
     * @return a new local nanoflake generator.
     */
    public static NanoflakeGenerator localGenerator(long epoch, long generatorId) {
        return new NanoflakeLocalGenerator(epoch, generatorId);
    }

    /**
     * Gets the timestamp of a nanoflake.
     * @param id the nanoflake.
     * @return the raw timestamp.
     */
    public static long timestampValue(long id) {
        return id >> TIMESTAMP_SHIFT;
    }

    /**
     * Gets the generator ID of a nanoflake.
     * @param id the nanoflake.
     * @return the generator ID.
     */
    public static long generatorValue(long id) {
        return id >> GENERATOR_ID_SHIFT & MAX_GENERATOR_ID;
    }

    /**
     * Gets the sequence ID of a nanoflake.
     * @param id the nanoflake.
     * @return the sequence ID.
     */
    public static long sequenceValue(long id) {
        return id & MAX_SEQUENCE;
    }

    /**
     * Converts an UTC time in milliseconds to an {@link OffsetDateTime}.
     * @param millis UTC time, in milliseconds.
     * @return an instance of {@link OffsetDateTime}.
     */
    public static OffsetDateTime toDateTime(long millis) {
        Calendar gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmt.setTimeInMillis(millis);
        return OffsetDateTime.ofInstant(gmt.toInstant(), gmt.getTimeZone().toZoneId());
    }
}
