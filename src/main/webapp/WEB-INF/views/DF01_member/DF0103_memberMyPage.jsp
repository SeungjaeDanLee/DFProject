<html>
<head>
    <title>내 정보</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="../resources/css/DF01_member.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>
    <script src="../resources/js/DF01_member/DF0103_myPage.js"></script>

</head>
<body oncontextmenu="return false" ondragstart="return false" onselectstart="return false">

<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="mypage">
    <div class="register_form">
        <div class="needed_things">
            <h3>내 정보</h3><br>
            <%--필수 기재--%>
            <h5 style="color: #ff1610">필수 기재</h5>
            <fieldset>
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" value="${member.id}" readonly>
            </fieldset>

            <fieldset>
                <label for="email">이메일</label>
                <input type="text" id="email" name="email" value="${member.email}" readonly>
            </fieldset>

            <fieldset>
                <label for="name">이름</label>
                <input type="text" id="name" name="name" value="${member.name}" readonly>
            </fieldset>

            <fieldset>
                <label for="nick_name">닉네임</label>
                <input type="text" id="nick_name" name="nick_name" value="${member.nick_name}" readonly>
            </fieldset>

            <div class="float-end" style="margin-top: 23%">
                <a href="#" onclick="confirmDelete()">회원탈퇴</a>
            </div>

        </div>
        <div class="d-flex">
            <div class="vr"></div>
        </div>
        <div class="unneeded_things">
            <%--선택 기재--%>
            <br><br><br>
            <h5 style="color: #1924ff">선택 기재</h5>

            <fieldset>
                <label for="phone">전화번호</label>
                <input type="text" id="phone" name="phone" value="${member.phone}" readonly>
            </fieldset>

            <fieldset>
                <label>주소</label>
                <div class="form-group">
                    <input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="zipcode"
                           id="zipcode" type="text" value="${member.zipcode}" readonly="readonly" />
                </div>
                <div class="form-group">
                    <input class="form-control" style="top: 5px;" placeholder="도로명 주소" name="streetAddress"
                           id="streetAddress" type="text" value="${member.streetAddress}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <input class="form-control" placeholder="상세주소" name="detailAddress" id="detailAddress" type="text"  value="${member.detailAddress}" readonly="readonly"/>
                </div>
            </fieldset>

            <fieldset>
                <label for="gender">성별</label>
                <input type="text" id="gender" name="gender" value="${member.gender}" readonly>
            </fieldset>

            <fieldset>
                <label for="birthday">생년월일</label>
                <input type="text" id="birthday" name="birthday" value="${member.birthday}" readonly>
            </fieldset>

            <div class="float-end" style="margin-top: 5%">
                <a href="#" onclick="confirmUpdate()">회원정보수정</a>
            </div>

        </div>
    </div>
</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</body>
</html>
