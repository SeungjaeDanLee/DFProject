<html>
<head>
    <title>페이지 리스트</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="../resources/js/DF00_admin/DF0204_boardPaging.js"></script>
</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<jsp:include page="/resources/layouts/DF02_layouts/DF02_boardMenu.jsp"></jsp:include>

<div class="container mt-5">
    <h3>전체 게시판입니다.</h3>
    <br>
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
            <tr onclick="location.href='/board/boardView?bno=${board.bno}&page=${paging.page}';" style="cursor:pointer;">
                <td>${board.title}</td>
                <td>
                    <c:forEach items="${memberList}" var="member">
                        <c:if test="${board.mno == member.mno}">
                            ${member.nick_name}
                        </c:if>
                    </c:forEach>
                </td>
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
                    <fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${board.written_date}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container mt-5" style="display: flex; justify-content: center;">
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span>[이전]</span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/board/paging?page=${paging.page-1}">[이전]</a>
        </c:otherwise>
    </c:choose>

    <%--  for(int i=startPage; i<=endPage; i++)      --%>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
            <c:when test="${i eq paging.page}">
                <span>${i}</span>
            </c:when>

            <c:otherwise>
                <a href="/board/paging?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span>[다음]</span>
        </c:when>
        <c:otherwise>
            <a href="/board/paging?page=${paging.page+1}">[다음]</a>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>

<!-- jQuery, Popper.js, Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
