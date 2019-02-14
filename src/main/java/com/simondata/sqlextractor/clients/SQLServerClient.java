package com.simondata.sqlextractor.clients;

import com.microsoft.sqlserver.jdbc.*;

import javax.sql.DataSource;

public class SQLServerClient extends AbstractSQLClient {

    private static final int DEFAULT_PORT = 1433;
    private static final String DEFAULT_HOST = "localhost";

    public SQLServerClient(SQLParams params) {
        super(params);
    }

    @Override
    public DataSource initDataSource() {
        SQLServerSQLParams params = (SQLServerSQLParams) this.params;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(params.getUser());
        ds.setPassword(params.getPassword());
        ds.setServerName(params.getHost(DEFAULT_HOST));
        ds.setPortNumber(params.getPort(DEFAULT_PORT));
        ds.setDatabaseName(params.getDatabase());
        ds.setEncrypt(params.getEncrypt());
        ds.setTrustServerCertificate(params.getTrustServerCertificate());
        ds.setAccessToken(params.getAccessToken());
        if  (params.getAuthentication() != null) {
            ds.setAuthentication(params.getAuthentication());
        }
        if  (params.getAuthenticationScheme() != null) {
            ds.setAuthenticationScheme(params.getAuthenticationScheme());
        }
        if  (params.getColumnEncryptionSetting() != null) {
            ds.setColumnEncryptionSetting(params.getColumnEncryptionSetting());
        }
        if  (params.getFailoverPartner() != null) {
            ds.setFailoverPartner(params.getFailoverPartner());
        }
        if  (params.getIntegratedSecurity() != null) {
            ds.setIntegratedSecurity(params.getIntegratedSecurity());
        }
        if (params.getHostNameInCertificate() != null) {
            ds.setHostNameInCertificate(params.getHostNameInCertificate());
        }
        return ds;
    }

    @Override
    protected String getDriverName() {
        return "com.microsoft.sqlserver.jdbc.Driver";
    }

}
