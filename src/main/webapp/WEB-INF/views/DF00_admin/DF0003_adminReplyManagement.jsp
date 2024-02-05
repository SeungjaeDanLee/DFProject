<!DOCTYPE html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>댓글 관리 페이지</title>

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
<script src="../resources/js/DF00_admin/DF0003_replyManagement.js"></script>

<script src="../resources/js/DF00_admin/DF0001_memberManagement.js"></script>
<script src="../resources/js/scripts.js" type="text/javascript"></script>
<script src="../resources/js/datatables-simple-demo.js" type="text/javascript"></script>


<body class="sb-nav-fixed">
<div id="layoutSidenav" style="height: 100%; overflow: scroll;">

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">댓글 관리 페이지</h1>
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
                        댓글 관리 페이지
                    </div>
                    <div class="card-body">
                        <div style="display: flex">
                            <!--   이름검색기능   -->
                            <input type="text" id="searchInput" placeholder="내용, 게시글...">
                            <button type="button" id="searchBtn" class="btn btn-primary"
                                    style="width: 95px;border: 1px solid white;height: 38px;padding-top: 7px;background-color: #00c473;border-color: #00c473;color: white;border-radius: 5px;margin-left: 10px;">
                                검색
                            </button>
                        </div>
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>댓글 번호</th>
                                <th>내용</th>
                                <th>쓴 날짜</th>
                                <th>해당 게시글 제목</th>
                                <th>작성자</th>
                                <th>삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${replyList}" var="reply">
                                <tr>
                                    <td id="rno">${reply.rno}</td>
                                    <td id="content">${reply.content}</td>
                                    <td id="written_date">${reply.written_date}</td>
                                    <td id="bno">
                                        <a href="/board?bno=${reply.bno}">
                                            <c:forEach items="${boardList}" var="board">
                                                <c:if test="${reply.bno == board.bno}">
                                                    ${board.title}
                                                </c:if>
                                            </c:forEach>
                                        </a>
                                    </td>
                                    <td id="mno">
                                        <c:forEach items="${memberList}" var="member">
                                            <c:if test="${reply.mno == member.mno}">
                                                ${member.nick_name}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td id="reply_delete">
                                        <button class="btn btn-outline-danger" onclick="deleteReply('${reply.rno}')">삭제</button>
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
