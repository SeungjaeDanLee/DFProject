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
        <table>
            <tr>
                <th>아이디</th>
                <td>${member.id}</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${member.email}</td>
            </tr>
            <tr>
                <th>이름</th>
                <td>${member.name}</td>
            </tr>
            <tr>
                <th>닉네임</th>
                <td>${member.nick_name}</td>
            </tr>
            <tr>
                <th>폰</th>
                <td>${member.phone}</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>${member.zipcode}</td>
                <td>${member.streetAddress}</td>
                <td>${member.detailAddress}</td>
            </tr>
            <tr>
                <th>성별</th>
                <td>${member.gender}</td>
            </tr>
            <tr>
                <th>생년월일</th>
                <td>${member.birthday}</td>
            </tr>
        </table>

    <a href="/members/delete" methods="get">회원탈퇴</a>

</div>

<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</body>
</html>
