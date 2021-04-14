# SumREST ➕

Spring Boot Server 


## Overview 🔍

The application propose is to make sums and store it in a H2 database.

## How to ⁉

Please check the path openapi/openapi.yaml to see how to work with the REST service.

* Routes

  * Numbers Routes
 
  <img src="/templates/routes.png">

    * Get
    
    <img src="/templates/getRoute.png">
    
    * Post
    
    <img src="/templates/postRoute.png">

# Unitary tests.

The JUnit test file is in "src/test/java/io/swagger/service/NumberServiceTest.java". Execute the file as JUnit Test to get the service tests result.
To get the Jacoco test, run the following command in the command line: "mvn test jacoco:report". Then you will be able to access it in "target/site/jacoco" directory.
