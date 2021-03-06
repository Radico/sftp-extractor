package com.simondata.sftpextractor.clients;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.simondata.sftpextractor.monitors.*;

/**
 * <h1>SFTP Client</h1>
 * Provide facilities for accessing SFTP
 *
 * @author  Jared Schwantz
 * @since   2020-04-23
 */

public class SFTPClient {
    private static final int DEFAULT_PORT = 22;
    private final Logger logger = LoggerFactory.getLogger(SFTPClient.class);
    protected SFTPParams params;
    protected Session sftpSession;
    protected ChannelSftp sftpChannel;

    /**
     * Constructor
     * @param params SFTPParams for the connection.
     */

    public SFTPClient(SFTPParams params) {
        this.params = params;
    }

    protected Session initSession() {
        Session session = getJschSession();
        session.setPassword(this.params.getPassword());
        
        java.util.Properties config = new java.util.Properties(); 
        config.put("StrictHostKeyChecking", Boolean.toString(this.params.getCheckHostKey()));
        if (this.params.getSftpCompression()) {
            config.put("compression.s2c", "zlib@openssh.com,zlib,none");
            config.put("compression.c2s", "zlib@openssh.com,zlib,none");
            config.put("compression_level", "9");
        }
        session.setConfig(config);
        
        return session;
    }

    protected Session getJschSession() {
        Session session = null;

        JSch jsch = new JSch();
        try {
            session = jsch.getSession(this.params.getUser(), this.params.getHost(), this.params.getPort(DEFAULT_PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
    
    protected ChannelSftp initChannelSftp(Session session) {
        ChannelSftp sftpChannel = null;
        try {
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            logger.info("SFTP connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftpChannel;
    }

    protected ChannelSftp openSessionChannelSftp() {
        ChannelSftp sftpChannel = null;
        try {
            Session session = initSession();
            this.sftpSession = session;
            this.sftpSession.connect();
            sftpChannel = initChannelSftp(this.sftpSession);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return sftpChannel;
    }

    protected void closeSessionChannelSftp() {
        try {
            this.sftpChannel.exit();
            this.sftpSession.disconnect();
            this.sftpSession = null;
            logger.info("SFTP disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(String inputFile, String outputFile) {
        try {
            ChannelSftp sftpChannel = this.openSessionChannelSftp();
            this.sftpChannel = sftpChannel;
            this.sftpChannel.get(inputFile, outputFile, new SFTPLogPercentDone());
            logger.info("Downloaded the file from " + inputFile + " to " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeSessionChannelSftp();
        }
    }
}
