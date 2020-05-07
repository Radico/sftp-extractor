package com.simondata.sftpextractor;

import com.simondata.sftpextractor.clients.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SFTPExtractor is the primary class to use and is designed for most use cases.
 */
public class SFTPExtractor extends AbstractExtractor {

    private final static Logger logger = LoggerFactory.getLogger(ExtractorRunner.class);

    private final SFTPClient sftpClient;
    private ParamsHolder paramsHolder;

    /**
     * Primary constructor
     * @param sftpParams the SFTPParams to use when building the connection.
     */
    private SFTPExtractor(SFTPParams sftpParams) {
        this.sftpClient = ClientFactory.makeSFTPClient("sftp", sftpParams);
    }

    public SFTPExtractor(ParamsHolder paramsHolder) {
        this(paramsHolder.getSftpParams());
        this.paramsHolder = paramsHolder;
    }

    public void extract() {
            this.extract(
                this.paramsHolder.getInputSftpFile(),
                this.paramsHolder.getOutputFile());
    }

    private void extract(String inputFile, String outputFile) {
        try {
            this.sftpClient.downloadFile(inputFile, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
