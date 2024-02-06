<html>
<head>
    <title>글쓰기</title>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>

    <!-- Quill 라이브러리 추가 -->
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">


</head>
<body>
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>

<form action="/board/write" method="post" enctype="multipart/form-data">
    <div class="Select_all">
        <section>
            <ul>
                <li>
                    <h3>글 종류</h3>
                    <select id="pop" name="category" required>
                        <option selected disabled value="">선택</option>
                        <c:if test="${sessionScope.loginMemberLevel == 0}">
                            <option value="0">공지</option>
                        </c:if>
                        <option value="1">자유</option>
                        <option value="2">정보</option>
                    </select>
                </li>
            </ul>

            <ul>
                <li>
                    <h3><br>제목</h3>
                    <input type="text" name="title" placeholder="제목을 입력해주세요" class="title" required>
                </li>
            </ul>

            <ul>
                <li style="width: 800px;">
                    <!-- Quill 텍스트 에디터 -->
                    <div id="editor" style="height: 500px;"></div>
                    <input type="hidden" name="content" id="content" class="content_box" required/>
                </li>
            </ul>

            <ul>
                <li style="margin-top: 10px;">
                    <input type="submit" value="등록" style="width: 800px;">
                </li>
            </ul>
        </section>
    </div>
</form>


<jsp:include page="/resources/layouts/DF00_layouts/DF00_generalFooter.jsp"></jsp:include>
<!-- Quill 라이브러리 스크립트 추가 -->
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script>
    let quill = new Quill('#editor', {
        theme: 'snow',
        modules: {
            toolbar: [
                [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
                ['bold', 'italic', 'underline'],
                [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                ['link', 'image']
            ]
        }
    });

    // Quill에서 입력한 내용을 숨은 input 필드에 복사하여 전송
    let form = document.querySelector('form');
    form.onsubmit = function() {
        let contentInput = document.querySelector('#content');
        contentInput.value = quill.root.innerHTML;
    };

    // Quill 이미지 업로드 모듈 활성화
    quill.getModule('toolbar').addHandler('image', function() {
        let input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');
        input.click();

        input.onchange = function() {
            let file = input.files[0];
            let formData = new FormData();
            formData.append('image', file);

            // 이미지를 업로드하고 이미지의 URL을 Quill 에디터에 삽입
            fetch('/file/upload', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    const range = quill.getSelection();
                    quill.insertEmbed(range.index, 'image', data.imageUrl);
                })
                .catch(error => {
                    console.error('이미지 업로드 실패:', error);
                });
        };
    });

</script>
</body>
</html>
