package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO class for Device Request (Serialization)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceRequest {
    
    private String name;
    private DeviceData data;
    
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
}
