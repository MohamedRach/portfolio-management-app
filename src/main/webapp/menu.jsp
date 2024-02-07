<%@ page import="java.util.Objects" %>
<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
        <a href="/" class="app-brand-link">
              <img src="../assets/img/logo.png" style="width: 200px; height: 120px">
        </a>

        <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
    </div>

    <div class="menu-inner-shadow"></div>
    <% HttpSession session1 = request.getSession();%>
    <ul class="menu-inner py-1">
        <!-- Dashboard -->
        <%if (Objects.equals(session1.getAttribute("role"), "admin")){%>
        <li class="menu-item">
            <a href="/dashboard" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div data-i18n="Analytics">Users</div>
            </a>
        </li>

        <!-- Layouts -->
        <li class="menu-item sub">
            <a href="/portfolio" class="menu-link">
                <i class="menu-icon tf-icons bx bx-layout"></i>
                <div data-i18n="Layouts">Consultants</div>
            </a>


        </li>
        <%} else {%>
        <li class="menu-item">
            <a href="/dashboard" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div data-i18n="Analytics">Dashboard</div>
            </a>
        </li>

        <!-- Layouts -->
        <li class="menu-item sub">
            <a href="/portfolio" class="menu-link">
                <i class="menu-icon tf-icons bx bx-layout"></i>
                <div data-i18n="Layouts">Portfolios</div>
            </a>


        </li>

        <%if (Objects.equals(session1.getAttribute("role"), "user")){%>
            <li class="menu-item">
                <a href="/conseiller" class="menu-link">
                    <i class="menu-icon tf-icons bx bx-dock-top"></i>
                    <div data-i18n="Account Settings">Consultants</div>
                </a>
            </li>
            <li class="menu-item">
                <a href="/report" class="menu-link">
                    <i class="menu-icon tf-icons bx bx-dock-top"></i>
                    <div data-i18n="Account Settings">Reports</div>
                </a>
            </li>
        <%} else {%>
        <li class="menu-item">
            <a href="/report" class="menu-link">
                <i class="menu-icon tf-icons bx bx-dock-top"></i>
                <div data-i18n="Account Settings">Reports</div>
            </a>
        <%}}%>
    </ul>
</aside>