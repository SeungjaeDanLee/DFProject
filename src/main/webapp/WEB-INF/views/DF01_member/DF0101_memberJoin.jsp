<html>
<head>
    <title>회원가입</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="../resources/css/DF01_member.css" rel="stylesheet"/>
    <script src="../resources/js/DF01_member/DF0101_join.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

</head>
<body oncontextmenu="return false" ondragstart="return false" onselectstart="return false">

<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="register">
    <form class="register_form" action="/members/new" method="post">
        <div class="needed_things">
            <h3>회원가입</h3><br>
            <%--필수 기재--%>
            <h5 style="color: #ff1610">필수 기재</h5>
            <fieldset>
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" minlength="8" maxlength="20"
                       placeholder="아이디를 입력해주세요" oninput="checkId(this) || characterCheck(this)"
                       required>
<%--                <button class="btn btn-secondary" type="button" onclick="checkId()">확인</button>--%>
            </fieldset>
            <span id="result1"></span>

            <fieldset>
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" oninput="pwCheck() || characterCheck(this)" minlength="8" maxlength="20"
                       placeholder="비밀번호 8자~20자 특수기호 금지"
                       required>
            </fieldset>

            <fieldset>
                <label for="password">비밀번호 확인</label>
                <input type="password" id="password_check" name="password_check" oninput="pwCheck() || characterCheck(this)"
                       placeholder="비밀번호를 다시 입력해주세요" required>
            </fieldset>
            <span id="pwConfirm"></span>

            <%--이메일--%>
            <jsp:include page="/resources/layouts/DF01_layouts/DF01_email.jsp"></jsp:include>

            <fieldset>
                <label for="name">이름</label>
                <input type="text" id="name" name="name" placeholder="이름을 입력해주세요"
                       minlength="2" maxlength="10" oninput="checkName() || characterCheckName(this)" required>
            </fieldset>
            <span id="result3"></span>

            <fieldset>
                <label for="nick_name">닉네임</label>
                <input type="text" id="nick_name" name="nick_name" placeholder="닉네임을 입력해주세요"
                       oninput="checkNickName() || characterCheckNickName(this)"
                       minlength="2" maxlength="16" required>
<%--                <button class="btn btn-secondary" type="button" onclick="checkNickName()">확인</button>--%>
            </fieldset>
            <span id="result2"></span>
        </div>
        <div class="d-flex">
            <div class="vr"></div>
        </div>
        <div class="unneeded_things">
            <%--선택 기재--%>
            <br><br><br>
            <h5 style="color: #1924ff">선택 기재</h5>

            <%--전화번호--%>
            <jsp:include page="/resources/layouts/DF01_layouts/DF01_phone.jsp"></jsp:include>

            <%--주소--%>
            <jsp:include page="/resources/layouts/DF01_layouts/DF01_address.jsp"></jsp:include>

            <%--성별--%>
            <jsp:include page="/resources/layouts/DF01_layouts/DF01_gender.jsp"></jsp:include>

            <%--생년월일--%>
            <jsp:include page="/resources/layouts/DF01_layouts/DF01_birthday.jsp"></jsp:include>

            <input type="submit" value="회원가입" onclick="return checkForm(); ">

            <div class="float-end" style="margin-top: 5%">기존 회원이라면??
                <a href="/members/login" style="text-decoration-line: none;">로그인</a>
            </div>
        </div>
    </form>
</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</body>
</html>
