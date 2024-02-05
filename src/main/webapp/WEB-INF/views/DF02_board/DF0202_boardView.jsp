<html>
<head>
    <title>글읽기</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/style.css" rel="stylesheet"/>
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
<%--            <li style="display: flex; justify-content: space-between; align-items: center;">--%>
            <li>
                <h2>${board.title}</h2>
            </li>

            <li style="display: flex; justify-content: right; align-items: center;">

                <c:if test="${!isLiked}">
                    <c:if test="${board.mno != loginMno}">
                        <button type="button" class="btn btn-outline-light like-btn" style="width: 100px" data-bno="${board.bno}">
                            <h6>좋아요</h6>
                            <img src="/resources/assets/black_heart.png" alt="검은 하트" width="33%" height="33%">
                        </button>
                    </c:if>
                </c:if>
                <c:if test="${isLiked}">
                    <button type="button" class="btn btn-outline-light like-btn" style="width: 100px" data-bno="${board.bno}">
                        <h6>좋아요</h6>
                        <img src="/resources/assets/red_heart.png" alt="빨간 하트" width="33%" height="33%">
                    </button>
                </c:if>
            </li>
            <h6 style="display: flex; justify-content: right; align-items: center;">좋아요 ${board.like_counts} | 조회 ${board.view_counts}</h6>
            <script>
                // 좋아요 버튼 클릭 이벤트 처리
                $('button.like-btn').click(function() {
                    let bno = $(this).data('bno'); // 게시물 번호 가져오기
                    // console.log(bno);
                    let liked = $(this).hasClass('liked'); // 좋아요 여부 확인
                    // console.log(liked)
                    $.ajax({
                        url: '/like/toggle',
                        method: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ bno: bno, liked: liked }), // 게시물 번호와 좋아요 여부 전송
                        success: function(response) {
                            if (response === 'liked') {
                                // 좋아요 추가 시 처리
                                $(this).addClass('liked');
                                // 좋아요 개수 증가 등 원하는 동작 수행
                                location.reload();
                            } else {
                                // 좋아요 삭제 시 처리
                                $(this).removeClass('liked');
                                // 좋아요 개수 감소 등 원하는 동작 수행
                                location.reload();
                            }
                        }
                    });
                });
            </script>

            <hr>
            ${board.content}
        </ul>


    </section>
</div>
    <div class="button-area" style="text-align: center;">
        <button class="btn btn-outline-primary" type="button" onclick="location.href='<c:url value='/board/paging?page=${page}'></c:url>'">목록</button>
        <c:if test="${isBoardAuthor || sessionScope.loginMemberLevel == 0}">
            <button class="btn btn-outline-warning" type="button" onclick="location.href='<c:url value='/board/update'><c:param name='bno' value='${board.bno}'/></c:url>'">수정</button>
            <button class="btn btn-outline-danger" type="button" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/board/delete'><c:param name='bno' value='${board.bno}'/></c:url>';">삭제</button>
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
    <c:forEach items="${replyList}" var="reply">
        <div style="text-align: center; margin: 10px;">
            <div style="display: flex; justify-content: space-between;">
                <div>작성자 :
                    <c:forEach items="${memberList}" var="member">
                        <c:if test="${reply.mno == member.mno}">
                            ${member.nick_name}
                        </c:if>
                    </c:forEach>
                </div>
                <div>작성시간 :
                    <script>document.write(new Date('${reply.written_date}').toLocaleString({
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit'
                    }));</script>
                </div>
            </div>
            <br>
            <div style="float: left">${reply.content}</div>
            <br>
            <div style="display: flex; justify-content: end;">
                <c:if test="${reply.mno == loginMno  || sessionScope.loginMemberLevel == 0}">
                    <button class="btn-outline-warning" type="button" onclick="openEditModal('${reply.rno}', '${reply.content}')">수정</button>
                    <button class="btn-outline-danger" type="button" onclick="if(confirm('정말 삭제하시겠습니까?')) location.href='<c:url value='/reply/delete'><c:param name='bno' value='${board.bno}'/><c:param name='rno' value='${reply.rno}'/></c:url>';">삭제</button>
                </c:if>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>

<!-- 댓글 수정 팝업 -->
<div id="editModal">
    <h2>댓글 수정</h2>
    <!-- editedReplyId 요소 추가 -->
    <input type="hidden" id="editedReplyId" value="">

    <textarea id="editedContent" placeholder="내용을 입력해주세요"></textarea>

    <button class="btn-outline-primary" type="button" onclick="saveEdit()">저장</button>
    <button class="btn-outline-danger" type="button" onclick="closeEditModal()">닫기</button>
</div>


<div class="overlay" onclick="closeEditModal()"></div>


    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
    <script src="../resources/js/DF02_board/DF0202_viewBoard.js"></script>
    <script>
        <%--$(document).ready(function(){--%>
        <%--    $("form").on('submit', function(event){--%>
        <%--        event.preventDefault(); // 폼 제출을 막아 페이지가 새로고침되는 것을 방지--%>
        <%--        let formValues= $(this).serialize(); // 폼 데이터를 쿼리 문자열로 인코딩--%>
        <%--        $.post("/reply/write", formValues, function(data){--%>
        <%--            // 댓글 작성이 완료된 후 실행할 코드--%>
        <%--            $('.reply_content_box').val('');  // textarea의 내용을 지움--%>
        <%--            $.ajax({--%>
        <%--                url: '/reply/' + '${board.bno}',  // 댓글 리스트를 가져오는 URL--%>
        <%--                type: 'get',--%>
        <%--                success: function(replyList) {--%>
        <%--                    $('#replyList').empty(); // 기존 내용을 비우기--%>

        <%--                    $.each(replyList, function(index, reply) {--%>
        <%--                        let writtenDate = new Date(reply.written_date); // 작성시간을 Date 객체로 변환--%>
        <%--                        let year = writtenDate.getFullYear();--%>
        <%--                        let month = (writtenDate.getMonth() + 1).toString().padStart(2, '0');--%>
        <%--                        let day = writtenDate.getDate().toString().padStart(2, '0');--%>
        <%--                        let hours = writtenDate.getHours().toString().padStart(2, '0');--%>
        <%--                        let minutes = writtenDate.getMinutes().toString().padStart(2, '0');--%>
        <%--                        let seconds = writtenDate.getSeconds().toString().padStart(2, '0');--%>

        <%--                        let div = '<div style="text-align: center; margin: 10px;">' +--%>
        <%--                            '<div>작성자 ' + reply.mno + '</div>' +--%>
        <%--                            '<div>내용 ' + reply.content + '</div>' +--%>
        <%--                            '<div>작성시간 ' + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds + '</div>' +--%>
        <%--                            '</div>' + '<hr>';--%>
        <%--                        $('#replyList').append(div); // 새로운 내용 추가--%>
        <%--                    });--%>
        <%--                }--%>

        <%--            });--%>
        <%--        });--%>
        <%--    });--%>
        <%--});--%>

        $(document).ready(function(){
            $("form").on("submit", function(event){
                event.preventDefault(); // 폼 제출을 막아 페이지가 새로고침되는 것을 방지
                let formValues = $(this).serialize(); // 폼 데이터를 쿼리 문자열로 인코딩
                $.post("/reply/write", formValues, function(data){
                    // 댓글 작성이 완료된 후 페이지를 리로드
                    location.reload();
                });
                location.reload();
            });
        });

        function openEditModal(rno, content) {
            // 현재 수정 중인 댓글의 ID를 저장
            document.getElementById('editedReplyId').value = rno;

            // 댓글 ID와 내용을 기반으로 수정할 내용을 팝업에 표시
            document.getElementById('editedContent').value = content;

            document.getElementById('editModal').style.display = 'block';
            document.querySelector('.overlay').style.display = '';
        }

        function saveEdit() {
            // 수정된 내용을 서버로 전송하고 댓글 업데이트 로직 추가
            let rno = document.getElementById('editedReplyId').value;
            // let bno = document.getElementById('boardId').value;
            let content = document.getElementById('editedContent').value;

            // 서버로 수정된 내용과 댓글 ID 전송 및 업데이트 로직 추가(AJAX)
            $.ajax({
                type: 'POST',
                url: '/reply/update',
                data: {
                    rno: rno,
                    content: content
                },
                success: function (response) {

                    // 팝업 닫기
                    closeEditModal();

                    // 페이지 리로드
                    location.reload();


                    // 업데이트된 댓글 목록을 가져와서 화면에 반영
                    <%--$.ajax({--%>
                    <%--    url: '/reply/' + '${board.bno}',--%>
                    <%--    type: 'get',--%>
                    <%--    success: function (updatedReplyList) {--%>
                    <%--        $('#replyList').empty(); // 기존 내용을 비우기--%>

                    <%--        $.each(updatedReplyList, function (index, updatedReply) {--%>
                    <%--            let writtenDate = new Date(updatedReply.written_date);--%>
                    <%--            let year = writtenDate.getFullYear();--%>
                    <%--            let month = (writtenDate.getMonth() + 1).toString().padStart(2, '0');--%>
                    <%--            let day = writtenDate.getDate().toString().padStart(2, '0');--%>
                    <%--            let hours = writtenDate.getHours().toString().padStart(2, '0');--%>
                    <%--            let minutes = writtenDate.getMinutes().toString().padStart(2, '0');--%>
                    <%--            let seconds = writtenDate.getSeconds().toString().padStart(2, '0');--%>

                    <%--            let div = '<div style="text-align: center; margin: 10px;">' +--%>
                    <%--                '<div>작성자 ' + updatedReply.mno + '</div>' +--%>
                    <%--                '<div>내용 ' + updatedReply.content + '</div>' +--%>
                    <%--                '<div>작성시간 ' + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds + '</div>' +--%>
                    <%--                '</div>' + '<hr>';--%>
                    <%--            $('#replyList').append(div); // 새로운 내용 추가--%>
                    <%--        });--%>
                    <%--    }--%>
                    <%--});--%>
                },
                error: function (error) {
                    // 수정이 실패하면 에러 처리

                    // 팝업 닫기
                    closeEditModal();
                }
            });

            // 팝업 닫기
            closeEditModal();

            // 페이지 리로드
            location.reload();
        }

        function closeEditModal() {
            document.getElementById('editModal').style.display = 'none';
            document.querySelector('.overlay').style.display = 'none';
        }

    </script>
</body>
</html>
