<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Text Editor</title>
    <!-- Quill 라이브러리 추가 -->
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
</head>
<body>

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
                <li>
                    <!-- Quill 텍스트 에디터 -->
                    <div id="editor" style="height: 300px;"></div>
                    <input type="hidden" name="content" id="content" />
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
</script>
</body>
</html>
