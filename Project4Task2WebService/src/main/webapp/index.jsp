<%--Co-Author: Sheldon Shi, I-Wen Chou--%>
<%--AndrewID: lijuns, ichou--%>
<%--Email: lijuns@andrew.cmu.edu, ichou@andrew.cmu.edu--%>
<%--ProjectTask: Project4Task2--%>
<%@ page import="java.util.List" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.Dashboard" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.TopDeviceInfo" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.Latency" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.SystemLog" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%= request.getAttribute("doctype") %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GIFBot Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h1>GIFBot Dashboard</h1>
        </div>
    </div>
    <% if (request.getAttribute("dashboard") == null) {%>
    <div class="row">
        <div class="col">
            <div class="alert alert-danger" role="alert">
                Failed to generate the GIFBot dashboard! Please try again!
            </div>
        </div>
    </div>
    <% } else { %>
    <% Dashboard dashboard = (Dashboard) request.getAttribute("dashboard"); %>
    <div class="row">
        <div class="col-2">
            <table class="table">
                <thead>
                <tr>
                    <th>Top Five Popular Keywords</th>
                </tr>
                </thead>
                <tbody>
                <% for (String word : dashboard.getTopSearchWords()) { %>
                <tr>
                    <td><%= word %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="col-5">
            <table class="table">
                <thead>
                <tr>
                    <th>Top Five Popular GIFs</th>
                </tr>
                </thead>
                <tbody>
                <% for (String gifUrl : dashboard.getTopGIFs()) { %>
                <tr>
                    <td><%= gifUrl %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="col-5">
            <% TopDeviceInfo topDevices = dashboard.getTopDevices(); %>
            <div class="row">
                <div class="col-3">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Top Mobile Manufacture</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < topDevices.getManufacture().size(); i++) { %>
                        <tr>
                            <td><%= topDevices.getManufacture().get(i) %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-2">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Top Mobile Brand</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < topDevices.getBrand().size(); i++) { %>
                        <tr>
                            <td><%= topDevices.getBrand().get(i) %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-4">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Top Mobile Model</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < topDevices.getModel().size(); i++) { %>
                        <tr>
                            <td><%= topDevices.getModel().get(i) %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-2">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Top Android Version</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (int i = 0; i < topDevices.getAndroidVersion().size(); i++) { %>
                        <tr>
                            <td><%= topDevices.getAndroidVersion().get(i) %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>


        </div>
    </div>
    <div class="row">
        <% Latency serviceLatency = dashboard.getServiceLatency(); %>
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th>Service Latency</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><strong>Average:</strong> <%=serviceLatency.getAverage()%> ms</td>
                </tr>
                <tr>
                    <td><strong>Maximum:</strong> <%=serviceLatency.getMaximum()%> ms</td>
                </tr>
                <tr>
                    <td><strong>Minimum:</strong> <%=serviceLatency.getMinimum()%> ms</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th>Recent Service Latency(1-<%=serviceLatency.getRecentRecords().size()%>) (ms)</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < serviceLatency.getRecentRecords().size(); i++) { %>
                <tr>
                    <td><%= serviceLatency.getRecentRecords().get(i) %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <% Latency apiLatency = dashboard.getExternalAPILatency(); %>
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th>External API Latency</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><strong>Average:</strong> <%=apiLatency.getAverage()%> ms</td>
                </tr>
                <tr>
                    <td><strong>Maximum:</strong> <%=apiLatency.getMaximum()%> ms</td>
                </tr>
                <tr>
                    <td><strong>Minimum:</strong> <%=apiLatency.getMinimum()%> ms</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col">
            <table class="table">
                <thead>
                <tr>
                    <th>Recent External API Latency(1-<%=apiLatency.getRecentRecords().size()%>) (ms)</th>
                </tr>
                </thead>
                <tbody>
                <% for (int i = 0; i < apiLatency.getRecentRecords().size(); i++) { %>
                <tr>
                    <td><%= apiLatency.getRecentRecords().get(i) %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <h3>System Log</h3>
        <table class="table" style="width:100%;">
            <% List<SystemLog> logs = dashboard.getLogs(); %>
            <thead>
            <tr>
                <th>#</th>
                <th>Client Request Info</th>
                <th>Client Response Info</th>
                <th>Client Device Info</th>
                <th>3rd Party Request Info</th>
                <th>3rd Party Response Info</th>
            </tr>
            </thead>
            <tbody>
            <% for (int i = 0; i < logs.size(); i++) { %>
            <tr>
                <td style="word-break:break-all;"><%=i + 1%>
                </td>
                <td style="word-break:break-all;"><%= logs.get(i).getClientRequest() %>
                </td>
                <td style="word-break:break-all;"><%= logs.get(i).getClientResponse() %>
                </td>
                <td style="word-break:break-all;"><%= logs.get(i).getClientDevice() %>
                </td>
                <td style="word-break:break-all;"><%= logs.get(i).getExternalAPIRequest() %>
                </td>
                <td style="word-break:break-all;"><%= logs.get(i).getExternalAPIResponse() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
</div>
</body>
</html>
