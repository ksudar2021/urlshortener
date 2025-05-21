URL SHORTENER API


A simple URL shortener service built using Java, Spring Boot and ConcurrentHashMap for in-memory storage

FEATURES:

* Shorten URLs to unique, non-sequential short codes
* redirect short URLs to the Original long URL
* Input validation and custom error Handling
* In-memory storage using ConcurrentHashMap

TECHNOLOGIES:

* Java17
* SpringBoot
* Spring Web
* Junit5, Mockito
* Gradle

TO RUN LOCALLY:

-Prerequisites
* Java17 or higher
* Gradle

BUILD AND RUN:

  # Build the application
        ./gradlew clean build
  # Run the Application
        ./gradlew bootrun

If the Application is up and running successfully with  ==> "Tomcat started on port 8080 (http) with context path '/'"
 1. Shorten URL
    * Endpoint: POST /url/shorten
    * Params: url (query param)
    * Example

POST http://localhost:8080/url/shorten?url=https://www.originenergy.com.au/electricity-gas/plans.html

Sample Response: http://localhost:8080/url/a1B2c3

2. Redirect to original URL
    * Endpoint: GET /url/{code}
    * Example

   GET http://localhost:8080/url/a1B2c3

Redirects to ===> https://www.originenergy.com.au/electricity-gas/plans.html

RUNNING UNIT TESTS:

            ./gradlew test

   * Tests for Controller, Service layers and Exceptions


