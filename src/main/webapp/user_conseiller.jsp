<%@ page import="com.example.projets3.bean.ConseillerBean" %>
<%@ page import="java.util.List" %>
<%@page buffer="8192kb" autoFlush="true" %>

<%@ page import="com.example.projets3.bean.ConseillerBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
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
                    <h2 class="card-title text-primary">Mes conseillers :</h2>
                    <div style="display: grid; grid-template-columns: repeat(4, 250px); column-gap: 30px; row-gap: 20px;">

                        <%
                            ArrayList<ConseillerBean> conseillers = (ArrayList<ConseillerBean>) request.getAttribute("conseillers");
                            Set<Integer> hiredConseillersIds = (Set<Integer>) request.getAttribute("hiredConseillersIds");

                            for (ConseillerBean conseiller : conseillers) {
                                if (hiredConseillersIds.contains(conseiller.getId())) {
                        %>
                        <div class="col">

                            <div class="card h-100">
                                <img style="border-radius: 50%; width: 100px; margin-top: 30px; margin-left: 80px" src="<%= conseiller.getImageLink() %>" alt="Image du conseiller" />
                                <div class="card-body">
                                    <h5 class="card-title" style="text-align: center"><%= conseiller.getPrenom() %> <%= conseiller.getNom() %></h5>

                                    <!-- Display star rating based on conseiller.rating -->
                                    <div style="text-align: center; margin-bottom: 10px;">
                                        <%
                                            double rating = conseiller.getRating();
                                            int numberOfFullStars = (int) rating;
                                            int remainingStars = 5 - numberOfFullStars;

                                            // Iterate to display full stars
                                            for (int i = 0; i < numberOfFullStars; i++) {
                                        %>
                                        <span class="fa fa-star" style="color: orange;"></span>
                                        <%
                                            }

                                            // If there is a partial star, display it
                                            if (rating % 1 != 0) {
                                        %>
                                        <span class="fa fa-star-half" style="color: orange;"></span>
                                        <%
                                                remainingStars--; // Adjust remaining stars if a half star is displayed
                                            }

                                            // Add empty stars for the remaining space
                                            for (int i = 0; i < remainingStars; i++) {
                                        %>
                                        <span class="fa fa-star"></span>
                                        <%
                                            }
                                        %>
                                    </div>

                                    <!-- Other details about the conseiller -->
                                    <!-- Display conseiller.description -->
                                    <p class="card-text" style="margin-top: 10px">
                                        <%= conseiller.getDescription() %>
                                    </p>

                                    <!-- Buttons -->
                                    <form action="conseiller" method="post">
                                        <input type="hidden" name="id_conseiller" value="<%= conseiller.getId() %>" />
                                        <input type="hidden" name="id_btn" value="2" />
                                        <button  type="submit" style="width: 100%" class="btn btn-primary">Voir le profil</button>
                                    </form>

                                </div>
                            </div>
                        </div>

                        <%
                                }
                            }
                        %>
                </div>
                    <h2 class="card-title text-primary">Tous les conseillers :</h2>
                    <div style="display: grid; grid-template-columns: repeat(4, 250px); column-gap: 30px; row-gap: 20px;">

                        <%
                            for (ConseillerBean conseiller : conseillers) {
                                if (!hiredConseillersIds.contains(conseiller.getId())) {
                        %>
                        <div class="col">
                            <div class="card h-100">
                                <img style="border-radius: 50%; width: 100px; margin-top: 30px; margin-left: 80px" src="<%= conseiller.getImageLink() %>" alt="Image du conseiller" />
                                <div class="card-body">
                                    <h5 class="card-title" style="text-align: center"><%= conseiller.getPrenom() %> <%= conseiller.getNom() %></h5>

                                    <!-- Display star rating based on conseiller.rating -->
                                    <div style="text-align: center; margin-bottom: 10px;">
                                        <%
                                            double rating = conseiller.getRating();
                                            int numberOfFullStars = (int) rating;
                                            int remainingStars = 5 - numberOfFullStars;

                                            // Iterate to display full stars
                                            for (int i = 0; i < numberOfFullStars; i++) {
                                        %>
                                        <span class="fa fa-star" style="color: orange;"></span>
                                        <%
                                            }

                                            // If there is a partial star, display it
                                            if (rating % 1 != 0) {
                                        %>
                                        <span class="fa fa-star-half" style="color: orange;"></span>
                                        <%
                                                remainingStars--; // Adjust remaining stars if a half star is displayed
                                            }

                                            // Add empty stars for the remaining space
                                            for (int i = 0; i < remainingStars; i++) {
                                        %>
                                        <span class="fa fa-star"></span>
                                        <%
                                            }
                                        %>
                                    </div>

                                    <!-- Other details about the conseiller -->
                                    <!-- Display conseiller.description -->
                                    <p class="card-text" style="margin-top: 10px">
                                        <%= conseiller.getDescription() %>
                                    </p>

                                    <!-- Buttons -->
                                    <form action="conseiller" method="post">
                                        <input type="hidden" name="id_conseiller" value="<%= conseiller.getId() %>" />
                                        <input type="hidden" name="id_btn" value="1" />
                                        <button type="submit" style="width: 100%" class="btn btn-primary">Voir le profil</button>
                                    </form>


                                </div>
                            </div>
                        </div>

                        <%
                                }
                            }
                        %>
                    </div>
                <!-- Content wrapper -->

            <!-- / Layout page -->
        </div>

        <!-- Overlay -->
        <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <script>
        const menus = document.querySelectorAll(".menu-item");
        menus.forEach((menu) => (
            menu.addEventListener("click", (e) => {
                e.preventDefault()
                if (menu.classList.contains('open')) {
                    // The 'open' class is present in the element's class list
                    menu.classList.remove("open")
                } else {
                    // The 'open' class is not present in the element's class list
                    menu.classList.add("open")
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
    <!--<script src="../assets/js/dashboards-analytics.js"></script>-->
    <script src="../assets/js/chartt.js"></script>
    <stock-chart data="<%= request.getAttribute("data")%>"></stock-chart>>
    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
