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
<% ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) request.getAttribute("data");

%>
</h1>
<canvas id="line-chart"></canvas>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
    var arrayOfArraysInJavaScript = [];

    <%
      for (ArrayList<String> innerArray : data) {
        // Convert the first element of the inner array to a string
        String firstElementAsString = innerArray.get(0);
        float secondElementAsString = Float.parseFloat(innerArray.get(1));
    %>
    arrayOfArraysInJavaScript.push({
        x: '<%= firstElementAsString %>',
        y: <%= secondElementAsString%>
    });
    var sortedArray = arrayOfArraysInJavaScript.sort(function (a,b) {
        return new Date(b.x) - new Date(a.x)
    })
    <%
      }
    %>
    console.log(sortedArray)



    const ctx = document.querySelector("#line-chart").getContext('2d');
    const config = {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                data: sortedArray,
                label: "Stock Prices",
                borderColor: "#3e95cd",
                fill: false
            }]
        },
        options: {
            scales: {
                xAxes: [{
                    type: 'time',
                    distribution: 'linear',
                }],
                title: {
                    display: false,
                }
            }
        }
    };
    new Chart(ctx, config);


</script>
</body>
</html>