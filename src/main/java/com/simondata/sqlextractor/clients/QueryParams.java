package com.simondata.sqlextractor.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryParams implements InputParams {

    private final static Logger logger = LoggerFactory.getLogger(InputParams.class);

    private static final Integer DEFAULT_FETCH_SIZE = 10000;

    private Integer fetchSize;
    private Integer maxRows;
    private Integer timeout;


    public QueryParams() {
        this.fetchSize = DEFAULT_FETCH_SIZE;
    }

    public QueryParams(Integer fetchSize, Integer maxRows, Integer timeout) {
        this.fetchSize = fetchSize;
        this.maxRows = maxRows;
        this.timeout = timeout;
    }

    public Integer getFetchSize() {
        if (fetchSize == null) {
            return DEFAULT_FETCH_SIZE;
        } else {
            return fetchSize;
        }
    }

    public Integer getMaxRows() {
        return this.maxRows;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public static QueryParams getDefaultQueryParams() {
        return new QueryParams();
    }

    @Override
    public void logValues() {
        logger.info("Query Max Rows: " + this.getMaxRows());
        logger.info("Query Timeout: " + this.getTimeout());
        logger.info("Query Fetch Size: " + this.getFetchSize());
    }
}