<html>
<head>
    <title>리스트</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<jsp:include page="/resources/layouts/DF02_layouts/DF02_boardMenu.jsp"></jsp:include>

<div class="container mt-5">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">좋아요</th>
            <th scope="col">조회수</th>
            <th scope="col">카테고리</th>
            <th scope="col">등록일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board">
            <tr>

                <td>
                    <a href="/board?bno=${board.bno}">${board.title}</a>
                </td>
                <td>${board.mno}</td>
                <td>${board.like_counts}</td>
                <td>${board.view_counts}</td>
                <td>
                    <c:choose>
                        <c:when test="${board.category == 0}">공지글</c:when>
                        <c:when test="${board.category == 1}">자유글</c:when>
                        <c:when test="${board.category == 2}">정보글</c:when>
                        <c:otherwise>분류되지 않은 글</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <script>document.write(new Date('${board.written_date}').toLocaleString('ko', {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit'
                    }));</script>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>

<!-- jQuery, Popper.js, Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
