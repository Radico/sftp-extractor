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
* `-t` `--type`: The engine type to use (sftp)
* `-h` `--host`: The host to connect to (defaults to `localhost`)
* `--inputfile`: The file to download
* `--is_sftp`: Indicating this is using SFTP

Optional Parameters:
* `-p` `--port`: The port to connect to (defaults to the standard port for the given engine e.g. 22 for SFTP)
* `-f` `--file`: The output file to write to (defaults to a basic filename)
* `--compress`: Use compression in transport of file

Passwords are passed by TTY input or passed through a `EXTRACT_DB_PASSWORD` environment variable.

```$sh
java -jar build/libs/sql-extractor/sql-extractor-1.0-SNAPSHOT-all.jar \
    -u <USERNAME> \
    -h <HOST> \
    -p <PORT> \
    -t <TYPE> \
    --inputfile /path/to/file.sql \
    -f /path/to/outputfile.jsonl
    --is_sftp
```

### Tests
```$sh
gradle test
```

### Deploy

Currently we run this via Python (see `java_sftp.py`) in Jenkins. Deploying has two steps.
First, we copy the fat jar up to S3, and then we copy that file to each Jenkins box.

```$sh
aws s3 cp build/libs/sftp-extractor-1.0-SNAPSHOT-all.jar s3://prod.radi.co/build/libs/sftp-extractor-1.0-SNAPSHOT-all.jar

djprod fab <nameTBD>
```

### Publish

Publish a new release to JFrog Artifactory
```sh
gradle artifactoryPublish
```

## Additional Information

### Security

Passwords can be passed via an environment variable: `EXTRACT_DB_PASSWORD`, 
however, the preferred method is to use the password console prompt.
