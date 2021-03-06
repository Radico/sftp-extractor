package com.simondata.sftpextractor.clients;

import com.simondata.sftpextractor.writers.FileOutputFormat;

/**
 * SQLParams
 * SQLParams are provided to the SQL client and are used in setting up an initial connection.
 * They will be reused as long as the client is in use.
 *
 * @author Chet Mancini
 * @since   2019-03-31
 */
public class ParamsHolder {

    // private final static Logger logger = LoggerFactory.getLogger(ParamsHolder.class);

    private String extractorType;
    private ExtractorEngine extractorEngine;
    private ClientEngine clientEngine;
    private SQLParams sqlParams;
    private SFTPParams sftpParams;
    private FormattingParams formattingParams;
    private String inputSql;
    private String inputSftpFile;
    private String outputFile;
    private FileOutputFormat outputFormat;
    private QueryParams queryParams;

    public ParamsHolder(String extractorType, ClientEngine clientEngine, SQLParams sqlParams,
            SFTPParams sftpParams, FormattingParams formattingParams, String inputSql, String inputSftpFile,
            String outputFile, FileOutputFormat outputFormat, QueryParams queryParams) {
        this.extractorType = extractorType;
        this.extractorEngine = null;
        this.clientEngine = clientEngine;
        this.sqlParams = sqlParams;
        this.sftpParams = sftpParams;
        this.formattingParams = formattingParams;
        this.inputSql = inputSql;
        this.inputSftpFile = inputSftpFile;
        this.outputFile = outputFile;
        this.outputFormat = outputFormat;
        this.queryParams = queryParams;
    }

    // Skipping setting most variables for now
    public String getExtractorType() {
        return this.extractorType;
    }

    public ExtractorEngine getExtractorEngine() {
        return this.extractorEngine;
    }

    public void setExtractorEngine(ExtractorEngine engine) {
        this.extractorEngine = engine;
    }

    public ClientEngine getClientEngine() {
        return this.clientEngine;
    }

    public SQLParams getSqlParams() {
        return this.sqlParams;
    }

    public SFTPParams getSftpParams() {
        return this.sftpParams;
    }

    public FormattingParams getFormattingParams() {
        return this.formattingParams;
    }

    public String getInputSql() {
        return this.inputSql;
    }

    public String getInputSftpFile() {
        return this.inputSftpFile;
    }

    public String getOutputFile() {
        return this.outputFile;
    }

    public FileOutputFormat getOutputFormat() {
        return this.outputFormat;
    }

    public QueryParams getQueryParams() {
        return this.queryParams;
    }
}
