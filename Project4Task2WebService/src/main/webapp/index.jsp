<%@ page import="java.util.List" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.Latency" %>
<%@ page import="edu.cmu.andrew.project4task2webservice.model.DeviceInfo" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interesting Picture</title>
    </head>
    <body>
        <h1>GIFBot Dashboard</h1>
<%--        Interesting analysis 1--%>
        <h2>Top Twenty Popular Keywords</h2>
        <%--list most popular searching keywords, top 20--%>
        <h3>
            <% List<String> popularKeyword = (List<String>)request.getAttribute("popularKeyword");%>
            <% for(int i = 0; i< popularKeyword.size(); i++){ %>
            <%System.out.println((i+1) + ". " + popularKeyword.get(i)); %><br/>
            <%}%>
        </h3>
<%--        Interesting analysis 2--%>
        <h2>Top Twenty GIFs Search Result</h2>
        <%--list most popular return GIF results, top 20--%>
        <h3>
            <% List<String> popularGIFsURL = (List<String>)request.getAttribute("popularGIFsURL");%>
            <% for(int i = 0; i< popularGIFsURL.size(); i++){ %>
            <%System.out.println((i+1) + ". "); %><img src="<%=popularGIFsURL.get(i)%>" height="200">
            <%}%>
        </h3>
<%--        Interesting analysis 3--%>
        <h2>GIFs Service Response Time</h2>
        <% Latency serviceLatency = (Latency) request.getAttribute("serviceLatency");%>
        <%--Avg., Max., and Min. of service latency--%>
        <h3>Service Latency</h3>
            <p>Average Response Time: <%= serviceLatency.getAverage()%></p>
            <p>maximum Response Time: <%= serviceLatency.getMaximum()%></p>
            <p>minimum Response Time: <%= serviceLatency.getMinimum()%></p>
        <h3>Service Latency Log</h3>
             <%--list latest data of service latency--%>
            <table>
                <% List<Long> ServiceRecentRecords = serviceLatency.getRecentRecords(); %>
                <tr>Latest 100 History Data</tr>
                    <th>ID</th>
                    <th>Data</th>
                </tr>
                <tr>
                    <% for(int i = 0; i< ServiceRecentRecords.size(); i++){ %>
                    <td><%System.out.println(i+1);%></td>
                    <td><%= ServiceRecentRecords.get(i)%></td>
                    <%}%>
                </tr>
            </table>
        <% Latency externalAPILatency = (Latency) request.getAttribute("externalAPILatency");%>
        <%--Avg., Max., and Min. of API latency--%>
        <h3>API Latency</h3>
            <p>Average Response Time: <%= externalAPILatency.getAverage()%></p>
            <p>maximum Response Time: <%= externalAPILatency.getMaximum()%></p>
            <p>minimum Response Time: <%= externalAPILatency.getMinimum()%></p>
        <h3>API Latency Log</h3>
            <%--list latest data of API latency--%>
            <table>
                <% List<Long> APIRecentRecords = externalAPILatency.getRecentRecords(); %>
                <tr>Latest 100 History Data</tr>
                    <th>ID</th>
                    <th>Data</th>
                </tr>
                <tr>
                    <% for(int i = 0; i< APIRecentRecords.size(); i++){ %>
                    <td><%System.out.println(i+1);%></td>
                    <td><%= APIRecentRecords.get(i)%></td>
                    <%}%>
                </tr>
            </table>
<%--        Interesting analysis 4--%>
        <h2>Frequent User Devices</h2>
        <%--list most popular user devices, top 10--%>
        <h3>
            <% List<DeviceInfo> top10Devices = (List<DeviceInfo>)request.getAttribute("top10Devices");%>
            <% for(int i = 0; i< top10Devices.size(); i++){ %>
            <%System.out.println((i+1) + ". " + top10Devices.get(i).getBrand() + ", " + top10Devices.get(i).getModel());%><br/>
            <%}%>
        </h3>
<%--        Full Log Information--%>
        <h2>System Log...</h2>
        <% List<String> logs = (List<String>) request.getAttribute("logs"); %>
        <table>
            <th>ID</th>
            <th>Data</th>
            </tr>
            <tr>
                <% for(int i = 0; i< logs.size(); i++){ %>
                <td><%System.out.println(i+1);%></td>
                <td><%= logs.get(i)%></td>
                <%}%>
            </tr>
        </table>
    </body>
</html>
