<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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

    <title>ModifierUtilisateur</title>

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
                            <div class="card">

                                <div class="card-header">
                                    <span>Modification d'un Utilisateur : </span>
                                </div>
                                <div class="card-body">
                                    <form action="/modifier-utilisateur" method="post">
                                        <div class="form-group">
                                            <label class="label-control"><strong> ID </strong>:</label>
                                            <input class="form-control" name="id" type="hidden" value="${user.id}">
                                            <label class="label-control">${user.id}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="label-control">Nom :</label>
                                            <input class="form-control" name="nom" placeholder="Entrer le nom" value="${user.nom}">
                                        </div>
                                        <div class="form-group">
                                            <label class="label-control">Prenom :</label>
                                            <input class="form-control" name="prenom" placeholder="Entrer le prénom" value="${user.prenom}">
                                        </div>
                                        <div class="form-group">
                                            <label class="label-control">Email :</label>
                                            <input class="form-control" name="email" placeholder="Entrer l'email" value="${user.email}">
                                        </div>
                                        <!-- Ajouter d'autres propriétés du conseiller au besoin -->
                                        <button class="btn btn-primary" type="submit">Enregistrer</button>
                                        <button class="btn btn-primary" type="button" onclick="cancelAction()">Annuler</button>

                                        <script>
                                            function cancelAction() {
                                                window.location.href = 'users';
                                            }
                                        </script>


                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content-backdrop fade"></div>
        </div>
        <!-- Content wrapper -->
    </div>
    <!-- / Layout page -->
</div>

<!-- Overlay -->
<div class="layout-overlay layout-menu-toggle"></div>
</div>
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
    console.log("<%= request.getAttribute("data")%>")
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
<stock-chart data="<%= request.getAttribute("data")%>"></stock-chart>
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>

