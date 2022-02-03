# Json Validation Service
Simple REST-service for validating JSON documents against JSON Schemas

## Requirements
Install scala-sbt (http://www.scala-sbt.org/download.html)

## Functionality
     POST    /schema/SCHEMAID        - Upload a JSON Schema with unique `SCHEMAID`
     GET     /schema/SCHEMAID        - Download a JSON Schema with unique `SCHEMAID`
     POST    /validate/SCHEMAID      - Validate a JSON document against the JSON Schema identified by `SCHEMAID`
## Usage & Development
The following commands are executed from within the base directory (directory containing the build.sbt file)

### Build
    sbt compile

### Run 
    sbt run

### Run unit tests
    sbt test

### Test coverage
    sbt clean coverage test coverageReport

Or from sbt console:

    ;clean ;coverage ;test ;coverageReport

Results will be available in `target/scala-2.12/scoverage-report/index.html`.

## Limitation note
In-memory H2 database-config (in `application.conf`) => restart the app will lose the stored schemas

