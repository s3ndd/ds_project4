package edu.cmu.andrew.project4task2webservice.model;


import java.util.ArrayList;
import java.util.List;

public class TopDeviceInfo {
    private List<String> manufacture;
    private List<String> brand;
    private List<String> model;
    private List<String> androidVersion;

    public TopDeviceInfo() {
        this.manufacture = new ArrayList<>();
        this.brand = new ArrayList<>();
        this.model = new ArrayList<>();
        this.androidVersion = new ArrayList<>();
    }

    public List<String> getManufacture() {
        return manufacture;
    }

    public void setManufacture(List<String> manufacture) {
        this.manufacture = manufacture;
    }

    public List<String> getBrand() {
        return brand;
    }

    public void setBrand(List<String> brand) {
        this.brand = brand;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public List<String> getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(List<String> androidVersion) {
        this.androidVersion = androidVersion;
    }

}
