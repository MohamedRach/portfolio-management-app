<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
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
<%ArrayList<ArrayList<String>> financialData = (ArrayList<ArrayList<String>>) request.getAttribute("financialData");%>
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
                    <div class="row">
                        <div class="col-lg-8 mb-4 order-0" style="width: 100%;">

                            <div class="d-flex align-items-end row">
                                <div class="col-sm-7" style="margin-top: 40px;">
                                    <div class="card-body">
                                        <h4 class="card-title text-primary"><%= request.getAttribute("portfolio")%></h4>
                                        <div style="display: flex; flex-direction: row; gap: 30px;">
                                            <h3 class="mb-4"><%= request.getAttribute("value")%> $</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-5 text-center text-sm-left">
                                    <div class="card-body pb-0 px-0 px-md-4" style="margin-top: -120px;">
                                        <% if(Objects.equals(session.getAttribute("role"), "user")){%>
                                        <form action="portfolio" method="post">
                                            <input type="hidden" name="delete" value="portfolio">
                                            <input type="hidden" name="porttfolioId" value="<%= request.getParameter("id")%>">
                                            <button type="submit" class="btn btn-lg btn-outline-primary">Delete portfolio</button>
                                        </form>
                                        <%} else {%>
                                            <a href=<%= "/createActivity?id=" + request.getAttribute("user_id")%>><button type="button" class="btn btn-lg btn-outline-primary">Add consultancy</button></a>
                                        <%}%>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4" style="width: 100%">
                        <div class="card">
                            <div class="row row-bordered g-0">
                                <div class="col-md-8">
                                    <h5 class="card-header m-0 me-2 pb-3">Total Revenue</h5>
                                    <div>
                                        <form action="stock" method="post" style="display: flex; flex-direction: row;">
                                            <select name="date" class="form-select" style="width: 150px; margin-left: 30px; margin-right: 30px">
                                                <option value="weekly">Last 5 days</option>
                                                <option value="monthly">Last Month</option>
                                            </select>
                                            <input type="hidden" name="stock" value="<%= request.getAttribute("stockName")%>">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>

                                    </div>
                                    <div id="totalRevenueChart" class="px-2"></div>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Stock Name</th>
                                            <th>Total Revenue</th>
                                            <th>Total Cash</th>
                                            <th>Total Debt</th>
                                            <th>Profit Margins</th>
                                            <% if(Objects.equals(session.getAttribute("role"), "user")){%>
                                            <th>Actions</th>
                                            <%}%>
                                        </tr>
                                        </thead>
                                        <tbody class="table-border-bottom-0">

                                        <% for (ArrayList<String> data: financialData){ %>
                                        <tr>
                                            <td><%= data.get(4)%></td>
                                            <td><%= data.get(0)%></td>
                                            <td><%= data.get(1)%></td>
                                            <td><%= data.get(2)%></td>
                                            <td><%= data.get(3)%></td>
                                            <% if(Objects.equals(session.getAttribute("role"), "user")){%>
                                            <td>
                                                <div class="dropdown">
                                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                        <i class="bx bx-dots-vertical-rounded"></i>
                                                    </button>
                                                    <div class="dropdown-menu">
                                                        <form action="portfolio" method="post">
                                                            <input type="hidden" name="delete" value="stock">
                                                            <input type="hidden" name="stockId" value="<%= data.get(5)%>">
                                                            <button class="btn btn-sm btn-outline-primary" type="submit">
                                                                <i class="bx bx-trash me-1"></i> Delete
                                                            </button>
                                                        </form>

                                                    </div>
                                                </div>
                                            </td>

                                            <%}};%>


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

    <!-- Vendors JS -->
    <script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>
    <!-- Page JS -->
    <script src="../assets/js/ui-modals.js"></script>
    <!-- Page JS -->
    <!--<script src="../assets/js/dashboards-analytics.js"></script>-->
    <script src="../assets/js/portfolioPage.js"></script>
    <stock-chart data="<%= request.getAttribute("priceData")%>" datesData=" <%= request.getAttribute("stockData")%>"></stock-chart>>
    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>

</body>
</html>
