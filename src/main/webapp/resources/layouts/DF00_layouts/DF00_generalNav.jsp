<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<%--    <link rel="icon" type="image/x-icon" href="../../assets/favicon.ico"/>--%>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/resources/css/styles.css" rel="stylesheet"/>

<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container px-4 px-lg-5">

            <a class="navbar-brand" href="/">
                <img src="/resources/assets/dogfoot.png" alt="개발자국" width="10%" height="10%">
                DogFit
            </a>


            <c:choose>
                <c:when test="${sessionScope.loginId == null}">
<%--                    <div class="collapse navbar-collapse" id="navbarSupportedContent">--%>
<%--                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">--%>
<%--                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="/board/paging">게시판</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
                    <form class="ms-auto mb-2 mb-lg-0" action="/members/login" method="get">
                        <button class="btn btn-outline-dark" type="submit">
                            <i></i>
                            로그인
                        </button>
                    </form>
                </c:when>
                <c:otherwise>

<%--                    ${sessionScope.loginNickName}님 반갑습니다.--%>
<%--                    회원님의 멤버등급은 ${sessionScope.loginMemberLevel}입니다.--%>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
                    </button>
                    ${sessionScope.loginMno}
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <c:if test="${sessionScope.loginMemberLevel == 0}">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="/admin/home">
                                        관리자 페이지
                                    </a>
                                </li>
                            </c:if>
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="/board/paging">게시판</a>
                            </li>
                            <li class="nav-item"><a class="nav-link" href="/board/write">글쓰기</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">마이 페이지</a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/members/my">회원정보</a></li>
                                    <li>
                                        <hr class="dropdown-divider"/>
                                    </li>
                                    <li><a class="dropdown-item" href="/board/paging/myBoard">내가 쓴 글</a></li>
                                    <li><a class="dropdown-item" href="/board/paging/myLike">내가 좋아하는 글</a></li>
                                    <li><a class="dropdown-item" href="/members/logout">로그아웃</a></li>
                                    <%--<li><a class="dropdown-item" href="<%session.invalidate();%>">로그아웃</a></li>--%>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
    <div></div>


    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="/resources/js/scripts.js"></script>
</head>
<body oncontextmenu="return false" ondragstart="return false" onselectstart="return false">
</body>
