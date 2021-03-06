package com.simondata.sftpextractor.monitors;

import com.jcraft.jsch.*;
import java.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SFTPLogPercentDone implements SftpProgressMonitor{
    private final Logger logger = LoggerFactory.getLogger(SFTPLogPercentDone.class);
    private long total = 0;
    private long completed = 0;
    private long percent = 0;
    
    public SFTPLogPercentDone() {}

    public void init(int op, String src, String dest, long max) {
        this.total = max;
        Instant time = Instant.now();
        logger.info("Starting at " + time + " -- 0 of " + this.total);
    }

    public boolean count(long count){
        this.completed += count;
        Instant time = Instant.now();
        long newPercent = (this.completed * 100) / this.total;
        if (newPercent > this.percent){
            this.percent = newPercent;
            logger.info(time + " -- Loading... " + this.percent + "%");
        }
        return(true);
    }

    public void end(){
        Instant time = Instant.now();
        logger.info("Finished download at " + time);
    }
}
