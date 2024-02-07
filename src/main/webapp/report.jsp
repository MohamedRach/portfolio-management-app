<%@ page import="java.util.ArrayList" %>
<%@page buffer="8192kb" autoFlush="true" %>
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
                <div class="container-xxl flex-grow-1 container-p-y">
                <!-- Content -->
                    <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4" style="width: 100%">
                        <div class="card">
                            <div class="row row-bordered g-0">
                                <div class="col-md-8">
                                    <h5 class="card-header m-0 me-2 pb-3"><%=request.getAttribute("reportName")%></h5>
                                    <div id="second_chart" class="px-2"></div>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Stock Name</th>
                                            <th><%= request.getAttribute("first_period")%></th>
                                            <th><%= request.getAttribute("second_period")%></th>
                                            <th><%= request.getAttribute("third_period")%></th>
                                        </tr>
                                        </thead>
                                        <tbody class="table-border-bottom-0">

                                        <% for (ArrayList<Object> dt: (ArrayList<ArrayList<Object>>) request.getAttribute("data") ){ %>
                                        <tr>
                                            <td><%= dt.get(0)%></td>
                                            <td><%= dt.get(1)%></td>
                                            <td><%= dt.get(2)%></td>
                                            <td><%= dt.get(3)%></td>
                                            <%};%>

                                        </tr>

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content wrapper -->
            </div>
            <!-- / Layout page -->
        </div>



    </div>
    <!-- / Layout wrapper -->

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
    </script>

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="../assets/vendor/js/menu.js"></script>
    <!-- endbuild -->
    <script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>
    <script>
        const data = [<%=request.getAttribute("first_period_return")%>, <%=request.getAttribute("second_period_return")%>, <%=request.getAttribute("third_period_return")%>]
        const categories = ["<%=request.getAttribute("first_period")%>", "<%=request.getAttribute("second_period")%>", "<%=request.getAttribute("third_period")%>"]
        var options = {
            series: [{
                name: 'Returns',
                data: data
            }],
            chart: {
                type: 'bar',
                height: 350
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: '55%',
                    endingShape: 'rounded'
                },
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                show: true,
                width: 2,
                colors: ['transparent']
            },
            xaxis: {
                categories: categories,
            },
            fill: {
                opacity: 1
            },

        };
        const second_chart = document.querySelector('#second_chart')
        var chart = new ApexCharts(second_chart, options);
        chart.render();
    </script>
    <!-- Vendors JS -->


    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>
    <!-- Page JS -->
    <script src="../assets/js/ui-modals.js"></script>
    <!-- Page JS -->
    <!--<script src="../assets/js/dashboards-analytics.js"></script>-->

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>

</body>
</html>
