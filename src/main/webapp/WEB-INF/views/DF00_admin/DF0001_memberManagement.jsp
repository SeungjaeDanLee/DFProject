<!DOCTYPE html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>회원 관리 페이지</title>

</head>

<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_adminNav.jsp"></jsp:include>
</header>

<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet"/>
<link href="../resources/css/DF00_admin.css" rel="stylesheet"/>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../resources/js/DF00_admin/DF0001_memberManagement.js"></script>


<body class="sb-nav-fixed">
<div id="layoutSidenav" style="height: 100%; overflow: scroll;">

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">회원 관리 페이지</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/">권한</a></li>
                    <li class="breadcrumb-item active">ADMIN</li>
                </ol>
                <div class="card mb-4">
                    <div class="card-body">
                        관리자에게만 권한이 부여된 페이지입니다. 관리자가 아닐 시 로그아웃 해주시길 바랍니다.
                    </div>

                </div>
                <div class="card mb-4">
                    <div class="card-header" style="font-weight: bold;color: white; background-color: #00c473;">
                        <i class="fas fa-table me-1"></i>
                        회원 관리 페이지
                    </div>
                    <div class="card-body">
                        <div style="display: flex">
                            <!--   이름검색기능   -->
                            <input type="text" id="searchInput" placeholder="이름or부서입력...">
                            <button type="button" id="searchBtn" class="btn btn-primary"
                                    style="width: 95px;border: 1px solid white;height: 38px;padding-top: 7px;background-color: #00c473;border-color: #00c473;color: white;border-radius: 5px;margin-left: 10px;">
                                검색
                            </button>
                        </div>
                        <table id="datatablesSimple">
                            <thead>
                            <tr>
                                <th>회원 번호</th>
                                <th>아이디</th>
                                <th>이메일</th>
                                <th>이름</th>
                                <th>닉네임</th>
                                <th>폰 번호</th>
<%--                                <th>지번</th>--%>
<%--                                <th>도로명</th>--%>
<%--                                <th>상세주소</th>--%>
                                <th>성별</th>
                                <th>생년월일</th>
                                <th>가입날짜</th>
                                <th>회원 상태</th>
                                <th>상태 수정</th>
                                <th>회원 삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${memberList}" var="member">
                                <tr>
                                    <td id="mno">${member.mno}</td>
                                    <td id="id">${member.id}</td>
                                    <td id="email">${member.email}</td>
                                    <td id="name">${member.name}</td>
                                    <td id="nick_name">${member.nick_name}</td>
                                    <td id="phone">${member.phone}</td>
<%--                                    <td id="zipcode">${member.zipcode}</td>--%>
<%--                                    <td id="streetAddress">${member.streetAddress}</td>--%>
<%--                                    <td id="detailAddress">${member.detailAddress}</td>--%>
                                    <td id="gender">${member.gender}</td>
                                    <td id="birthday">${member.birthday}</td>
                                    <td id="regdate">${member.regdate}</td>
                                    <td id="member_level">
                                        <c:choose>
                                            <c:when test="${member.member_level == 0}">관리자</c:when>
                                            <c:when test="${member.member_level == 1}">일반 회원</c:when>
                                            <c:otherwise>분류되지 않은 회원</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td id="member_update">
                                        <select id="pop" name="member_level">
                                            <option selected disabled value="${member.member_level}">
                                                <c:choose>
                                                    <c:when test="${member.member_level == 0}">관리자</c:when>
                                                    <c:when test="${member.member_level == 1}">일반 회원</c:when>
                                                    <c:otherwise>분류되지 않은 회원</c:otherwise>
                                                </c:choose>
                                            </option>
                                            <option value="0">관리자</option>
                                            <option value="1">일반 회원</option>
                                        </select>
                                        <button class="btn btn-outline-warning" onclick="updateMember('${member.mno}', document.getElementById('pop').value)">수정</button>
                                    </td>
                                    <td id="member_delete">
                                        <button class="btn btn-outline-danger" onclick="deleteMember('${member.mno}')">삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>

    </div>
</div>

</body>
<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
</html>
