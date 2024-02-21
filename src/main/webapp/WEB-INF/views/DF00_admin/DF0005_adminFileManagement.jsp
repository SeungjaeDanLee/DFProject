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

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../resources/js/DF00_admin/DF0005_fileManagement.js"></script>
<script src="../resources/js/scripts.js" type="text/javascript"></script>
<script src="../resources/js/datatables-simple-demo.js" type="text/javascript"></script>


<body class="sb-nav-fixed">
<div id="layoutSidenav" style="height: 100%; overflow: scroll;">

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">파일 관리 페이지</h1>
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
                        파일 관리 페이지
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
                                <th>파일 번호</th>
                                <th>UUID</th>
                                <th>원 이름</th>
                                <th>업로드 날짜</th>
                                <th>경로</th>
                                <th>게시물</th>
                                <th>상태</th>
                                <th>수정</th>
                                <th>db 삭제</th>
                                <th>완전 삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${fileList}" var="file">
                                <tr>
                                    <td id="fno">${file.fno}</td>
                                    <td id="file_name">${file.file_name}</td>
                                    <td id="origin_name">${file.origin_name}</td>
                                    <td id="uploaded_date">${file.uploaded_date}</td>
                                    <td id="path">${file.path}</td>
                                    <td id="bno">
                                        <a href="/board/boardView?bno=${file.bno}">
                                            <c:forEach items="${boardList}" var="board">
                                                <c:if test="${file.bno == board.bno}">
                                                    ${board.title}
                                                </c:if>
                                            </c:forEach>
                                        </a>
                                    </td>
                                    <td id="yn">
                                        <c:choose>
                                            <c:when test="${file.yn == true}"><div style="color: blue">일반</div></c:when>
                                            <c:when test="${file.yn == false}"><div style="color: orangered">취소</div></c:when>
                                        </c:choose>
                                    </td>
                                    <td id="file_update">
<%--                                        <select id="pop" name="yn">--%>
<%--                                            <option selected disabled value="${file.yn}" readonly="readonly">--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${file.yn == true}">일반 정보</c:when>--%>
<%--                                                    <c:when test="${file.yn == false}">삭제 정보</c:when>--%>
<%--                                                </c:choose>--%>
<%--                                            </option>--%>
<%--                                            <option value="true">일반 정보</option>--%>
<%--                                            <option value="false">삭제 정보</option>--%>
<%--                                        </select>--%>
                                        <button class="btn btn-outline-warning" onclick="updateStatusFile('${file.fno}', '${file.yn}')">수정</button>
                                    </td>
                                    <td id="file_delete">
                                        <button class="btn btn-outline-danger" onclick="deleteFile('${file.fno}')">삭제</button>
                                    </td>
                                    <td id="file_delete_both">
                                        <a href="<c:url value='/admin/fileManagement/deleteBoth'><c:param name='file_name' value='${file.file_name}'/><c:param name='path' value='${file.path}'/><c:param name='fno' value='${file.fno}'/></c:url>"
                                           class="btn btn-outline-danger">삭제</a>
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
