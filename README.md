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

### Publish

First publish a new release to [JFrog Artifactory](https://simondata.jfrog.io/ui/repos/tree/General/gradle-virtual/gradle-int/sftp-extractor)
```sh
gradle clean shadowJar artifactoryPublish
```

You will see a version pushed like `sftp-extractor-0.0.x-all.jar` in the output of the publish.

Next, update Jenkins build argument for the version to pull into the container.
[Jenkins Build Base Container](https://misc.automation.simondata.net/job/build-base-web-container/configure)

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

## Additional Information

### Security

Passwords can be passed via an environment variable: `EXTRACT_DB_PASSWORD`, 
however, the preferred method is to use the password console prompt.
