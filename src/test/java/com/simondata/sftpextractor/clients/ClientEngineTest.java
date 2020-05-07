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

package com.simondata.sftpextractor.clients;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ClientEngineTest {

    @Test
    public void testByName() throws Exception {
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("mssql"));
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("sqlserver"));
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("sqlServer"));
    }

    @Test
    public void testEachName() {
        assertEquals(ClientEngine.ATHENA, ClientEngine.byName("athena"));
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("sqlserver"));
        assertEquals(ClientEngine.MYSQL, ClientEngine.byName("mysql"));
        assertEquals(ClientEngine.POSTGRESQL, ClientEngine.byName("postgresql"));
        assertEquals(ClientEngine.ORACLE, ClientEngine.byName("oracle"));
        assertEquals(ClientEngine.INFORMIX, ClientEngine.byName("informix"));
        assertEquals(ClientEngine.REDSHIFT, ClientEngine.byName("redshift"));
        assertEquals(ClientEngine.BIGQUERY, ClientEngine.byName("bigquery"));
        assertEquals(ClientEngine.SNOWFLAKE, ClientEngine.byName("snowflake"));
    }

    @Test
    public void testByNameIgnoresSpaces() {
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("ms sql"));
        assertEquals(ClientEngine.SQLSERVER, ClientEngine.byName("SQL Server"));
        assertEquals(ClientEngine.ORACLE, ClientEngine.byName("oracle db"));
        assertEquals(ClientEngine.INFORMIX, ClientEngine.byName("ibm informix"));
    }
}
