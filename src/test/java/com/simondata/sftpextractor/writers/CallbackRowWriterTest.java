package com.simondata.sftpextractor.writers;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class CallbackRowWriterTest {
    @Test
    public void writeQuery() throws Exception {
        Map<String, Object> test1 = new HashMap<>();
        test1.put("a", "abc");
        test1.put("b", 1);
        Map<String, Object> test2 = new HashMap<>();
        test2.put("a", "jkl");
        test2.put("b", 2);

        AtomicInteger invocationCount = new AtomicInteger();

        Function<Map<String, Object>, ?> callback = (Function<Map<String, Object>, Object>) stringObjectMap -> {
            invocationCount.incrementAndGet();
            return null;
        };

        List<Map<String, Object>> input = Arrays.asList(test1, test2);
        CallbackRowWriter subject = new CallbackRowWriter(callback);
        int lines = subject.writeRows(input);
        assertEquals(2, invocationCount.get());
        assertEquals(2, lines);
    }
}
