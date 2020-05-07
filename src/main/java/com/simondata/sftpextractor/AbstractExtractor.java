package com.simondata.sftpextractor;

/**
 * The primary class that drives all extractions. Immediate descendants of this class should be
 * differentiated by the high-level category of data source they represent, e.g. "Database" or "SFTP"
 */
public class AbstractExtractor {

    /**
     * Performs the actual extract from the data source and writes the extracted
     * data to the location configured by the launch arguments
     */
    public void extract() {

    }
}
