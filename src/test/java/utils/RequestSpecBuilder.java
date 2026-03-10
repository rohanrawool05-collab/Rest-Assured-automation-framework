package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

/**
 * Utility class to build reusable request specifications
 */
public class RequestSpecBuilder {
    
    private static final Logger logger = LogManager.getLogger(RequestSpecBuilder.class);
    
    public static RequestSpecification getRequestSpec() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        
        logger.info("Building request specification with base URL: " + ConfigReader.getBaseUrl());
        
        return given()
                .filter(new RequestResponseLogger())
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .log().all();
    }
    
    public static RequestSpecification getRequestSpecWithBody(Object body) {
        logger.info("Building request specification with body");
        return getRequestSpec().body(body);
    }
}
