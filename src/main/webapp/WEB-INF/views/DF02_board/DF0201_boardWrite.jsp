<html>
<head>
    <title>글쓰기</title>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic:wght@400;700;800&family=Nanum+Myeongjo:wght@400;700;800&family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
            rel="stylesheet"
    />
    <style>
        div#titleName {
            padding: 16px 24px;
            border: 1px solid #d6d6d6;
            border-radius: 4px;
            overflow-y: auto;
        }

        div#editor {
            padding: 16px 24px;
            border: 1px solid #d6d6d6;
            border-radius: 4px;
            overflow-y: auto;
        }

        div#editor img {
            max-width: 100%;
        }

        button.active {
            background-color: purple;
            color: #fff;
        }

        #img-selector {
            display: none;
        }
    </style>
</head>
<body oncontextmenu="return true" ondragstart="return true" onselectstart="return true">
<header>
    <jsp:include page="/resources/layouts/DF00_layouts/DF00_generalNav.jsp"></jsp:include>
</header>


<form action="/board/write" method="post" enctype="multipart/form-data" >
    <div class="Select_all">
        <section>

            <%-- 글 종류 --%>
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

            <%-- 글 제목 --%>
            <ul>
                <li>
                    <h3><br>제목</h3>
                    <div id="titleName" style="height: 50px; width: 800px;" contenteditable="true"></div>
                    <input type="hidden" name="title" id="title" placeholder="제목을 입력해주세요" class="title" required>
                    <span id="result1" style="color: red;"></span>
                </li>
            </ul>

            <%-- 에디터 툴바 --%>
            <ul>
                <li class="editor-menu">
                    <button type="button" id="btn-bold">
                        <b>B</b>
                    </button>
                    <button type="button" id="btn-italic">
                        <i>I</i>
                    </button>
                    <button type="button" id="btn-underline">
                        <u>U</u>
                    </button>
                    <button type="button" id="btn-strike">
                        <s>S</s>
                    </button>
                    <button type="button" id="btn-ordered-list">OL</button>
                    <button type="button" id="btn-unordered-list">UL</button>
                    <button type="button" id="btn-image">IMG</button>
                    <select style="width: 12%; height: 10%;" id="select-font-size">
                        <option value="">Size</option>
                        <option value="1">10px</option>
                        <option value="2">13px</option>
                        <option value="3">16px</option>
                        <option value="4">18px</option>
                        <option value="5">24px</option>
                        <option value="6">32px</option>
                        <option value="7">48px</option>
                    </select>
                    <select style="width: 12%; height: 10%;" id="select-font-name">
                        <option value="">Font</option>
                        <option value="Black Han Sans">Black Han Sans</option>
                        <option value="Noto Sans KR">Noto Sans</option>
                        <option value="Nanum Gothic">Nanum Gothic</option>
                        <option value="Nanum Myeongjo">Nanum Myeongjo</option>
                        <option value="Nanum Pen Script">Nanum Pen Script</option>
                    </select>
                    <select style="width: 12%; height: 10%;" id="select-font-color">
                        <option value="">Color</option>
                        <option value="#000000">검정</option>
                        <option value="#FFFFFF">흰색</option>
                        <option value="#CCCCCC">회색</option>
                        <option value="#F03E3E">빨강</option>
                        <option value="#1971C2">파랑</option>
                        <option value="#37B24D">녹색</option>
                    </select>
                    <select style="width: 12%; height: 10%;" id="select-font-background">
                        <option value="rgba(0, 0, 0, 0)">Back</option>
                        <option value="#000000">검정</option>
                        <option value="#FFFFFF">흰색</option>
                        <option value="#CCCCCC">회색</option>
                        <option value="#F03E3E">빨강</option>
                        <option value="#1971C2">파랑</option>
                        <option value="#37B24D">녹색</option>
                    </select>
                    <input id="img-selector" name="imageFileSize" type="file" accept="image/*"/>
                </li>
            </ul>

            <%-- 에디터 --%>
            <ul>
                <li>
                    <div id="editor" style="height: 500px; width: 800px;" contenteditable="true"></div>
                    <input type="hidden" name="content" id="content" class="content_box" required/>
                    <span id="result2" style="color: red;"></span>
                </li>
            </ul>

            <%-- 파일 --%>
            <ul>
                <li>
                    <input id="file" type="file" name="file">
                </li>
            </ul>
            <ul>
                <li>
                    <input type="hidden" name="fno" value="0">
                </li>
            </ul>

            <%-- 등록 --%>
            <ul>
                <li style="margin-top: 10px;">
                    <input type="submit" name="boardWrite" value="등록" style="width: 800px;">
                </li>
            </ul>
        </section>
    </div>
</form>

<script>
    // jsp 텍스트 에디터 만들기
    // 제목
    const titleName = document.getElementById("titleName");
    // 내용
    const editor = document.getElementById("editor");
    // 파일
    // const file = document.getElementById("file");

    // 제목 내 enter 금지
    document.getElementById('titleName').addEventListener('input', function () {
        this.textContent = this.textContent.replace(/\n/g, '');
    });

    // 제목과 내용 검사
    document.querySelector('form').addEventListener('submit', function(event) {
        // 제목과 내용 검사
        if (titleName.textContent.trim() === "") {
            document.getElementById("result1").textContent = "제목을 입력해주세요.";
            titleName.focus();
            event.preventDefault(); // 제출을 중지합니다.
            return false;
        }
        else if (editor.innerHTML.trim() === "") {
            document.getElementById("result2").textContent = "내용을 입력해주세요.";
            editor.focus();
            event.preventDefault(); // 제출을 중지합니다.
            return false;
        }
    });

    // 이미지 업로드
    function uploadImage(file) {
        const formData = new FormData();
        formData.append("uploadFile", file);

        $.ajax({
            type: "post",
            enctype: "multipart/form-data",
            url: "/file/imageUpload", // 이미지를 업로드할 서버 엔드포인트 URL
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                console.log("이미지 업로드 성공 : " + data);

                // 이미지를 삽입할 위치에 파일 경로를 포함하여 이미지 삽입
                insertImage(data.fileName, data.datePath);
            },
            error: function (err) {
                console.error("이미지 업로드 실패 : ", err.responseJSON.errorFat || err.responseJSON.errorBig || "알 수 없는 오류가 발생했습니다.");
                let errorMassage = err.responseJSON.errorFat || err.responseJSON.errorBig || "알 수 없는 오류가 발생했습니다.";
                alert("이미지 업로드 실패\n" + errorMassage);
            },
        });
    }

    // 이미지 보기
    function insertImage(fileName, datePath) {
        // 이미지 파일 경로
        const fileUrl = "/file/display?fileName=" + encodeURI(fileName) + "&datePath=" + encodeURI(datePath);

        // 에디터에 이미지 삽입
        const img = document.createElement("img");
        img.src = fileUrl;

        // 에디터의 현재 커서 위치에 이미지 삽입
        editor.focus(); // 에디터에 포커스를 맞춤
        const selection = window.getSelection();
        if (selection.getRangeAt && selection.rangeCount) {
            const range = selection.getRangeAt(0);
            range.collapse(false); // 커서를 현재 선택 영역의 끝으로 이동
            range.insertNode(img); // 이미지를 삽입
        }
    }

    // 파일 업로드
    // 허용된 파일 확장자 목록
    const whiteList = ['hwp', 'txt'];

    // 파일 업로드 용량 제한
    $("input[name=file]").on("change", function(){
        let maxSize = 10 * 1024 * 1024; //* 10MB 사이즈 제한
        let fileSize = this.files[0].size; //업로드한 파일용량
        let fileName = this.files[0].name; // 업로드한 파일 이름
        let fileExtension = fileName.split('.').pop().toLowerCase(); // 파일 확장자 추출
        console.log(file)

        if(fileSize > maxSize){
            alert("파일첨부 사이즈는 10MB 이내로 가능합니다.");
            $(this).val(''); //업로드한 파일 제거
        }

        // 확장자 검사
        if (!whiteList.includes(fileExtension)) {
            alert("허용되지 않는 파일 형식입니다.");
            $(this).val(''); // 업로드한 파일 제거
        }
    });

    // 등록 시 서버로 데이터 넘김
    document.querySelector('input[type="submit"]').addEventListener('click', function (event) {
        // 기본 제출 동작 중지
        event.preventDefault();

        // 입력한 내용을 숨은 input 필드에 복사하여 전송 제목 + 내용
        let titleInput = document.querySelector('#title');
        titleInput.value = titleName.innerHTML;

        let contentInput = document.querySelector('#content');
        contentInput.value = editor.innerHTML;

        // 파일 input 요소에서 선택된 파일 추가
        let fileInput = document.getElementById('file');
        if (fileInput.files.length > 0) {
            // FormData 객체 생성
            let formData = new FormData();
            formData.append('file', fileInput.files[0]);

            // Ajax 요청 생성
            let xhr = new XMLHttpRequest();
            xhr.open('POST', '/file/fileUpload');
            xhr.onload = function () {
                if (xhr.status === 200) {
                    // xhr.response 값을 input의 value에 설정
                    document.querySelector('input[name="fno"]').value = xhr.response;

                    // 응답 받은 후 form 제출
                    document.querySelector('form').submit();
                } else if (xhr.status === 204) {
                    alert('해당 파일을 업로드할 수 없습니다. (hwp, txt 파일만 가능)');
                }
                else {
                    // 서버 응답을 받지 못했을 때 처리할 코드 작성
                    console.error('파일 업로드에 실패했습니다.');
                    alert('파일 업로드에 실패했습니다.');
                }
            };
            // Ajax 요청 전송
            xhr.send(formData);
        } else {
            // 파일이 없을 경우 form 바로 제출
            document.querySelector('form').submit();
        }
    });


    // document.querySelector('input[type="submit"]').addEventListener('click', function (event) {
    //     // 기본 제출 동작 중지
    //     event.preventDefault();
    //
    //     // 입력한 내용을 숨은 input 필드에 복사하여 전송 제목 + 내용
    //     let titleInput = document.querySelector('#title');
    //     titleInput.value = titleName.innerHTML;
    //
    //     let contentInput = document.querySelector('#content');
    //     contentInput.value = editor.innerHTML;
    //
    //     // FormData 객체 생성
    //     let formData = new FormData();
    //
    //     // 파일 input 요소에서 선택된 파일 추가
    //     let fileInput = document.getElementById('file');
    //     formData.append('file', fileInput.files[0]);
    //
    //     // Ajax 요청 생성
    //     let xhr = new XMLHttpRequest();
    //     xhr.open('POST', '/file/fileUpload');
    //     xhr.onload = function () {
    //         if (xhr.status === 200) {
    //             // xhr.response 값을 input의 value에 설정
    //             document.querySelector('input[name="fno"]').value = xhr.response;
    //
    //             // 응답 받은 후 form 제출
    //             document.querySelector('form').submit();
    //         } else if (xhr.status === 204) {
    //             alert('해당 파일을 업로드할 수 없습니다. (hwp, txt 파일만 가능)');
    //         }
    //         else {
    //             // 서버 응답을 받지 못했을 때 처리할 코드 작성
    //             console.error('파일 업로드에 실패했습니다.');
    //             alert('파일 업로드에 실패했습니다.');
    //         }
    //     };
    //     // Ajax 요청 전송
    //     xhr.send(formData);
    // });

    // form 제출 이벤트 리스너 등록
    // document.getElementById('file').addEventListener('change', function(event) {
    //     const file = event.target.files[0];
    //     const formData = new FormData();
    //     formData.append('file', file);
    //
    //     fetch('/file/fileUpload', {
    //         method: 'POST',
    //         body: formData
    //     })
    //         .then(response => {
    //             if (response.ok) {
    //                 console.log('파일이 성공적으로 업로드되었습니다.');
    //             } else {
    //                 console.error('파일 업로드에 실패했습니다.');
    //             }
    //         })
    //         .catch(error => {
    //             console.error('파일 업로드에 실패했습니다.', error);
    //         });
    // });

    // document.querySelector('form').addEventListener('submit', function (event) {
    //     // 기본 제출 동작 중지
    //     // event.preventDefault();
    //
    //     // FormData 객체 생성
    //     let formData = new FormData();
    //
    //     // 파일 input 요소에서 선택된 파일 추가
    //     let fileInput = document.getElementById('file');
    //     formData.append('file', fileInput.files[0]);
    //
    //     // Ajax 요청 생성
    //     let xhr = new XMLHttpRequest();
    //     xhr.open('POST', '/file/fileUpload');
    //     xhr.onload = function () {
    //         if (xhr.status === 200) {
    //             console.log(xhr.response);
    //         //     xhr.response == fno
    //             // xhr.response 값을 input의 value에 설정
    //             document.querySelector('input[name="fno"]').value = xhr.response;
    //             // console.log(document.querySelector('input[name="fno"]').value);
    //         } else {
    //             // 서버 응답을 받지 못했을 때 처리할 코드 작성
    //             console.error('파일 업로드에 실패했습니다.');
    //             alert('파일 업로드에 실패했습니다.');
    //             event.preventDefault();
    //         }
    //     };
    //     // Ajax 요청 전송
    //     xhr.send(formData);
    // });

    // // 입력한 내용을 숨은 input 필드에 복사하여 전송 제목 + 내용
    // let form = document.querySelector('form');
    // form.onsubmit = function () {
    //     let titleInput = document.querySelector('#title');
    //     titleInput.value = titleName.innerHTML;
    //
    //     let contentInput = document.querySelector('#content');
    //     contentInput.value = editor.innerHTML;
    // };

    // ==========================여기서부터는 텍스트 에디터 설정==========================
    // 텍스트 에디터와 툴바에 관한 설정
    // 폰트
    // 폰트 필체
    const btnBold = document.getElementById("btn-bold");
    const btnItalic = document.getElementById("btn-italic");
    const btnUnderline = document.getElementById("btn-underline");
    const btnStrike = document.getElementById("btn-strike");
    const btnOrderedList = document.getElementById("btn-ordered-list");
    const btnUnorderedList = document.getElementById("btn-unordered-list");

    // 폰트 색상
    const selectFontColor = document.getElementById("select-font-color");
    const selectFontBackground = document.getElementById(
        "select-font-background"
    );

    // 폰트 사이즈
    const fontSizeSelector = document.getElementById("select-font-size");
    const fontNameSelector = document.getElementById("select-font-name");
    const fontSizeList = [10, 13, 16, 18, 24, 32, 48];

    // 이미지
    const btnImage = document.getElementById("btn-image");
    const imageSelector = document.getElementById("img-selector");

    // 내용
    editor.addEventListener("keydown", function () {
        checkStyle();
    });

    editor.addEventListener("mousedown", function () {
        checkStyle();
    });

    // 필체
    btnBold.addEventListener("click", function () {
        setStyle("bold");
    });

    btnItalic.addEventListener("click", function () {
        setStyle("italic");
    });

    btnUnderline.addEventListener("click", function () {
        setStyle("underline");
    });

    btnStrike.addEventListener("click", function () {
        setStyle("strikeThrough");
    });

    btnOrderedList.addEventListener("click", function () {
        setStyle("insertOrderedList");
    });

    btnUnorderedList.addEventListener("click", function () {
        setStyle("insertUnorderedList");
    });

    // 폰트 색상 및 사이즈
    selectFontColor.addEventListener("change", function () {
        setFontColor(this.value);
    });

    selectFontBackground.addEventListener("change", function () {
        setFontBackground(this.value);
    });

    fontSizeSelector.addEventListener("change", function () {
        changeFontSize(this.value);
    });

    fontNameSelector.addEventListener("change", function () {
        changeFontName(this.value);
    });

    // 이미지 업로드 모듈 활성화
    btnImage.addEventListener("click", function () {
        imageSelector.click();
    });

    imageSelector.addEventListener("change", function (e) {
        const files = e.target.files;
        if (!!files) {
            uploadImage(files[0]);
        }
    });

    // FileReader를 사용하여 이미지 파일을 읽고 에디터에 이미지를 삽입
    function insertImageDate(file) {
        const reader = new FileReader();
        reader.addEventListener("load", function (e) {
            focusEditor();
            document.execCommand("insertImage", false, `${reader.result}`);
        });
        reader.readAsDataURL(file);
    }

    // 폰트 색상 변경
    function setFontColor(color) {
        document.execCommand("foreColor", false, color);
        focusEditor();
    }

    // 스타일
    function setFontBackground(color) {
        document.execCommand("hiliteColor", false, color);
        focusEditor();
    }

    function setStyle(style) {
        document.execCommand(style);
        focusEditor();
        checkStyle();
    }

    // 내용 에디터에 포커스
    function focusEditor() {
        editor.focus({preventScroll: true});
    }

    // 필체 선택 함수
    function checkStyle() {
        if (isStyle("bold")) {
            btnBold.classList.add("active");
        } else {
            btnBold.classList.remove("active");
        }
        if (isStyle("italic")) {
            btnItalic.classList.add("active");
        } else {
            btnItalic.classList.remove("active");
        }
        if (isStyle("underline")) {
            btnUnderline.classList.add("active");
        } else {
            btnUnderline.classList.remove("active");
        }
        if (isStyle("strikeThrough")) {
            btnStrike.classList.add("active");
        } else {
            btnStrike.classList.remove("active");
        }
        if (isStyle("insertOrderedList")) {
            btnOrderedList.classList.add("active");
        } else {
            btnOrderedList.classList.remove("active");
        }
        if (isStyle("insertUnorderedList")) {
            btnUnorderedList.classList.add("active");
        } else {
            btnUnorderedList.classList.remove("active");
        }

        reportFont();
    }

    // 지정된 요소의 계산된 스타일 속성 값 가져오기
    function getComputedStyleProperty(el, propName) {
        if (window.getComputedStyle) {
            return window.getComputedStyle(el, null)[propName];
        } else if (el.currentStyle) {
            return el.currentStyle[propName];
        }
    }

    // 폰트 이름을 변경
    function changeFontName(name) {
        document.execCommand("fontName", false, name);
        focusEditor();
    }

    // 폰트 사이즈를 변경
    function changeFontSize(size) {
        document.execCommand("fontSize", false, size);
        focusEditor();
    }

    // 지정된 스타일이 적용되어 있는지 확인
    function isStyle(style) {
        return document.queryCommandState(style);
    }

    // 현재 선택된 텍스트의 폰트 설정
    function reportFont() {
        let containerEl, sel;
        if (window.getSelection) {
            sel = window.getSelection();
            if (sel.rangeCount) {
                containerEl = sel.getRangeAt(0).commonAncestorContainer;
                if (containerEl.nodeType === 3) {
                    containerEl = containerEl.parentNode;
                }
            }
        } else if ((sel = document.selection) && sel.type !== "Control") {
            containerEl = sel.createRange().parentElement();
        }

        if (containerEl) {
            const fontSize = getComputedStyleProperty(containerEl, "fontSize");
            const fontName = getComputedStyleProperty(containerEl, "fontFamily");
            const fontColor = getComputedStyleProperty(containerEl, "color");
            const backgroundColor = getComputedStyleProperty(
                containerEl,
                "backgroundColor"
            );
            const size = parseInt(fontSize.replace("px", ""));
            fontSizeSelector.value = fontSizeList.indexOf(size) + 1;
            // fontName이 문자열 "폰트명"으로 오기 때문에 "를 제거해주는 코드 추가
            fontNameSelector.value = fontName.replaceAll('"', "");
            fontColorSelector.value = rgbToHex(fontColor).toUpperCase();
            // 기본 배경색은 rgba(0, 0, 0, 0)
            if (backgroundColor === "rgba(0, 0, 0, 0)") {
                fontBackgroundSelector.value = backgroundColor;
            } else {
                fontBackgroundSelector.value =
                    rgbToHex(backgroundColor).toUpperCase();
            }
        }
    }

    // 10진수를 16진수로 변환
    function componentToHex(c) {
        const hex = parseInt(c).toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    }

    // RGB 색상 값을 16진수 색상 값으로 변환
    function rgbToHex(color) {
        // rgb(r, g, b)에서 색상값만 뽑아 내기 위해서 rgb() 제거
        const temp = color.replace(/[^0-9,]/g, "");
        // r,g,b만 남은 값을 ,로 [r,g,b] 배열로 변환
        const rgb = temp.split(",");
        return (
            "#" +
            componentToHex(rgb[0]) +
            componentToHex(rgb[1]) +
            componentToHex(rgb[2])
        );
    }
</script>
</body>
</html>