<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.projets3.bean.portfolioBean" %>
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
<% ArrayList<Float> quoteData = (ArrayList<Float>) request.getAttribute("quoteData");
ArrayList<ArrayList<String>> financialData = (ArrayList<ArrayList<String>>) request.getAttribute("financialData");%>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <%@ include file= "menu.html"%>
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
                                            <h4 class="card-title text-primary"><%= request.getAttribute("stockName")%> Inc</h4>
                                            <div style="display: flex; flex-direction: row; gap: 30px;">
                                                <h3 class="mb-4"><%= quoteData.get(0)%> $</h3>
                                                <div style="width: 90px; height: 30px; background:
                                                        <% if (quoteData.get(1) >= 0){%>
                                                        <%= "#E6F4EA"%>
                                                        <%} else {%>
                                                        <%= "#FCE8E6"%>
                                                        <%}%>; color:
                                                    <% if (quoteData.get(1) >= 0){%>
                                                    <%= "#277333"%>
                                                    <%} else {%>
                                                    <%= "#C5221F"%>
                                                    <%}%>
                                                        ; text-align: center; padding-top: 4px; border-radius: 5px;"><% if (quoteData.get(1) >= 0){%>
                                                    <%= "&#x2191"%>
                                                    <%} else {%>
                                                    <%= "&#x2193"%>
                                                    <%}%> <%= String.format("%.3f", quoteData.get(1))%> %</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5 text-center text-sm-left">
                                        <div class="card-body pb-0 px-0 px-md-4" style="margin-top: -120px;">
                                            <button data-bs-toggle="modal" data-bs-target="#modalCenter" class="btn btn-lg btn-outline-primary">Add to portfolio</button>
                                            <div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="modalCenterTitle">Add Stock to portfolio</h5>
                                                            <button
                                                                    type="button"
                                                                    class="btn-close"
                                                                    data-bs-dismiss="modal"
                                                                    aria-label="Close"
                                                            ></button>
                                                        </div>
                                                        <form action="portfolio" method="post">
                                                        <div class="modal-body">

                                                            <div class="row g-2">
                                                                <div class="col mb-0">
                                                                    <label for="nameWithTitle" class="form-label">Quantity</label>
                                                                    <input
                                                                            type="number"
                                                                            id="nameWithTitle"
                                                                            class="form-control"
                                                                            name="quantity"
                                                                            placeholder="Enter quantity"
                                                                    />
                                                                </div>
                                                                <div class="col mb-0">
                                                                    <label for="nameWithTitle" class="form-label">Select portfolio</label>
                                                                    <select
                                                                            type="number"
                                                                            name="portfolio"
                                                                            class="form-control"
                                                                    >
                                                                        <% for(portfolioBean portfolio: (ArrayList<portfolioBean>) request.getAttribute("portfolios")){%>
                                                                            <option value="<%= portfolio.getId()%>"><%= portfolio.getName()%></option>
                                                                        <% } %>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="row g-2">
                                                                <div class="col mb-0">
                                                                    <label for="emailWithTitle" class="form-label">Purshase price</label>
                                                                    <input
                                                                            type="number"
                                                                            id="emailWithTitle"
                                                                            name="purshasePrice"
                                                                            class="form-control"
                                                                            name="purshasePrice"
                                                                    />
                                                                </div>
                                                                <div class="col mb-0">
                                                                    <label for="dobWithTitle" class="form-label">purshase date</label>
                                                                    <input
                                                                            type="date"
                                                                            id="dobWithTitle"
                                                                            class="form-control"
                                                                            name="purshaseDate"
                                                                            placeholder="DD / MM / YY"
                                                                    />
                                                                </div>
                                                            </div>
                                                            <input type="hidden" name="stockName" value="<%= request.getAttribute("symbole")%>"/>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                                                Close
                                                            </button>
                                                            <button type="submit" class="btn btn-primary">Save changes</button>
                                                        </div>
                                                        </form>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                        </div>
                    </div>
                        <!-- Total Revenue -->
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
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!--/ Total Revenue -->


                    <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4" style="width: 100%">
                        <div class="card">
                            <div class="row row-bordered g-0">
                                <div class="col-md-8">
                                    <h5 class="card-header m-0 me-2 pb-3">Total Revenue</h5>
                                    <div id="second_chart" class="px-2"></div>
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>Quarter</th>
                                            <th>Total Revenue</th>
                                            <th>Net Income</th>
                                            <th>Volume</th>
                                            <th>Market cap</th>
                                        </tr>
                                        </thead>
                                        <tbody class="table-border-bottom-0">

                                        <% for (ArrayList<String> data: financialData.subList(0, financialData.size() -1)){ %>
                                            <tr>
                                                <td><%= data.get(0)%></td>
                                                <td><%= data.get(1)%></td>
                                                <td><%= data.get(2)%></td>
                                                <td><%= financialData.get(financialData.size() - 1).get(1)%></td>
                                                <td><%= financialData.get(financialData.size() - 1).get(0)%></td>
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

<!-- Vendors JS -->
<script src="../assets/vendor/libs/apex-charts/apexcharts.js"></script>

<!-- Main JS -->
<script src="../assets/js/main.js"></script>
    <!-- Page JS -->
    <script src="../assets/js/ui-modals.js"></script>
<!-- Page JS -->
<!--<script src="../assets/js/dashboards-analytics.js"></script>-->
<script src="../assets/js/chartt.js"></script>
<stock-chart data="<%= request.getAttribute("data")%>" financialData=" <%= request.getAttribute("financialData")%>"></stock-chart>>
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

</body>
</html>
