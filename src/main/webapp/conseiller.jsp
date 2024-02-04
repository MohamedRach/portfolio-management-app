<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.projets3.bean.ConseillerBean" %>
<%@ page import="com.example.projets3.bean.CommentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.projets3.bean.UserBean" %>
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

    <title>Conseiller</title>

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
<% ConseillerBean conseiller = (ConseillerBean) request.getAttribute("conseiller");%>
<% ArrayList<CommentBean> comments = (ArrayList<CommentBean>) request.getAttribute("comments");%>
<% ArrayList<UserBean> users = (ArrayList<UserBean>) request.getAttribute("users");%>
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
                    <div class="col-lg-8 mb-4 order-0">
                        <div class="card">
                            <div class="d-flex align-items-end row">
                                <div class="col-sm-7">

                                    <div class="card-body">
                                        <h5 class="card-title text-primary"><%= conseiller.getPrenom() %> <%= conseiller.getNom() %></h5>
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

                                        <div style="display: flex; flex-direction: column; row-gap: 5px; margin-top: 20px;">
                                            <div style="display: flex; flex-direction: row; column-gap: 20px;">
                                                <p>Hourly Rate</p>
                                                <p>Postive Reviews</p>
                                                <p>Rehired</p>
                                            </div>
                                            <div style="display: flex; flex-direction: row; column-gap: 50px;">
                                                <h5 style="text-align: center"><%= conseiller.getHourly_Rate() %>$</h5>
                                                <h5 style="margin-left: 47px"><%= conseiller.getPostive_Reviews() %></h5>
                                                <h5 style="margin-left: 40px"><%= conseiller.getRehired() %></h5>
                                            </div>

                                        </div>
                                    <c:if test="${conseiller.getHired() == 0}">
                                        <a href="javascript:;" class="btn btn-md btn-primary" style="margin-top: 20px;">Hire Me</a>
                                    </c:if>
                                        <c:if test="${conseiller.getHired() == 1}">
                                            <a href="javascript:;" class="btn btn-md btn-primary" style="margin-top: 20px;">Generate a review</a>
                                        </c:if>

                                    </div>

                                </div>
                                <div class="col-sm-5 text-center text-sm-left">
                                    <div class="card-body pb-0 px-0 px-md-4">
                                        <img style="border-radius: 50%; width: 100px; margin-bottom: 80px;" src="<%= conseiller.getImageLink() %>" alt="Image du conseiller" />
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${conseiller.getHired() == 0}">
                        <div class="card" style="margin-top: 30px;">
                            <div class="d-flex align-items-end row">
                                <div>
                                    <div class="card-body">
                                        <h5 class="card-title text-primary">About me</h5>
                                        <p>  <%= conseiller.getDescription() %></p>

                                    </div>
                                </div>

                            </div>
                        </div>
                        </c:if>
                        <div class="card" style="margin-top: 30px;">
                            <div class="d-flex align-items-end row">
                                <div>
                                    <c:if test="${conseiller.getHired() == 1}">
                                    <div class="card-body">
                                        <h5 class="card-title text-primary">Activity</h5>
                                        <span style="background: green; color: white; width: 10px; height: 7px; padding: 3px 4px; border-radius: 7%;">What to buy</span>
                                        <div style="border-bottom: 0.8px solid darkgrey;margin-top: 8px; margin-bottom: 10px;">
                                            <p> buy 50 shares of microsoft stock</p>
                                            <button class="btn btn-sm btn-outline-primary" style="margin-bottom: 8px">View details</button>
                                        </div>
                                        <div style="border-bottom: 0.8px solid darkgrey;margin-top: 8px; margin-bottom: 10px;">
                                            <p> buy 50 shares of microsoft stock</p>
                                            <button class="btn btn-sm btn-outline-primary" style="margin-bottom: 8px">View details</button>
                                        </div>
                                        <span style="background: red; color: white; width: 10px; height: 7px; padding: 3px 4px; border-radius: 7%;">What to sell</span>
                                        <div style="border-bottom: 0.8px solid darkgrey;margin-top: 8px; margin-bottom: 10px;">
                                            <p> buy 50 shares of microsoft stock</p>
                                            <button class="btn btn-sm btn-outline-primary" style="margin-bottom: 8px">View details</button>
                                        </div>
                                    </div>
                                </div>
                                </c:if>

                            </div>
                        </div>
            <c:if test="${conseiller.getHired() == 1}">
                        <div class="card" style="margin-top: 30px;">
                            <div class="d-flex align-items-end row">
                                <div>
                                    <%
                                        // Your array


                                        // Loop to display array elements

                                        for (int i = 0;i<comments.size(); i++) {

                                    %>
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <h5><%=users.get(i).getNom() + " " + users.get(i).getPrenom()%></h5>
                                            <p class="card-text">
                                                <%= comments.get(i).getComment()%>
                                            </p>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>

                            </div>
                        </div>
            </c:if>
                    </div>
                </div>
                <!-- Content wrapper -->
            </div>
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
