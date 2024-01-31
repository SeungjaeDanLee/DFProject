<html>
<head>
    <title>내가 좋아하는 글 리스트</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<%--    <script src="../resources/js/DF02_board/DF0204_boardPaging.js"></script>--%>
</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>
<h3 style="text-align: center">내가 좋아하는 글</h3>
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
                <tr onclick="location.href='/board?bno=${board.bno}&page=${paging.page}';" style="cursor:pointer;">
                        <%--                <td>--%>
                        <%--                    <a href="/board?bno=${board.bno}&page=${paging.page}">${board.title}</a>--%>
                        <%--                </td>--%>
                    <td>${board.title}</td>
                    <td>${member.nick_name}</td>
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

<div class="container mt-5" style="display: flex; justify-content: center;">
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span>[이전]</span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/board/paging/myLike?page=${paging.page-1}">[이전]</a>
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
                <a href="/board/paging/myLike?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span>[다음]</span>
        </c:when>
        <c:otherwise>
            <a href="/board/paging/myLike?page=${paging.page+1}">[다음]</a>
        </c:otherwise>
    </c:choose>
</div>

<%--<div style="display: flex">--%>
<%--    <!--   이름검색기능   -->--%>
<%--    <input type="text" id="searchInput" placeholder="제목 입력...">--%>
<%--    <button type="button" id="searchBtn" class="btn btn-primary"--%>
<%--            style="width: 95px;border: 1px solid white;height: 38px;padding-top: 7px;background-color: black;border-color: black;color: white;border-radius: 5px;margin-left: 10px;">--%>
<%--        검색--%>
<%--    </button>--%>
<%--</div>--%>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>

<!-- jQuery, Popper.js, Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

<script>
    //검색기능추가
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById('searchBtn').addEventListener('click', function () {
            const searchTerm = document.getElementById('searchInput').value.toLowerCase();
            const rows = document.querySelectorAll('#datatablesSimple tbody tr');

            rows.forEach(row => {
                // 기존 코드에서 변경: 각 열이 존재하는지 확인
                const name = row.querySelector('td:nth-child(2)');
                const dept = row.querySelector('td:nth-child(3)');
                const checkInTime = row.querySelector('td:nth-child(4)');
                const checkOutTime = row.querySelector('td:nth-child(5)');

                // 수정: 각 열이 존재하지 않더라도 계속 검색 수행
                const nameText = name ? name.textContent.toLowerCase() : '';
                const deptText = dept ? dept.textContent.toLowerCase() : '';
                const checkInTimeText = checkInTime ? checkInTime.textContent.toLowerCase() : '';
                const checkOutTimeText = checkOutTime ? checkOutTime.textContent.toLowerCase() : '';

                // 수정: 각 열이 존재하지 않더라도 계속 검색 수행
                if (nameText.includes(searchTerm) || deptText.includes(searchTerm) || checkInTimeText.includes(searchTerm) || checkOutTimeText.includes(searchTerm)) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            });
        });
    });

</script>

</html>
