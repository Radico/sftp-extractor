package com.simondata.sftpextractor.clients;

import org.apache.commons.lang3.EnumUtils;

/**
 * <h1>ExtractorEngine</h1>
 * The type of Extractor to use.
 */
public enum ExtractorEngine {
    SFTP,
    SQL;

    private static String normalizeName(String name) {
        String result;
        String cleaned = name.toUpperCase().replaceAll(" ", "_");
        switch (cleaned) {
            case "SFTP":
                result = SFTP.name();
                break;
            case "SQL":
                result = SQL.name();
                break;
            default:
                result = name.toUpperCase();
                break;
        }
        return result;
    }

    /**
     * Safer version of converting a string to enum.
     * Consistent usage pattern with SQL engine for now for future use.
     * Case insensitive
     * @param name the String name of the db type to parse.
     * @return The SQLEngine that matches the given name.
     */
    public static ExtractorEngine byName(String name) {
        return EnumUtils.getEnum(ExtractorEngine.class, normalizeName(name));
    }

}
