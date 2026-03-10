package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO class for Device Data
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceData {
    
    private String year;
    private String price;
    private String cpuModel;
    private String hardDiskSize;
    
    public String getYear() {
        return year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getCpuModel() {
        return cpuModel;
    }
    
    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }
    
    public String getHardDiskSize() {
        return hardDiskSize;
    }
    
    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }
}
