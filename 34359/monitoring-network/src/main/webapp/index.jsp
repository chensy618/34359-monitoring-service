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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Network Traffic Monitoring</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
            text-align: center;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Network Traffic Monitoring</h1>

<!-- 错误信息展示 -->
<c:if test="${not empty error}">
    <p style="color: red; text-align: center;">Error: ${error}</p>
</c:if>

<!-- Network Links Section -->
<h2 style="text-align: center;">Network Links from ONOS REST API</h2>
<table>
    <thead>
    <tr>
        <th>Source</th>
        <th>Destination</th>
        <th>Type</th>
        <th>State</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="link" items="${onosLinksData}">
        <tr>
            <td>${link.source}</td>
            <td>${link.destination}</td>
            <td>${link.type}</td>
            <td>${link.state}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty onosLinksData}">
        <tr>
            <td colspan="4">No links data available</td>
        </tr>
    </c:if>
    </tbody>
</table>

<!-- Hosts Overview Section -->
<h2 style="text-align: center;">Hosts Overview</h2>
<c:if test="${not empty onosHostsData}">
    <table border="1">
        <tr>
            <th>MAC Address</th>
            <th>IP Addresses</th>
            <th>Locations</th>
        </tr>
        <c:forEach var="host" items="${onosHostsData}">
            <tr>
                <td>${host.mac}</td>
                <td>${host.ipAddresses}</td>
                <td>${host.locations}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty onosHostsData}">
    <p>No host data available.</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red;">Error: ${error}</p>
</c:if>


<table border="1">
    <tr>
        <th>Link</th>
        <th>Actual Bandwidth (bps)</th>
        <th>Available Bandwidth (bps)</th>
        <th>Utilization (%)</th>
    </tr>
    <c:forEach var="entry" items="${linkBandwidthData}">
        <tr>
            <td>${entry.key}</td>
            <td>${entry.value.actualBandwidth}</td>
            <td>${entry.value.availableBandwidth}</td>
            <td>${entry.value.utilization}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>

