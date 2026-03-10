package resources;

/**
 * Enum class to centralize all API resource endpoints
 */
public enum APIResources {
    
    GET_ALL_PRODUCTS("/products"),
    GET_PRODUCT_BY_ID("/products/{id}"),
    CREATE_PRODUCT("/products/add"),
    UPDATE_PRODUCT("/products/{id}"),
    DELETE_PRODUCT("/products/{id}"),
    SEARCH_PRODUCTS("/products/search");
    
    private String resource;
    
    APIResources(String resource) {
        this.resource = resource;
    }
    
    public String getResource() {
        return resource;
    }
}
