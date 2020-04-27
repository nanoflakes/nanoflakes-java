package com.github.nanoflakes;

import java.util.Objects;

import static com.github.nanoflakes.Nanoflakes.*;
import static java.lang.System.currentTimeMillis;

/**
 * A local generator of {@linkplain Nanoflake nanoflakes}.
 */
public class NanoflakeLocalGenerator implements NanoflakeGenerator {
    private final long epoch;
    private final long generatorId;
    private long lastTimestamp = -1L;
    private long sequence = 0L;

    /**
     * Creates a new local generator of {@linkplain Nanoflake nanoflakes}.
     *
     * @param epoch       the generator's epoch.
     * @param generatorId the generator's ID.
     */
    public NanoflakeLocalGenerator(long epoch, long generatorId) {
        if (epoch > System.currentTimeMillis()) {
            throw new IllegalArgumentException("Specified epoch is on the future.");
        }
        if (generatorId < 0 || generatorId > MAX_GENERATOR_ID) {
            throw new IllegalArgumentException("Invalid generator id.");
        }
        this.epoch = epoch;
        this.generatorId = generatorId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Nanoflake next() {
        long timestamp = currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate for " + (lastTimestamp - timestamp) + "milliseconds.");
        }

        synchronized (this) {
            if (lastTimestamp != timestamp) {
                sequence = 0L;
            } else {
                sequence = (sequence + 1) & Nanoflakes.MAX_SEQUENCE;
                if (sequence == 0) timestamp = tilNextMillis(lastTimestamp);
            }

            lastTimestamp = timestamp;

            long value = timestamp - epoch << TIMESTAMP_SHIFT | generatorId << GENERATOR_ID_SHIFT | sequence;
            return new Nanoflake(epoch, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long epochMillis() {
        return epoch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NanoflakeLocalGenerator that = (NanoflakeLocalGenerator) o;
        return epoch == that.epoch &&
            generatorId == that.generatorId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(epoch, generatorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "NanoflakeLocalGenerator{" +
            "epoch=" + epoch +
            ", generatorId=" + generatorId +
            ", lastTimestamp=" + lastTimestamp +
            ", sequence=" + sequence +
            '}';
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) timestamp = currentTimeMillis();
        return timestamp;
    }
}
