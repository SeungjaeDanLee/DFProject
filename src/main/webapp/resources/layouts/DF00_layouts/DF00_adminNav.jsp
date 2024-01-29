<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../resources/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="../resources/js/datatables-simple-demo.js"></script>

    <%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

    <%-- Check if loginMemberLevel is not 0, then redirect to error page --%>

    <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
        <!-- Navbar Brand-->
        <a class="navbar-brand" href="/">
            <img src="../resources/assets/dogfoot.png" alt="개발자국" width="10%" height="10%">
            DogFit
        </a>
        <!-- Sidebar Toggle-->
        <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="navbar-brand ps-3" disabled="none">관리자 페이지</a>
                            <div class="sb-sidenav-menu-heading">Core</div>
                            <a class="nav-link" href="/admin/home">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                관리자 홈
                            </a>
                            <div class="sb-sidenav-menu-heading">Addons</div>
                            <a class="nav-link" href="/admin/memberManagement">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                회원관리
                            </a>
                            <a class="nav-link" href="/admin/boardManagement">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                게시글 관리
                            </a>
                            <a class="nav-link" href="/admin/replyManagement">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                댓글 관리
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as: Dogfit</div>
                        <a class="dropdown-item" href="/members/logout">Logout</a>
                    </div>
                </nav>
            </div>
        </div>
    </nav>
</head>
