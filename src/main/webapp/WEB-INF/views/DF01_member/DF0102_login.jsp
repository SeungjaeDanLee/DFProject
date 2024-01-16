<html>
<head>
    <title>로그인</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <link href="../resources/css/DF01_member.css" rel="stylesheet"/>
    <script src="../resources/js/DF01_member.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="login">
    <form class="login_form" action="/members/login" method="post">
        <h3>로그인</h3><br>

        <label for="id">아이디</label>
        <input type="text" id="id" name="id" required="">

        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required="">

        <input type="submit" value="로그인">

        <div class="float-end" style="margin-top: 5%">신규 회원이라면??
            <a class="float-end" href="/members/new" style="text-decoration-line: none;">회원가입</a>
        </div>
    </form>
</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</body>
</html>
