<%@ page import="java.util.List" %>
<%@page buffer="8192kb" autoFlush="true" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.projets3.bean.portfolioBean" %>
<%@ page import="com.example.projets3.bean.reportBean" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                <!-- Content -->

                <div class="container-xxl flex-grow-1 container-p-y">
                    <button style="margin-bottom: 20px" onclick="location.href='/generateReport'" class="btn-outline-primary btn-lg">Generate new report</button>
                    <h2 class="card-title text-primary">All of your reports :</h2>
                    <div style="display: grid; grid-template-columns: repeat(4, 250px); column-gap: 30px; row-gap: 20px;">

                        <%
                            for (reportBean report : (ArrayList<reportBean>) request.getAttribute("reports")) {

                        %>
                        <div class="col">
                            <div class="card h-100">

                                <div class="card-body">
                                    <h5 class="card-title" style="text-align: center"><%= report.getName() %></h5>

                                    <!-- Display star rating based on conseiller.rating -->


                                    <!-- Other details about the conseiller -->
                                    <!-- Display conseiller.description -->


                                    <!-- Buttons -->


                                    <a href=<%= "/report?id=" + report.getId()%>> <button type="button" style="width: 100%" class="btn btn-primary">see Report</button><a>
                                </div>

                            </div>
                        </div>

                        <%

                            }
                        %>
                    </div>
                    <!-- Content wrapper -->

                    <!-- / Layout page -->
                </div>

                <!-- Overlay -->

            </div>
            <!-- / Layout wrapper -->


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
</body>
</html>
