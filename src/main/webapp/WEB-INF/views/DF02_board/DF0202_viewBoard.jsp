<html>
<head>
    <title>글읽기</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="Select_all">
    <section>
        <ul>
            <li>
                <c:choose>
                    <c:when test="${board.category == 0}">공지글</c:when>
                    <c:when test="${board.category == 1}">자유글</c:when>
                    <c:when test="${board.category == 2}">정보글</c:when>
                    <c:otherwise>분류되지 않은 글</c:otherwise>
                </c:choose>
            </li>
            <li>
                <h2>${board.title}</h2>
            </li>
            <hr>
            ${board.content}
        </ul>
    </section>
</div>
<button onclick="listFn()">목록</button>
<button onclick="updateFn()">수정</button>
<button onclick="deleteFn()">삭제</button>

    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
    <script src="../resources/js/DF02_board/DF0202_viewBoard.js"></script>
</body>
</html>
