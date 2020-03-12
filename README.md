# Gists

Gists is a REST service automation testing framework using:

- [REST-assured](http://rest-assured.io/) library for REST service testing
- [Cucumber JVM](https://cucumber.io/) Behavior-Driven Development tool
- [JUnit](https://junit.org/junit4/) testing framework
- [JSON Schema Validator](https://github.com/everit-org/json-schema) for JSON schema validation
- [Cluecumber Report Maven Plugin](https://github.com/trivago/cluecumber-report-plugin) for Cucumber test reporting

## Setting up authentication

-In [ServiceConstants](/src/test/java/utilities/ServiceConstants.java) class update **USER_NAME** and **OAUTH2_TOKEN** 

## Approach

- Using open source libraries and effective tools for automation testing
- Using Cucumber BDD to easily handle data inside feature file and for easy understanding

## Executing Tests

**Maven Commands:**

- Run the project - `mvn clean test`
- Run tests with cucumber tags - `mvn test -Dcucumber.options="--tags @Create_Gist"`

## Reports

Reports will be generated in `target/generated-reports/index.html` after running the tests using maven commands

![Report](/images/Report_image.png)  