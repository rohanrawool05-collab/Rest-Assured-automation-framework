package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO class for Product Request (Serialization)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    
    private String title;
    private String description;
    private Double price;
    private String category;
    private String brand;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
