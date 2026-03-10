package testdata;

import pojo.ProductRequest;

/**
 * Test Data Builder class to create test data dynamically
 */
public class TestDataBuilder {
    
    public static ProductRequest createProductPayload(String title, String description, Double price, String category, String brand) {
        ProductRequest request = new ProductRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setPrice(price);
        request.setCategory(category);
        request.setBrand(brand);
        return request;
    }
    
    public static ProductRequest updateProductPayload(String title, Double price) {
        ProductRequest request = new ProductRequest();
        request.setTitle(title);
        request.setPrice(price);
        return request;
    }
}
