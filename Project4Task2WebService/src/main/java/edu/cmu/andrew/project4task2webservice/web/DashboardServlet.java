/**
 * Co-Author: Sheldon Shi, I-Wen Chou
 * AndrewID: lijuns, ichou
 * Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu
 * ProjectTask: Project4Task2
 * <p>
 * This is a class for dashboard servlet.
 */

package edu.cmu.andrew.project4task2webservice.web;

import edu.cmu.andrew.project4task2webservice.service.DashboardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    private final static String DASHBOARD_PAGE = "index.jsp";

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
        request.setAttribute("doctype", getDocType(request.getHeader("User-Agent")));
        request.setAttribute("dashboard", dbs.generateDashboard());

        request.getRequestDispatcher(DASHBOARD_PAGE).forward(request, response);
    }

    // Reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture/blob/master/InterestingPictureServlet.java
    private static String getDocType(String userAgent) {
        if (userAgent != null && ((userAgent.contains("Android")) || (userAgent.contains("iPhone")))) {
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            return "<!DOCTYPE html PUBLIC \"-// WAPFORUM// DTD XHTML Mobile 1.2// EN\" \"http://www" +
                    ".openmobilealliance" +
                    ".org/tech/DTD/xhtml-mobile12.dtd\">";
        } else {
            return "<!DOCTYPE HTML PUBLIC \"-// W3C// DTD HTML 4.01 Transitional// EN\" \"http://www.w3" +
                    ".org/TR/html4/loose.dtd\">";
        }
    }
}
