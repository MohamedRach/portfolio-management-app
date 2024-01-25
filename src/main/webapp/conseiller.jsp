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
                    <div class="col-lg-8 mb-4 order-0">
                        <div class="card">
                            <div class="d-flex align-items-end row">
                                <div class="col-sm-7">
                                    <div class="card-body">
                                        <h5 class="card-title text-primary">Tom Smith</h5>
                                        <div>
                                            <span style="background: orange; color: white; width: 10px; height: 7px; padding: 3px 4px; border-radius: 7%; margin-right:10px; ">3.5</span>
                                            <span class="fa fa-star" style="color: orange;"></span>
                                            <span class="fa fa-star" style="color: orange;"></span>
                                            <span class="fa fa-star" style="color: orange;"></span>
                                            <span class="fa fa-star"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                        <div style="display: flex; flex-direction: column; row-gap: 5px; margin-top: 20px;">
                                            <div style="display: flex; flex-direction: row; column-gap: 20px;">
                                                <p>Hourly Rate</p>
                                                <p>Postive Reviews</p>
                                                <p>Rehired</p>
                                            </div>
                                            <div style="display: flex; flex-direction: row; column-gap: 50px;">
                                                <h5 style="text-align: center">53 $</h5>
                                                <h5 style="margin-left: 47px">100</h5>
                                                <h5 style="margin-left: 40px">10</h5>
                                            </div>

                                        </div>
                                        <a href="javascript:;" class="btn btn-md btn-primary" style="margin-top: 20px;">Hire Me</a>
                                    </div>
                                </div>
                                <div class="col-sm-5 text-center text-sm-left">
                                    <div class="card-body pb-0 px-0 px-md-4">
                                        <img style="border-radius: 50%; width: 100px; margin-bottom: 80px;" src="../assets/img/elements/2.jpg" alt="Card image cap" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="margin-top: 30px;">
                            <div class="d-flex align-items-end row">
                                <div>
                                    <div class="card-body">
                                        <h5 class="card-title text-primary">About me</h5>
                                        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="card" style="margin-top: 30px;">
                            <div class="d-flex align-items-end row">
                                <div>
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

                            </div>
                        </div>
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
