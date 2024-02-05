<!DOCTYPE html>
<html lang="en">
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>DogFit</title>


</head>
<body>
<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
<!-- Header-->
<div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="5000">
            <header class="bg-dark py-5">
                <div class="container px-4 px-lg-5 my-5">
                    <div class="text-center text-white">
                        <h1 class="display-4 fw-bolder">Dog in your style</h1>
                        <hr>
                        <p class="lead fw-normal text-white-50 mb-0">당신의 반려견은 어떤 모습인가요</p>
                    </div>
                </div>
            </header>
        </div>
        <div class="carousel-item" data-bs-interval="5000">
            <header class="bg-primary py-5">
                <div class="container px-4 px-lg-5 my-5">
                    <div class="text-center text-white">
                        <h1 class="display-4 fw-bolder">Dog in your life</h1>
                        <hr>
                        <p class="lead fw-normal text-white-50 mb-0">당신은 어떤 반려견을 키우고 있나요??</p>
                    </div>
                </div>
            </header>
        </div>
        <div class="carousel-item" data-bs-interval="5000">
            <header class="bg-secondary py-5">
                <div class="container px-4 px-lg-5 my-5">
                    <div class="text-center text-white">
                        <h1 class="display-4 fw-bolder">Dog for your style</h1>
                        <hr>
                        <p class="lead fw-normal text-white-50 mb-0">반려견은 당신을 닮았나요??</p>
                    </div>
                </div>
            </header>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


<!-- Section-->
<section style="width: 60%; margin: 0 auto; margin-top: 50px;">
    <hr>
    <c:forEach items="${boardList}" var="board">
        <a href="/board?bno=${board.bno}" style="text-decoration: none">
<%--            <img src="../resources/assets/dogfoot.png" alt="개발자국" width="10%" height="10%">--%>
            <div style="text-align: center; margin: 10px; color: black;">
                <div style="display: flex; justify-content: space-between;">
                    <div>작성자 :
                        <c:forEach items="${memberList}" var="member">
                            <c:if test="${board.mno == member.mno}">
                                ${member.nick_name}
                            </c:if>
                        </c:forEach>
                    </div>
                    <div>작성시간 :
                        <script>document.write(new Date('${board.written_date}').toLocaleString({
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit',
                            hour: '2-digit',
                            minute: '2-digit'
                        }));</script>
                    </div>
                </div>
                <br>
                <div style="display: flex; justify-content: space-between;">
                    <div>${board.content}</div>
                    <div>
                        <c:choose>
                            <c:when test="${board.category == 0}">공지글</c:when>
                            <c:when test="${board.category == 1}">자유글</c:when>
                            <c:when test="${board.category == 2}">정보글</c:when>
                            <c:otherwise>분류되지 않은 글</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <br>
                <h6 style="display: flex; justify-content: right; align-items: center;">좋아요 ${board.like_counts} | 조회 ${board.view_counts}</h6>
            </div>
            <hr style="color: black;">
        </a>
    </c:forEach>
</section>


<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>

</body>
</html>
