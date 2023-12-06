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
            <div class="add">
                <h1>tesla</h1>
                <form>

                    <input type="submit" value="Add to portfolio">
                </form>
            </div>

            <div class="price">
                <p>140$</p>
                <p>+3%</p>
            </div>
        </div>
        <div class="card">
            <div class="from">
                <form>
                    <select>
                        <option value="today">Today</option>
                        <option>In the last 5 days</option>
                        <option>In the last 6 months</option>
                        <option>Year to date</option>
                    </select>
                    <input type="submit" value="Submit">
                </form>
            </div>
            <div class="chart-container">
                <canvas id="line-chart"></canvas>
            </div>
            <div class="stockDetails">
                <table class="content-table">
                    <thead>
                        <tr>
                            <th class="firstColumn">Stock Name</th>
                            <th>Market Cap</th>
                            <th>DIVIDEND YIELD</th>
                            <th>Previouse Close</th>
                            <th>YEAR RANGE</th>
                            <th>DAY RANGE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="firstColumn"> <%= request.getAttribute("stockName")%></td>
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
<stock-chart data="[[2023-12-05 19:20:00, 238.9450], [2023-12-05 19:05:00, 242.3400], [2023-12-05 15:50:00, 239.0500], [2023-12-05 12:10:00, 238.4580], [2023-12-05 16:20:00, 239.2210], [2023-12-05 15:35:00, 241.0050], [2023-12-05 12:50:00, 238.9500], [2023-12-05 16:40:00, 239.9250], [2023-12-05 17:10:00, 245.0050], [2023-12-05 12:15:00, 238.9600], [2023-12-05 14:10:00, 239.1700], [2023-12-05 15:15:00, 238.9280], [2023-12-05 17:30:00, 239.3100], [2023-12-05 14:20:00, 239.5500], [2023-12-05 13:15:00, 239.4000], [2023-12-05 15:00:00, 239.3300], [2023-12-05 12:35:00, 239.0650], [2023-12-05 17:25:00, 239.8850], [2023-12-05 14:40:00, 243.4700], [2023-12-05 18:30:00, 239.2990], [2023-12-05 15:45:00, 239.0650], [2023-12-05 18:55:00, 239.1300], [2023-12-05 19:10:00, 239.7900], [2023-12-05 19:35:00, 238.7600], [2023-12-05 11:50:00, 241.2400], [2023-12-05 17:55:00, 239.0300], [2023-12-05 14:35:00, 239.0200], [2023-12-05 15:05:00, 238.3600], [2023-12-05 14:55:00, 240.5800], [2023-12-05 19:15:00, 241.2900], [2023-12-05 19:30:00, 238.8900], [2023-12-05 17:00:00, 239.0000], [2023-12-05 16:30:00, 243.2540], [2023-12-05 12:25:00, 239.9700], [2023-12-05 14:00:00, 239.2100], [2023-12-05 15:25:00, 239.6250], [2023-12-05 16:50:00, 242.9900], [2023-12-05 19:45:00, 239.3000], [2023-12-05 12:45:00, 239.1300], [2023-12-05 13:50:00, 239.0500], [2023-12-05 14:30:00, 239.3000], [2023-12-05 16:15:00, 238.8500], [2023-12-05 13:05:00, 239.8730], [2023-12-05 13:45:00, 239.7500], [2023-12-05 19:00:00, 239.6400], [2023-12-05 17:15:00, 244.1300], [2023-12-05 14:50:00, 238.8500], [2023-12-05 15:55:00, 238.1500], [2023-12-05 13:00:00, 242.8800], [2023-12-05 13:25:00, 239.6150]]"></stock-chart>
</body>
</html>