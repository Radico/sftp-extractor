package com.simondata.sftpextractor.writers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>AbstractRowWriter</h1>
 */
public abstract class AbstractRowWriter implements RowWriter {

    public abstract void writeRow(Map<String, Object> row);

    public int writeRows(List<Map<String, Object>> rows) {
        AtomicInteger counter = new AtomicInteger();
        rows.forEach(row -> {
            this.writeRow(row);
            counter.getAndIncrement();
        });
        return counter.intValue();
    }

    public abstract void close();
}
