<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="../assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Stock Prices</title>

    <meta name="description" content="" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="../assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="../assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="../assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="../assets/css/demo.css" />

    <!-- Helpers -->
    <!--<script src="../assets/vendor/js/helpers.js"></script>-->

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="../assets/js/config.js"></script>
</head>

<body>
<% ArrayList<ArrayList<Object>> tableData = (ArrayList<ArrayList<Object>>) request.getAttribute("tableData");
    ArrayList<String> stockName = (ArrayList<String>) request.getAttribute("graphData1");
    ArrayList<Float> percentage = (ArrayList<Float>) request.getAttribute("graphData2");
    %>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <%@ include file= "menu.jsp"%>
        <!-- / Menu -->
        <!-- Layout container -->
        <div class="layout-page">
            <!-- Navbar -->
            <%@ include file= "nav.html"%>
            <!-- / Navbar -->
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->

                <div class="container-xxl flex-grow-1 container-p-y">
                    <div class="row" style="margin-left: 10px;display: grid; grid-template-columns: repeat(3, 300px); column-gap: 25px;">
                        <div class="card">
                            <div>
                                <h5 class="card-header m-0 me-2 pb-3">Total Stocks</h5>
                                <h4 style="margin-left: 18px"><%= request.getAttribute("total_stocks")%></h4>
                            </div>
                        </div>
                        <div class="card">
                            <div>
                                <h5 class="card-header m-0 me-2 pb-3">Total Portfolios</h5>
                                <h4 style="margin-left: 18px"><%= request.getAttribute("total_portfolios")%></h4>
                            </div>
                        </div>
                        <div class="card">
                            <div>
                                <h5 class="card-header m-0 me-2 pb-3">Total Value</h5>
                                <h4 style="margin-left: 18px"><%= request.getAttribute("total_value")%> $</h4>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top:20px;margin-left: 10px;display: grid; grid-template-columns: repeat(2, 465px); column-gap: 25px;">
                        <div class="card">
                            <div>
                                <h5 class="card-header m-0 me-2 pb-3">Your Stocks today</h5>
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>Stocks</th>
                                        <th>Gain</th>
                                    </tr>
                                    </thead>
                                    <tbody class="table-border-bottom-0">
                                    <%for (ArrayList<Object> data: tableData){%>
                                    <tr>
                                        <td><%= data.get(0)%></td>
                                        <td><%= String.format("%.3f", data.get(1))%> %</td>
                                    </tr>
                                    <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card">
                            <div>
                                <h5 class="card-header m-0 me-2 pb-3">Total Revenue</h5>
                                <div id="chart" class="px-2"></div>
                            </div>
                        </div>
                    </div>
                </div><!-- Content wrapper -->
            </div><!-- / Layout page -->
        </div>

    </div>
    <!-- / Layout wrapper -->
    <!-- Vendors JS -->
    <script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>
    <script>
        const menus = document.querySelectorAll(".menu-toggle");
        console.log(menus)
        menus.forEach((menu) => (
            menu.addEventListener("click", (e) => {
                e.preventDefault()
                if (menu.parentElement.classList.contains('open')) {
                    // The 'open' class is present in the element's class list
                    menu.parentElement.classList.remove("open")
                } else {
                    // The 'open' class is not present in the element's class list
                    menu.parentElement.classList.add("open")
                }
            })
        ))
        const inputString = "<%= stockName%>"
        //console.log(JSON.parse("[" + inputString.substring(1, inputString.length - 1) + "]"))
        var options = {
            series: <%= percentage%>,
            chart: {
                width: 380,
                type: 'pie',
            },
            labels: inputString.replace(/\[|\]/g, '').split(', '),
            responsive: [{
                breakpoint: 480,
                options: {
                    chart: {
                        width: 200
                    },
                    legend: {
                        position: 'bottom'
                    }
                }
            }]
        };

        var chart = new ApexCharts(document.querySelector("#chart"), options);
        chart.render();
    </script>
    <script src="../assets/js/searchInput.js"></script>

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="../assets/vendor/js/menu.js"></script>
    <!-- endbuild -->



    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>


    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
</div>
</body>
</html>
