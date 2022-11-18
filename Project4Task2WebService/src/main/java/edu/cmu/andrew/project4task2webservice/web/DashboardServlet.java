package edu.cmu.andrew.project4task2webservice.web;

import java.io.IOException;
import java.util.List;

import edu.cmu.andrew.project4task2webservice.service.DashboardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "DashboardServlet",
        urlPatterns = {"/getGIFs"})
public class DashboardServlet extends HttpServlet {

    DashboardService dbs = null;  // The "business model" for this app

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        dbs = new DashboardService();
    }

    // This servlet will reply to HTTP GET requests via this doGet method
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
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        //The most popular search keyword
        List<String> popularKeyword = dbs.getPopularKeyword();
        request.setAttribute("popularKeyword", popularKeyword);

        //Top 5 GIFs Search Result
        List<String> popularGIFsURL = dbs.getPopularGIFsURL();
        request.setAttribute("popularGIFsURL", popularGIFsURL);

        //GIFs Service Response Time
        //average value
        String avgResponse = dbs.getAvgResponse();
        request.setAttribute("avgResponse", avgResponse);
        //a table of latest 100 history data


        //Frequent User Devices
        List<String> commonDevice = dbs.getCommonDevice();
        request.setAttribute("commonDevice", commonDevice);

        String nextView = "index.jsp";
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
}
