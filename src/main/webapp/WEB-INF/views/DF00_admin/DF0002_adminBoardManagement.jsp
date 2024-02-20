<!DOCTYPE html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>게시글 관리 페이지</title>

</head>

<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_adminNav.jsp"></jsp:include>
</header>

<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet"/>
<link href="../resources/css/DF00_admin.css" rel="stylesheet"/>
<link href="../resources/css/style.css" rel="stylesheet" />

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../resources/js/DF00_admin/DF0002_boardManagement.js"></script>
<script src="../resources/js/scripts.js" type="text/javascript"></script>
<script src="../resources/js/datatables-simple-demo.js" type="text/javascript"></script>


<body class="sb-nav-fixed">
<div id="layoutSidenav" style="height: 100%; overflow: scroll;">

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">게시글 관리 페이지</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/">권한</a></li>
                    <li class="breadcrumb-item active">ADMIN</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-body">
                        관리자에게만 권한이 부여된 페이지입니다. 관리자가 아닐 시 로그아웃 해주시길 바랍니다.
                    </div>

                </div>
                <div class="card mb-4">
                    <div class="card-header" style="font-weight: bold;color: white; background-color: #00c473;">
                        <i class="fas fa-table me-1"></i>
                        게시글 관리 페이지
                    </div>
                    <div class="card-body">
                        <div style="display: flex">
                            <!--   이름검색기능   -->
                            <input type="text" id="searchInput" placeholder="제목 입력...">
                            <button type="button" id="searchBtn" class="btn btn-primary"
                                    style="width: 95px;border: 1px solid white;height: 38px;padding-top: 7px;background-color: #00c473;border-color: #00c473;color: white;border-radius: 5px;margin-left: 10px;">
                                검색
                            </button>
                        </div>
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>게시글 번호</th>
                                <th>제목</th>
                                <th>좋아요</th>
                                <th>조회수</th>
                                <th>쓴 날짜</th>
                                <th>카테고리</th>
                                <th>작성자</th>
                                <th>삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${boardList}" var="board">
                                <tr>
                                    <td id="bno">${board.bno}</td>
                                    <td id="title">
                                        <a href="/board/boardView?bno=${board.bno}">${board.title}</a>
                                    </td>
                                    <td id="like_counts">${board.like_counts}</td>
                                    <td id="view_counts">${board.view_counts}</td>
                                    <td id="written_date">${board.written_date}</td>
                                    <td id="category">
                                        <c:choose>
                                            <c:when test="${board.category == 0}">공지글</c:when>
                                            <c:when test="${board.category == 1}">자유글</c:when>
                                            <c:when test="${board.category == 2}">정보글</c:when>
                                            <c:otherwise>분류되지 않은 글</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td id="mno">
                                        <c:forEach items="${memberList}" var="member">
                                            <c:if test="${board.mno == member.mno}">
                                                ${member.nick_name}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td id="board_delete">
                                        <button class="btn btn-outline-danger" onclick="deleteBoard('${board.bno}')">삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>

    </div>
</div>

</body>
<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</html>
