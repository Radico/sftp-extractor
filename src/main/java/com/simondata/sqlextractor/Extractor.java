package com.simondata.sqlextractor;

import com.simondata.sqlextractor.clients.ClientFactory;
import com.simondata.sqlextractor.clients.SQLClient;
import com.simondata.sqlextractor.clients.SQLParams;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.simondata.sqlextractor.writers.JsonLRowWriter;
import com.simondata.sqlextractor.writers.RowHandler;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Extractor {

    private final static Logger logger = LoggerFactory.getLogger(Extractor.class);

    /*
    TODO these could be configurable.
     */
    private static final String ENV_VAR_PASSWORD_KEY = "EXTRACT_DB_PASSWORD";
    private static final String DEFAULT_OUTPUT_FILENAME = "out.json";

    private static Options getOptions() {
        Options options = new Options();
        options.addRequiredOption("u", "user", true, "user");
        options.addOption("h", "host", true, "host");
        options.addOption("p", "port", true, "port");
        options.addOption("d", "database", true, "database");
        options.addOption("t", "type", true, "Driver type (SQLServer | MySQL | Postgres )");
        options.addOption("s", "sql", true, "SQL file to read");
        options.addOption("o", "print", false, "Print to stdout");
        options.addOption("f", "file", true, "File to write to");
        return options;
    }

    private static String getPassword() {
        /*
          Try to get password from environment var.
          If that doesn't exist prompt on console.
         */
        String value = System.getenv(ENV_VAR_PASSWORD_KEY);
        if (value != null) {
            return value;
        } else {
            Console console = System.console();
            return new String(console.readPassword("Password: "));
        }
    }

    private static void configureLogging() {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "trace");
    }

    private static String readSql(String filename) throws IOException {
        logger.debug("Reading " + filename);
        return new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        configureLogging();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(getOptions(), args);

            String user = line.getOptionValue("user");
            String host = line.getOptionValue("host", "localhost");
            String type = line.getOptionValue("type", "SQLSERVER").toUpperCase();
            String strPort = line.getOptionValue("port");
            Integer port = null;
            if (strPort != null) {
                port = Integer.parseInt(strPort);
            }
            String database = line.getOptionValue("database");
            String password = getPassword();

            SQLParams params = new SQLParams(host, port, user, password, database);

            SQLClient client = ClientFactory.makeSQLClient(type, params);
            try {
                String inputSql = readSql(line.getOptionValue("sql"));
                String outputFile = line.getOptionValue("file", DEFAULT_OUTPUT_FILENAME);
                boolean print  = line.hasOption("print");
                JsonLRowWriter writer = new JsonLRowWriter();
                if (print) {
                    writer.openStdOut();
                } else {
                    writer.open(outputFile);
                }
                RowHandler rh = new RowHandler(writer);
                int rows = client.queryWithHandler(inputSql, rh);
                logger.info("Finished " + rows + " rows");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException exp) {
            logger.error("Parsing failed.  Reason: " + exp.getMessage());
        }
    }
}