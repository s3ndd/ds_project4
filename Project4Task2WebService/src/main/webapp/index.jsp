<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interesting Picture</title>
    </head>
    <body>
        <h1>GIFBot Dashboard</h1>
        <h2>Top Five Popular Keywords</h2>
        <h3>
            <% List<String> popularKeyword = (List<String>)request.getAttribute("popularKeyword");%>
            <% for(int i = 0; i< popularKeyword.size(); i++){ %>
            <%System.out.println((i+1) + ". " + popularKeyword.get(i)); %><br/>
            <%}%>
        </h3>
        <h2>Top Five GIFs Search Result</h2>
        <h3>
            <% List<String> popularGIFsURL = (List<String>)request.getAttribute("popularGIFsURL");%>
            <% for(int i = 0; i< popularGIFsURL.size(); i++){ %>
            <%System.out.println((i+1) + ". "); %><img src="<%=popularGIFsURL.get(i)%>" height="200">
            <%}%>
        </h3>
        <h2>GIFs Service Response Time</h2>
        <h3>Average Response Time: <%= request.getAttribute("avgResponse")%></h3>
        <h3>Response Time Log</h3>
        <table>
            <tr>Latest 100 History Data</tr>
            <th>ID</th>
            </tr>
            <tr>
                <td>1</td>
            </tr>
        </table>
        <h2>Frequent User Devices</h2>
        <h3>
            <% List<String> commonDevice = (List<String>)request.getAttribute("commonDevice");%>
            <% for(int i = 0; i< commonDevice.size(); i++){ %>
            <%System.out.println((i+1) + ". " + commonDevice.get(i)); %><br/>
            <%}%>
        </h3>
        <h2>System Log...</h2>
    </body>
</html>
