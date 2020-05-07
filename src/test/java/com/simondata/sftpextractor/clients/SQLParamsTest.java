package com.simondata.sftpextractor.clients;

import org.junit.Test;

import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class SQLParamsTest {

    @Test
    public void testConstructor() {
        String host = "fake.database";
        Integer port = 999;
        String user = "admin";
        String password = "******";
        String database = "main";
        SQLParams queryParams = new SQLParams(host, port, user, password, database);
        assertEquals(host, queryParams.getHost());
        assertEquals(port, queryParams.getPort());
        assertEquals(user, queryParams.getUser());
        assertEquals(password, queryParams.getPassword());
        assertEquals(database, queryParams.getDatabase());
    }

    @Test
    public void testGetWithDefaultsIfNull() {
        String host = null;
        Integer port = null;
        String user = null;
        String password = null;
        String database = null;
        SQLParams queryParams = new SQLParams(host, port, user, password, database);
        assertEquals("localhost", queryParams.getHost("localhost"));
        assertEquals((Integer) 1234, queryParams.getPort(1234));
    }

    @Test
    public void testGetWithDefaultsIfSet() {
        String host = "fake.database";
        Integer port = 999;
        String user = "admin";
        String password = "******";
        String database = "main";
        SQLParams queryParams = new SQLParams(host, port, user, password, database);
        assertEquals(host, queryParams.getHost("localhost"));
        assertEquals(port, queryParams.getPort(1234));
    }

    @Test
    public void testGetProperties() {
        String host = "fake.database";
        Integer port = 999;
        String user = "admin";
        String password = "******";
        String database = "main";
        Properties props = new Properties();
        props.setProperty("testInt", "1234");
        props.setProperty("testBoolean", "false");
        props.setProperty("testStr", "val");

        SQLParams queryParams = new SQLParams(host, port, user, password, database, props);
        assertEquals((Integer) 1234, queryParams.getPropertyAsInteger("testInt"));
        assertFalse(queryParams.getPropertyAsBoolean("testBoolean"));
        assertEquals("val", queryParams.getPropertyAsString("testStr"));
    }
}
