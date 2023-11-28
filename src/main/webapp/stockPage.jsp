<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= request.getAttribute("stockName") + " Prices"%>
<% ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) request.getAttribute("data");%>
</h1>
<canvas id="line-chart"></canvas>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="./js/chart.js"></script>
<stock-chart data='<%= data %>'></stock-chart>
</body>
</html>