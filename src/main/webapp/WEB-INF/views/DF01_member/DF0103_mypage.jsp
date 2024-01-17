<html>
<head>
    <title>내 정보</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="../resources/css/DF01_member.css" rel="stylesheet"/>
    <script src="../resources/js/DF01_member/DF0101_join.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

</head>
<body>

<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="mypage">
    <form class="mypage_form" action="/members/update" method="post">
        <h3>내 정보</h3><br>
        <%--필수 기재--%>
        <fieldset>
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" readonly>
        </fieldset>

        <fieldset>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" readonly>
        </fieldset>

        <fieldset>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" readonly>
        </fieldset>

        <fieldset>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" readonly>
        </fieldset>

        <fieldset>
            <label for="nick_name">닉네임</label>
            <input type="text" id="nick_name" name="nick_name" readonly>
        </fieldset>


        <hr>
        <%--선택 기재--%>

        <%--전화번호--%>
        <jsp:include page="/resources/layouts/DF01_layouts/DF01_phone.jsp"></jsp:include>

        <%--주소--%>
        <jsp:include page="/resources/layouts/DF01_layouts/DF01_address.jsp"></jsp:include>

        <%--성별--%>
        <jsp:include page="/resources/layouts/DF01_layouts/DF01_gender.jsp"></jsp:include>

        <%--생년월일--%>
        <jsp:include page="/resources/layouts/DF01_layouts/DF01_birthday.jsp"></jsp:include>

    </form>

    <a href="/members/delete" methods="get">회원탈퇴</a>

</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</body>
</html>
