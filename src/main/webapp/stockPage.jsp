<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stock Data</title>
    <link href="./css/stockPage.css" rel="stylesheet">
    <script defer src="js/sideBare.js"></script>
</head>
<body>
<!-- <h1>request.getAttribute("stockName") + " Prices"%></h1> -->
<% ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) request.getAttribute("data");%>
<div class="page">
    <side-bar menu="user"></side-bar>
    <div class="main">
        <div class="stockName">
            <h1><%= request.getAttribute("stockName")%></h1>
            <div>
                <p>140$</p>
                <p>+3%</p>
            </div>
        </div>
        <div class="card">
            <form>
                <select>
                    <option value="today">Today</option>
                    <option>In the last 5 days</option>
                    <option>In the last 6 months</option>
                    <option>Year to date</option>
                </select>
                <input type="submit" value="Submit">
            </form>
            <div class="chart-container">
                <canvas id="line-chart"></canvas>
            </div>
            <div class="stockDetails">
                <table>
                    <thead>
                        <tr>
                            <th>Stock Name</th>
                            <th>Market Cap</th>
                            <th>DIVIDEND YIELD</th>
                            <th>Previouse Close</th>
                            <th>YEAR RANGE</th>
                            <th>DAY RANGE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><%= request.getAttribute("stockName")%></td>
                            <td>163.45B USD</td>
                            <td>1.29%</td>
                            <td>$37.95</td>
                            <td>$24.73 - $40.07</td>
                            <td>$37.98 - $38.90</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>



</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="./js/chart.js"></script>
<stock-chart data='<%= data %>'></stock-chart>
</body>
</html>