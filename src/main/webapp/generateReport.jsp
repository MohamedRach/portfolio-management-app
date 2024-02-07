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

    <title>Add portfolio</title>

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

                    <!-- Total Revenue -->
                    <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4" style="width: 90%; margin-left: 30px">
                        <div class="card">
                            <div class="row row-bordered g-0">
                                <div class="col-md-8">
                                    <h5 class="card-header m-0 me-2 pb-3">Generate Report</h5>
                                    <div>
                                        <form action="generateReport" method="post"  style="margin-left: 20px; display: flex; flex-direction: column; justify-content: center">
                                            <label for="nameWithTitle" class="form-label">Name</label>
                                            <input
                                                    type="text"
                                                    id="nameWithTitle"
                                                    class="form-control"
                                                    placeholder="Enter report name"
                                                    name="name"
                                                    style="width: 25%; margin-bottom: 20px"
                                            />
                                            <div style="display: flex;flex-direction: row; column-gap: 20px;margin-bottom: 20px">
                                                <label style="margin-top: 15px" for="firtperiodstart" class="form-label">First period start</label>
                                                <input class="form-control" name="firstPeriodStart" type="date" placeholder="Enter Date" id="firtperiodstart" style="width: 230px; height: 50px" />
                                                <label style="margin-top: 15px" for="firtperiodend" class="form-label">First period end</label>
                                                <input class="form-control" name="firstPeriodEnd" type="date" placeholder="Enter Date" id="firtperiodend" style="width: 230px; height: 50px" />
                                            </div>
                                            <div style="display: flex;flex-direction: row; column-gap: 20px;margin-bottom: 20px">
                                                <label style="margin-top: 15px" for="secondperiodstart" class="form-label">Second period start</label>
                                                <input class="form-control" name="secondPeriodStart" type="date" placeholder="Enter Date" id="secondperiodstart" style="width: 230px; height: 50px" />
                                                <label style="margin-top: 15px" for="secondperiodend" class="form-label">Second period end</label>
                                                <input class="form-control" name="secondPeriodEnd" type="date" placeholder="Enter Date" id="secondperiodend" style="width: 230px; height: 50px" />
                                            </div>
                                            <div style="display: flex;flex-direction: row; column-gap: 20px;margin-bottom: 20px">
                                                <label style="margin-top: 15px" for="thirdperiodstart" class="form-label">Third period start</label>
                                                <input class="form-control" name="thirdPeriodStart" type="date" placeholder="Enter Date" id="thirdperiodstart" style="width: 230px; height: 50px" />
                                                <label style="margin-top: 15px" for="thirdperiodend" class="form-label">Third period end</label>
                                                <input class="form-control" name="thirdPeriodEnd" type="date" placeholder="Enter Date" id="thirdperiodend" style="width: 230px; height: 50px" />
                                            </div>

                                            <button type="submit" class="btn btn-primary" style="margin-bottom: 30px; width: 25%">Generate Report</button>
                                        </form>

                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                    <!--/ Total Revenue -->



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
        <script src="../assets/js/stateManagement.js"></script>

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
        <!-- Page JS -->
        <script src="../assets/js/ui-modals.js"></script>
        <!-- Page JS -->
        <!--<script src="../assets/js/dashboards-analytics.js"></script>-->

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
