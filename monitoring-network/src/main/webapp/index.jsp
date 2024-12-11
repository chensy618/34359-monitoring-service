<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/11/24
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Network Traffic Monitoring</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Network Traffic Monitoring</h1>--%>

<%--<h2>Network Links from ONOS REST API</h2>--%>
<%--<table border="1" width="100%" style="text-align: center;">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>Source (Device:Port)</th>--%>
<%--        <th>Destination (Device:Port)</th>--%>
<%--        <th>Type</th>--%>
<%--        <th>State</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <%--%>
<%--        String[][] onosLinksData = (String[][]) request.getAttribute("onosLinksData");--%>
<%--        if (onosLinksData != null && onosLinksData.length > 0) {--%>
<%--            for (String[] row : onosLinksData) {--%>
<%--                out.println("<tr>");--%>
<%--                for (String cell : row) {--%>
<%--                    out.println("<td>" + cell + "</td>");--%>
<%--                }--%>
<%--                out.println("</tr>");--%>
<%--            }--%>
<%--        } else {--%>
<%--            out.println("<tr><td colspan='4'>No data available</td></tr>");--%>
<%--        }--%>
<%--    %>--%>
<%--    </tbody>--%>
<%--</table>--%>


<%--<h2>Port Statistics Overview</h2>--%>
<%--<table border="1" width="100%" style="text-align: center;">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>Device ID</th>--%>
<%--        <th>Port Number</th>--%>
<%--        <th>Packets Received</th>--%>
<%--        <th>Packets Sent</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <%--%>
<%--        String[][] statisticsData = (String[][]) request.getAttribute("statisticsData");--%>
<%--        if (statisticsData != null && statisticsData.length > 0) {--%>
<%--            for (String[] row : statisticsData) {--%>
<%--                out.println("<tr>");--%>
<%--                for (String cell : row) {--%>
<%--                    out.println("<td>" + cell + "</td>");--%>
<%--                }--%>
<%--                out.println("</tr>");--%>
<%--            }--%>
<%--        } else {--%>
<%--            out.println("<tr><td colspan='4'>No statistics data available</td></tr>");--%>
<%--        }--%>
<%--    %>--%>
<%--    </tbody>--%>
<%--</table>--%>

<%--<%--%>
<%--    String errorMessage = (String) request.getAttribute("error");--%>
<%--    if (errorMessage != null) {--%>
<%--%>--%>
<%--<p style="color: red;">Error: <%= errorMessage %></p>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Network Traffic Monitoring</title>
</head>
<body>
<h1>Network Traffic Monitoring</h1>

<!-- 错误信息展示 -->
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<p style="color: red;">Error: <%= error %></p>
<% } %>

<h2>Network Links from ONOS REST API</h2>
<table border="1">
    <thead>
    <tr>
        <th>Source (Device:Port)</th>
        <th>Destination (Device:Port)</th>
        <th>Type</th>
        <th>State</th>
    </tr>
    </thead>
    <tbody>
    <%
        String[][] linksData = (String[][]) request.getAttribute("onosLinksData");
        if (linksData != null && linksData.length > 0) {
            for (String[] row : linksData) {
    %>
    <tr>
        <td><%= row[0] %></td>
        <td><%= row[1] %></td>
        <td><%= row[2] %></td>
        <td><%= row[3] %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">No links data available</td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
