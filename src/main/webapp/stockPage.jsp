<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="./css/stockPage.css" rel="stylesheet">
    <script defer src="js/sideBare.js"></script>
</head>
<body>
<!-- <h1>request.getAttribute("stockName") + " Prices"%></h1> -->
<% ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) request.getAttribute("data");%>
<div class="page">
    <side-bar menu="user"></side-bar>
    <div class="main">
        <h1 class="stockName"><%= request.getAttribute("stockName") + " Prices"%></h1>
        <div class="stockData">
            <div class="chart-container">
                <canvas id="line-chart"></canvas>
            </div>
            <div class="stockDetails">
                <table>
                    <tr>
                        <td>Market Cap</td>
                        <td>163.45B USD</td>
                    </tr>
                    <tr>
                        <td>DIVIDEND YIELD</td>
                        <td>1.29%</td>
                    </tr>
                    <tr>
                        <td>Previouse Close</td>
                        <td>$37.95</td>
                    </tr>
                    <tr>
                        <td>YEAR RANGE</td>
                        <td>$24.73 - $40.07</td>
                    </tr>
                    <tr>
                        <td>DAY RANGE</td>
                        <td>$37.98 - $38.90</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="discover">
            <h2 class="title">Discover More</h2>
            <p class="desc">You may be interested In</p>
            <div class="stocks">
                <div class="stockCard">
                    <div class="stockTitle">
                        <h3>Apple</h3>
                        <p>Apple Inc</p>
                    </div>
                    <div class="StockPrice">
                        <h3>570</h3>
                        <p>+6,54%</p>
                    </div>
                </div>
                <div class="stockCard">
                    <div class="stockTitle">
                        <h3>Apple</h3>
                        <p>Apple Inc</p>
                    </div>
                    <div class="StockPrice">
                        <h3>570</h3>
                        <p>+6,54%</p>
                    </div>
                </div>
                <div class="stockCard">
                    <div class="stockTitle">
                        <h3>Apple</h3>
                        <p>Apple Inc</p>
                    </div>
                    <div class="StockPrice">
                        <h3>570</h3>
                        <p>+6,54%</p>
                    </div>
                </div>
                <div class="stockCard">
                    <div class="stockTitle">
                        <h3>Apple</h3>
                        <p>Apple Inc</p>
                    </div>
                    <div class="StockPrice">
                        <h3>570</h3>
                        <p>+6,54%</p>
                    </div>
                </div>
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