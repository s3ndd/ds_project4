/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for device Information.
 *
 */

package edu.cmu.andrew.project4task2webservice.model;

public class DeviceInfo {
    //manufacturer of the device
    String manufacture;
    //brand of the device
    String brand;
    //model of the device
    String model;
    //androidVersion of the device
    String androidVersion;

    //constructor of DeviceInfo
    public DeviceInfo() {
    }

    //constructor of DeviceInfo with manufacture, brand, model,
    //and androidVersion of the device as input
    public DeviceInfo(String manufacture, String brand, String model, String androidVersion) {
        this.manufacture = manufacture;
        this.brand = brand;
        this.model = model;
        this.androidVersion = androidVersion;
    }

    //get manufacturer of the device
    public String getManufacture() {
        return manufacture;
    }

    //setmanufacturer of the device
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    //get brand of the device
    public String getBrand() {
        return brand;
    }

    //set brand of the device
    public void setBrand(String brand) {
        this.brand = brand;
    }

    //get model of the device
    public String getModel() {
        return model;
    }

    //set model of the device
    public void setModel(String model) {
        this.model = model;
    }

    //get androidVersion of the device
    public String getAndroidVersion() {
        return androidVersion;
    }

    //set androidVersion of the device
    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    //override toString for print out
    @Override
    public String toString() {
        return "DeviceInfo{" +
                "manufacture='" + manufacture + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", androidVersion='" + androidVersion + '\'' +
                '}';
    }
}
