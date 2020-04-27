package com.github.nanoflakes;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A <a href="https://github.com/nanoflakes/nanoflakes">Nanoflake</a>.
 */
public class Nanoflake {
    /**
     * The base epoch of the nanoflake.
     */
    private final long epoch;

    /**
     * The value of the nanoflake.
     */
    private final long value;

    /**
     * Creates a new nanoflake.
     *
     * @param epoch the nanoflake's epoch.
     * @param value the nanoflake's value.
     */
    public Nanoflake(long epoch, long value) {
        this.epoch = epoch;
        this.value = value;
    }

    /**
     * Creates a new nanoflake, parsing the value from a string.
     *
     * @param epoch the nanoflake's epoch.
     * @param value the nanoflake's value.
     */
    public Nanoflake(long epoch, String value) {
        this.epoch = epoch;
        this.value = Long.decode(value);
    }

    /**
     * Creates a new nanoflake, parsing the value from a string with a radix.
     *
     * @param epoch the nanoflake's epoch.
     * @param value the nanoflake's value.
     * @param radix the radix of the nanoflake.
     */
    public Nanoflake(long epoch, String value, int radix) {
        this.epoch = epoch;
        this.value = Long.parseLong(value, radix);
    }

    /**
     * Gets the nanoflake value as a Long.
     *
     * @return the value as a long.
     */
    public long asLong() {
        return value;
    }

    /**
     * Gets the nanoflake value as a string.
     *
     * @return the value as a String.
     */
    public String asString() {
        return Long.toString(value);
    }
    /**
     * Gets the nanoflake value as a string, using a specified radix.
     *
     * @param radix the radix.
     * @return the value as a String.
     */
    public String withRadix(int radix) {
        return Long.toString(value, radix);
    }

    /**
     * Gets the creation time as an {@link OffsetDateTime}.
     *
     * @return a {@link OffsetDateTime} set to the creation time.
     */
    public OffsetDateTime creationTime() {
        return Nanoflakes.toDateTime(creationTimeMillis());
    }

    /**
     * Gets the creation time.
     *
     * @return the creation time in milliseconds.
     */
    public long creationTimeMillis() {
        return epoch + Nanoflakes.timestampValue(value);
    }

    /**
     * Gets this nanoflake's epoch.
     *
     * @return the epoch in milliseconds.
     */
    public long epochMillis() {
        return epoch;
    }

    /**
     * Gets this nanoflake's epoch as an {@link OffsetDateTime}.
     *
     * @return a {@link OffsetDateTime} set to the epoch time.
     */
    public OffsetDateTime epoch() {
        return Nanoflakes.toDateTime(epochMillis());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nanoflake nanoflake = (Nanoflake) o;
        return epoch == nanoflake.epoch &&
            value == nanoflake.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(epoch, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Nanoflake{" + "epoch=" + epoch + ", value=" + value + '}';
    }
}
