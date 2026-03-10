package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.ProductRequest;
import pojo.ProductResponse;
import resources.APIResources;
import testdata.TestDataBuilder;
import utils.RequestSpecBuilder;

import static org.junit.Assert.*;

/**
 * Step Definition class for Product API operations
 */
public class ProductSteps {
    
    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    
    private RequestSpecification request;
    private Response response;
    private Integer createdProductId;
    private ProductRequest productPayload;
    
    @Given("User creates product payload with title {string} description {string} price {double} category {string} and brand {string}")
    public void user_creates_product_payload(String title, String description, Double price, String category, String brand) {
        logger.info("Creating product payload with title: " + title);
        productPayload = TestDataBuilder.createProductPayload(title, description, price, category, brand);
        request = RequestSpecBuilder.getRequestSpecWithBody(productPayload);
    }
    
    @Given("User creates update payload with title {string} and price {double}")
    public void user_creates_update_payload(String title, Double price) {
        logger.info("Creating update payload");
        productPayload = TestDataBuilder.updateProductPayload(title, price);
        request = RequestSpecBuilder.getRequestSpecWithBody(productPayload);
    }
    
    @When("User sends {string} request to {string}")
    public void user_sends_request(String method, String endpoint) {
        logger.info("Sending " + method + " request to " + endpoint);
        
        APIResources apiResource = APIResources.valueOf(endpoint);
        String resourcePath = apiResource.getResource();
        
        if (createdProductId != null && resourcePath.contains("{id}")) {
            resourcePath = resourcePath.replace("{id}", String.valueOf(createdProductId));
        }
        
        switch (method.toUpperCase()) {
            case "POST":
                response = request.when().post(resourcePath);
                break;
            case "GET":
                response = RequestSpecBuilder.getRequestSpec().when().get(resourcePath);
                break;
            case "PUT":
                response = request.when().put(resourcePath);
                break;
            case "PATCH":
                response = request.when().patch(resourcePath);
                break;
            case "DELETE":
                response = RequestSpecBuilder.getRequestSpec().when().delete(resourcePath);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
        
        response.then().log().all();
    }
    
    @When("User sends {string} request to {string} with id {string}")
    public void user_sends_request_with_id(String method, String endpoint, String id) {
        logger.info("Sending " + method + " request to " + endpoint + " with id: " + id);
        
        APIResources apiResource = APIResources.valueOf(endpoint);
        String resourcePath = apiResource.getResource();
        
        if (resourcePath.contains("{id}")) {
            resourcePath = resourcePath.replace("{id}", id);
        }
        
        switch (method.toUpperCase()) {
            case "POST":
                response = request.when().post(resourcePath);
                break;
            case "GET":
                response = RequestSpecBuilder.getRequestSpec().when().get(resourcePath);
                break;
            case "PUT":
                response = request.when().put(resourcePath);
                break;
            case "PATCH":
                response = request.when().patch(resourcePath);
                break;
            case "DELETE":
                response = RequestSpecBuilder.getRequestSpec().when().delete(resourcePath);
                break;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
        
        response.then().log().all();
    }
    
    @Then("Response status code should be {int}")
    public void response_status_code_should_be(int expectedStatusCode) {
        logger.info("Validating status code: " + expectedStatusCode);
        int actualStatusCode = response.getStatusCode();
        assertEquals("Status code mismatch", expectedStatusCode, actualStatusCode);
    }
    
    @Then("Response should contain field {string}")
    public void response_should_contain_field(String field) {
        logger.info("Validating field presence: " + field);
        String responseBody = response.getBody().asString();
        assertTrue("Field " + field + " not found in response", responseBody.contains("\"" + field + "\""));
    }
    
    @Then("Response field {string} should be {string}")
    public void response_field_should_be(String field, String expectedValue) {
        logger.info("Validating field value: " + field + " = " + expectedValue);
        String actualValue = response.jsonPath().getString(field);
        assertEquals("Field value mismatch for " + field, expectedValue, actualValue);
    }
    
    @Then("User saves the product id from response")
    public void user_saves_product_id() {
        ProductResponse productResponse = response.as(ProductResponse.class);
        createdProductId = productResponse.getId();
        logger.info("Saved product ID: " + createdProductId);
        assertNotNull("Product ID should not be null", createdProductId);
    }
    
    @Then("Response time should be less than {int} ms")
    public void response_time_should_be_less_than(int maxTime) {
        long responseTime = response.getTime();
        logger.info("Response time: " + responseTime + " ms");
        assertTrue("Response time exceeded " + maxTime + " ms", responseTime < maxTime);
    }
}
