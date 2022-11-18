package edu.cmu.andrew.project4task2webservice.service;

import java.util.ArrayList;
import java.util.List;

public class DashboardService {
    public List<String> getPopularKeyword() {
        //add top five popular words, query from db
        List<String> popularKeyword = new ArrayList<>();
        popularKeyword.add("PopularKeyword1");
        popularKeyword.add("PopularKeyword2");
        popularKeyword.add("PopularKeyword3");
        popularKeyword.add("PopularKeyword4");
        popularKeyword.add("PopularKeyword5");
        return popularKeyword;
    }

    public List<String> getPopularGIFsURL() {
        List<String> popularGIFsURL = new ArrayList<>();
        popularGIFsURL.add("https://live.staticflickr.com/1761/43293863672_d84227a8d5_z.jpg");
        popularGIFsURL.add("https://live.staticflickr.com/1261/5110834170_0797f39278_z.jpg");
        popularGIFsURL.add("https://live.staticflickr.com/3397/3431364120_2e9f78ca60_z.jpg");
        popularGIFsURL.add("https://live.staticflickr.com/8163/7469820870_aa60b7ff71_z.jpg");
        popularGIFsURL.add("https://live.staticflickr.com/1189/561670551_efa5f9ae72_z.jpg");
        return popularGIFsURL;
    }

    public String getAvgResponse() {
        int avg = 100;
        String avgResponse = avg + "ms";
        return avgResponse;
    }

    public List<String> getCommonDevice() {
        List<String> commonDevice = new ArrayList<>();
        commonDevice.add("iphone");
        commonDevice.add("samsung");
        commonDevice.add("panasonic");
        return commonDevice;
    }
}