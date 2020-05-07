package com.simondata.sftpextractor.clients;

import org.apache.commons.lang3.EnumUtils;

/**
 * <h1>SFTPEngine</h1>
 * The type of SFTP client to connect to.
 */
public enum SftpEngine {
    SFTP;

    private static String normalizeName(String name) {
        String result;
        String cleaned = name.toUpperCase().replaceAll(" ", "_");
        switch (cleaned) {
            case "SFTP":
                result = SFTP.name();
                break;
            default:
                result = name.toUpperCase();
                break;
        }
        return result;
    }

    /**
     * Safer version of converting a string to enum.
     * Resiliant against differences in common names (MySQL vs MariaDB)
     * or Postgres vs Postgresql.
     * Consistent naming pattern with SQL engine for now for future use.
     * Case insensitive
     * @param name the String name of the db type to parse.
     * @return The SQLEngine that matches the given name.
     */
    public static SftpEngine byName(String name) {
        return EnumUtils.getEnum(SftpEngine.class, normalizeName(name));
    }

}
