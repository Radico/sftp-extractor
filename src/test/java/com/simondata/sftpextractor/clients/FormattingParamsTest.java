package com.simondata.sftpextractor.clients;

import com.simondata.sftpextractor.writers.KeyCaseFormat;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FormattingParamsTest {
    @Test
    public void testConstructor() {
        KeyCaseFormat keyCaseFormat = KeyCaseFormat.SNAKE_CASE;
        FormattingParams formattingParams = new FormattingParams(keyCaseFormat);
        assertEquals(keyCaseFormat, formattingParams.getKeyCaseFormat());
    }

    @Test
    public void testDefaultParams() {
        KeyCaseFormat defaultKeyCase = KeyCaseFormat.DEFAULT;
        assertEquals(defaultKeyCase, FormattingParams.getDefaultFormattingParams().getKeyCaseFormat());
    }
}
