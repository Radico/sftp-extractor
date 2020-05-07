# sftp-extractor

## Project and Rationale

## Running

### System Dependencies
* Gradle
* Java 8

### Build
```$sh
gradle shadowJar
```

### Run
Required Parameters:
* `-u` `--user`: The username to connect with (required)
* `-t` `--type`: The engine type to use

Optional Parameters:
* `-h` `--host`: The host to connect to (defaults to `localhost`)
* `-p` `--port`: The port to connect to (defaults to the standard port for the given engine e.g. 22 for SFTP)
* `-f` `--file`: The output file to write to (defaults to a basic filename)

Passwords are passed by TTY input or passed through a `EXTRACT_DB_PASSWORD` environment variable.

```$sh
java -jar build/libs/sql-extractor/sql-extractor-1.0-SNAPSHOT-all.jar \
    -u <USERNAME> \
    -h <HOST> \
    -p <PORT> \
    -d <DATABASE NAME> \
    -t <TYPE> \
    -c <CASE> (default, snake, camel)
    -s /path/to/query.sql \
    -f /path/to/outputfile.jsonl
```

### Tests
```$sh
gradle test
```

### Deploy

Currently we run this via Python (see `jdbc.py`) in Jenkins. Deploying has two steps.
First, we copy the fat jar up to S3, and then we copy that file to each Jenkins box.

```$sh
aws s3 cp build/libs/sql-extractor-1.0-SNAPSHOT-all.jar s3://prod.radi.co/build/libs/sql-extractor-1.0-SNAPSHOT-all.jar

djprod fab jenkins.deploy_jdbc_extractor
```

## Additional Information

### Security

Passwords can be passed via an environment variable: `EXTRACT_DB_PASSWORD`, 
however, the preferred method is to use the password console prompt.
