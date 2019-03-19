/*
Copyright 2019-present, Simon Data, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at:
http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.simondata.sqlextractor.writers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class FileRowWriter implements RowWriter {

    private final Logger logger = LoggerFactory.getLogger(FileRowWriter.class);

    static String ENCODING = "UTF-8";

    protected PrintWriter writer = null;

    public abstract void writeRow(Map<String, Object> row);

    public int writeRows(List<Map<String, Object>> rows) {
        AtomicInteger counter = new AtomicInteger();
        rows.forEach(row -> {
            this.writeRow(row);
            counter.getAndIncrement();
        });
        return counter.intValue();
    }

    /**
     * Convenience method.
     * @param filename
     */
    public void open(String filename) {
        this.open(new File(filename));
    }

    public void open(File file) {
        try {
            this.writer = new PrintWriter(file, ENCODING);
            logger.info("Opening file: " + file.getName());
            this.postOpenHook();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void open(OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream);
        this.postOpenHook();
    }

    public void openStdOut() {
        this.open(System.out);
    }

    protected void postOpenHook() {

    }

    protected void postCloseHook() {

    }

    void flush() {
        if (this.writer != null) {
            this.writer.flush();
        }
    }

    public void close() {
        this.flush();
        if (this.writer != null) {
            this.writer.close();
        }
        this.writer = null;
        this.postCloseHook();
    }
}
