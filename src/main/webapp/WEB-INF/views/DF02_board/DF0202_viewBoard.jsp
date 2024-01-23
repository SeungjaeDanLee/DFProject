<html>
<head>
    <title>글읽기</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <link href="../resources/css/DF03_reply.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<div class="Select_all">
    <section>
        <ul>
            <li>
                <c:choose>
                    <c:when test="${board.category == 0}">공지글</c:when>
                    <c:when test="${board.category == 1}">자유글</c:when>
                    <c:when test="${board.category == 2}">정보글</c:when>
                    <c:otherwise>분류되지 않은 글</c:otherwise>
                </c:choose>
            </li>
            <li>
                <h2>${board.title}</h2>
            </li>
            <hr>
            ${board.content}
        </ul>
    </section>
</div>
    <div class="button-area" style="text-align: center;">
        <button type="button" onclick="location.href='<c:url value='/board/paging?page=${page}'></c:url>'">목록</button>
        <c:if test="${isBoardAuthor}">
            <button type="button" onclick="location.href='<c:url value='/board/update'><c:param name='bno' value='${board.bno}'/></c:url>'">수정</button>
            <button type="button" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/board/delete'><c:param name='bno' value='${board.bno}'/></c:url>';">삭제</button>
        </c:if>
    </div>
<hr>


<form action="/reply/write" method="post">
    <div class="Select_all">
        <section>
            <ul>
                <li>
                    <h3>댓글</h3>
                </li>
                <li>
                    <textarea type="text" name="content" placeholder="내용을 입력해주세요" class="reply_content_box" required></textarea>
                </li>
                <li style="margin-top: 10px; float: right">
                    <input style="width: 100px" type="submit" value="댓글 등록">
                </li>
                <input type="hidden" name="bno" value="${board.bno}">
            </ul>
        </section>
    </div>
</form>


<div id="replyList">

    <div class="Select_all">
        <section>
            <ul>
                <c:forEach items="${replyList}" var="reply">
                <li>작성자: ${reply.mno}</li>
                <li>
                    <textarea type="text" name="content" class="reply_content_box" readonly>${reply.content}</textarea>
                </li>
                <li style="text-align: right">작성시간:
                    <script>document.write(new Date('${reply.written_date}').toLocaleString('ko', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit',
                    hour: '2-digit',
                    minute: '2-digit'
                }));</script>
                </li>
                    <div class="reply-button-area" style="text-align: center;">
                        <c:if test="${isReplyAuthor}">
                            <button type="button" onclick="location.href='<c:url value='/reply/update'><c:param name='rno' value='${reply.rno}'/></c:url>'">수정</button>
                            <button type="button" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/reply/delete'><c:param name='rno' value='${reply.rno}'/></c:url>';">삭제</button>
                        </c:if>
                    </div>
                </c:forEach>

            </ul>
        </section>
    </div>

</div>

    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
    <script src="../resources/js/DF02_board/DF0202_viewBoard.js"></script>
    <script>
        $(document).ready(function(){
            $("form").on('submit', function(event){
                event.preventDefault(); // 폼 제출을 막아 페이지가 새로고침되는 것을 방지
                let formValues= $(this).serialize(); // 폼 데이터를 쿼리 문자열로 인코딩
                $.post("/reply/write", formValues, function(data){
                    // 댓글 작성이 완료된 후 실행할 코드
                    $('.reply_content_box').val('');  // textarea의 내용을 지움
                    $.ajax({
                        url: '/reply/' + '${board.bno}',  // 댓글 리스트를 가져오는 URL
                        type: 'get',
                        success: function(replyList) {  // 서버로부터 응답을 성공적으로 받았을 때 실행할 함수
                            $('#replyList').empty(); // 기존 댓글 목록을 비우기
                            $.each(replyList, function(index, reply) {
                                let row = `
                                    <div class="Select_all">
                                        <section>
                                            <ul>
                                                <li>작성자: ${reply.mno}</li>
                                                <li>
                                                    <textarea type="text" name="content" class="reply_content_box" readonly>${reply.content}</textarea>
                                                </li>
                                                <li style="text-align: right">작성시간: ${reply.written_date}</li>
                                                <div class="button-area" style="text-align: center;">
                                                    <button type="button">수정</button>
                                                    <button type="button">삭제</button>
                                                </div>
                                            </ul>
                                        </section>
                                    </div>
                                    `;
                                $('#replyList').append(row);
                            });
                        }
                    });
                });
            });
        });
    </script>
</body>
</html>
