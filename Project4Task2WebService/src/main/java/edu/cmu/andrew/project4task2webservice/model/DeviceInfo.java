package edu.cmu.andrew.project4task2webservice.model;


public class DeviceInfo {
    String manufacture;
    String brand;
    String model;
    String androidVersion;

    public DeviceInfo() {
    }

    public DeviceInfo(String manufacture, String brand, String model, String androidVersion) {
        this.manufacture = manufacture;
        this.brand = brand;
        this.model = model;
        this.androidVersion = androidVersion;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

}
