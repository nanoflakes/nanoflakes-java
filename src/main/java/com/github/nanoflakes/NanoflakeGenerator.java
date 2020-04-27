package com.github.nanoflakes;

import java.time.OffsetDateTime;

/**
 * A generator of {@linkplain Nanoflake nanoflakes}.
 */
public interface NanoflakeGenerator {
    /**
     * Gets the next {@linkplain Nanoflake nanoflake}.
     *
     * @return a new, generated nanoflake.
     */
    Nanoflake next();

    /**
     * Gets this nanoflake generator's epoch.
     *
     * @return the epoch in milliseconds.
     */
    long epochMillis();

    /**
     * Gets this nanoflake generator's epoch as an {@link OffsetDateTime}.
     *
     * @return a {@link OffsetDateTime} set to the epoch time.
     */
    default OffsetDateTime epoch() {
        return Nanoflakes.toDateTime(epochMillis());
    }
}