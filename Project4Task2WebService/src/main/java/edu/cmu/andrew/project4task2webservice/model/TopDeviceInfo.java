/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 */

package edu.cmu.andrew.project4task2webservice.model;


import java.util.ArrayList;
import java.util.List;

public class TopDeviceInfo {
    // the manufacture list
    private List<String> manufacture;

    // the brand list
    private List<String> brand;

    // the model list
    private List<String> model;

    // the android version list
    private List<String> androidVersion;

    // create a TopDeviceInfo with empty lists
    public TopDeviceInfo() {
        this.manufacture = new ArrayList<>();
        this.brand = new ArrayList<>();
        this.model = new ArrayList<>();
        this.androidVersion = new ArrayList<>();
    }

    // a getter to return the manufacture list
    public List<String> getManufacture() {
        return manufacture;
    }

    // a getter to return the brand list
    public List<String> getBrand() {
        return brand;
    }

    // a getter to return the manufacture list
    public List<String> getModel() {
        return model;
    }

    // a getter to return the androidVersion list
    public List<String> getAndroidVersion() {
        return androidVersion;
    }

}
