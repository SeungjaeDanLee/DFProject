<!DOCTYPE html>
<html lang="en">
    <head>
        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>관리자 페이지</title>

    </head>
    <header>
        <jsp:include page="/resources/layouts/DF00_layouts/DF00_adminNav.jsp"></jsp:include>
    </header>
    <body class="sb-nav-fixed">
    <div id="layoutSidenav" style="height: 100%; overflow: scroll;">

        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">관리자 페이지</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/">권한</a></li>
                        <li class="breadcrumb-item active">ADMIN</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-body">
                            관리자에게만 권한이 부여된 페이지입니다. 관리자가 아닐 시 로그아웃 해주시길 바랍니다.
                        </div>

                    </div>
                </div>
            </main>

        </div>
    </div>

    </body>


    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
    </body>
</html>
