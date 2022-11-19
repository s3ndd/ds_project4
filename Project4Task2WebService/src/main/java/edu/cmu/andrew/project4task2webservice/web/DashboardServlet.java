/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 *
 * This is a class for dashboard servlet.
 *
 */

package edu.cmu.andrew.project4task2webservice.web;

import java.io.IOException;
import java.util.List;

import edu.cmu.andrew.project4task2webservice.model.DeviceInfo;
import edu.cmu.andrew.project4task2webservice.model.Latency;
import edu.cmu.andrew.project4task2webservice.service.DashboardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DashboardServlet",
        urlPatterns = {"/getGIFs"})
public class DashboardServlet extends HttpServlet {

    //business model for dashboard
    DashboardService dbs = null;

    //initiate this servlet by instantiating the model
    @Override
    public void init() {
        dbs = new DashboardService();
    }

    //servlet will reply to HTTP GET requests
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            //latest XHTML Mobile doctype
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            //device other than mobile
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        //The most popular search keyword
        List<String> popularKeyword = dbs.getPopularKeyword();
        //set attribute for view
        request.setAttribute("popularKeyword", popularKeyword);

        //Top 5 GIFs Search Result
        List<String> popularGIFsURL = dbs.getPopularGIFsURL();
        //set attribute for view
        request.setAttribute("popularGIFsURL", popularGIFsURL);

        //GIFs System Service Latencies return object with average, maximum, minimum value, and a table of latest 100 history data
        Latency serviceLatency = dbs.getServiceLatency();
        //set attribute for view
        request.setAttribute("serviceLatency", serviceLatency);
        //GIFs API Latencies return object with average, maximum, minimum value, and a table of latest 100 history data
        Latency externalAPILatency = dbs.getExternalAPILatency();
        //set attribute for view
        request.setAttribute("externalAPILatency", externalAPILatency);

        //Frequent User Devices
        List<DeviceInfo> top10Devices = dbs.getTop10Devices();
        //set attribute for view
        request.setAttribute("top10Devices", top10Devices);

        //System log
        List<String> logs = dbs.getLogs();
        //set attribute for view
        request.setAttribute("logs", logs);

        //set front-end document
        String nextView = "index.jsp";
        //get resource for request
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        //forward response
        view.forward(request, response);
    }
}
