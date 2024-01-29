<html>
<head>
    <title>글쓰기</title>
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

<form action="/board/write" method="post" enctype="multipart/form-data">
    <div class="Select_all">
        <section>
            <ul>
                <li>
                    <h3>글 종류</h3>
                    <select id="pop" name="category" required>
                        <option selected disabled value="">선택</option>
                        <option value="0">공지</option>
                        <option value="1">자유</option>
                        <option value="2">정보</option>
                    </select>
                </li>
            </ul>

            <ul>
                <li>
                    <h3><br>제목</h3>
                    <input type="text" name="title" placeholder="제목을 입력해주세요" class="title" required>
                </li>
            </ul>

            <ul>
                <li>
                    <textarea type="text" name="content" placeholder="내용을 입력해주세요" class="content_box"
                              required></textarea>
                </li>
            </ul>

            <ul>
                <li style="margin-top: 10px;">
                    <input type="submit" value="등록" style="width: 800px;">
                </li>
            </ul>
        </section>
    </div>
</form>


<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
<script src="../resources/js/DF02_board/DF0201_writeBoard.js"></script>
</body>
</html>
