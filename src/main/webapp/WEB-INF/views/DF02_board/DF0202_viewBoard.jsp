<html>
<head>
    <title>글읽기</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <link href="../resources/css/DF03_reply.css" rel="stylesheet"/>
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
    <div class="button-area" style="text-align: center;">
        <button type="button" onclick="location.href='<c:url value='/board/paging?page=${page}'></c:url>'">목록</button>
        <c:if test="${isAuthor}">
            <button type="button" onclick="location.href='<c:url value='/board/update'><c:param name='bno' value='${board.bno}'/></c:url>'">수정</button>
            <button type="button" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/board/delete'><c:param name='bno' value='${board.bno}'/></c:url>';">삭제</button>
        </c:if>
    </div>
<hr>


<form action="/reply/write" method="post">
    <div class="Select_all">
        <section>
            <ul>
                <li>
                    <h3>댓글</h3>
                </li>
                <li>
                    <textarea type="text" name="content" placeholder="내용을 입력해주세요" class="reply_content_box"></textarea>
                </li>
                <li style="margin-top: 10px; float: right">
                    <input style="width: 100px" type="submit" value="댓글 등록">
                </li>
                <input type="hidden" name="bno" value="${board.bno}">
            </ul>
        </section>
    </div>
</form>


<div id="replyList" style="text-align: -webkit-center;">
    <table>
        <tr>
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:forEach items="${replyList}" var="reply">
            <tr>
                <td>${reply.mno}</td>
                <td>${reply.content}</td>
                <td>${reply.written_date}</td>
            </tr>
        </c:forEach>
    </table>
</div>




    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
    <script src="../resources/js/DF02_board/DF0202_viewBoard.js"></script>
</body>
</html>
