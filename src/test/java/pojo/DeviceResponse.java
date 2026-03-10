package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO class for Device Response (Deserialization)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceResponse {
    
    private String id;
    private String name;
    private DeviceData data;
    private String createdAt;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public DeviceData getData() {
        return data;
    }
    
    public void setData(DeviceData data) {
        this.data = data;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
