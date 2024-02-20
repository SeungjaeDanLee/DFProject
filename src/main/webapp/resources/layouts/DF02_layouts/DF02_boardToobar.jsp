<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <option value="">사이즈</option>
            <option value="1">10px</option>
            <option value="2">13px</option>
            <option value="3">16px</option>
            <option value="4">18px</option>
            <option value="5">24px</option>
            <option value="6">32px</option>
            <option value="7">48px</option>
        </select>
        <select style="width: 12%; height: 10%;" id="select-font-name">
            <option value="">폰트</option>
            <option value="Black Han Sans">Black Han Sans</option>
            <option value="Noto Sans KR">Noto Sans</option>
            <option value="Nanum Gothic">Nanum Gothic</option>
            <option value="Nanum Myeongjo">Nanum Myeongjo</option>
            <option value="Nanum Pen Script">Nanum Pen Script</option>
        </select>
        <select style="width: 12%; height: 10%;" id="select-font-color">
            <option value="">색상</option>
            <option value="#000000">검정</option>
            <option value="#FFFFFF">흰색</option>
            <option value="#CCCCCC">회색</option>
            <option value="#F03E3E">빨강</option>
            <option value="#1971C2">파랑</option>
            <option value="#37B24D">녹색</option>
        </select>
        <select style="width: 12%; height: 10%;" id="select-font-background">
            <option value="rgba(0, 0, 0, 0)">백그라운드</option>
            <option value="#000000">검정</option>
            <option value="#FFFFFF">흰색</option>
            <option value="#CCCCCC">회색</option>
            <option value="#F03E3E">빨강</option>
            <option value="#1971C2">파랑</option>
            <option value="#37B24D">녹색</option>
        </select>
        <input id="img-selector" type="file" accept="image/*" />
    </li>
</ul>


<script>
    // jsp 텍스트 에디터 만들기
    const editor = document.getElementById("editor");
    const btnBold = document.getElementById("btn-bold");
    const btnItalic = document.getElementById("btn-italic");
    const btnUnderline = document.getElementById("btn-underline");
    const btnStrike = document.getElementById("btn-strike");
    const btnOrderedList = document.getElementById("btn-ordered-list");
    const btnUnorderedList = document.getElementById("btn-unordered-list");
    const selectFontColor = document.getElementById("select-font-color");
    const selectFontBackground = document.getElementById(
        "select-font-background"
    );
    const btnImage = document.getElementById("btn-image");
    const imageSelector = document.getElementById("img-selector");
    const fontSizeSelector = document.getElementById("select-font-size");
    const fontNameSelector = document.getElementById("select-font-name");

    const fontSizeList = [10, 13, 16, 18, 24, 32, 48];

    editor.addEventListener("keydown", function () {
        checkStyle();
    });

    editor.addEventListener("mousedown", function () {
        checkStyle();
    });

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

    selectFontColor.addEventListener("change", function () {
        setFontColor(this.value);
    });

    selectFontBackground.addEventListener("change", function () {
        setFontBackground(this.value);
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

    fontSizeSelector.addEventListener("change", function () {
        changeFontSize(this.value);
    });
    fontNameSelector.addEventListener("change", function () {
        changeFontName(this.value);
    });

    function insertImageDate(file) {
        const reader = new FileReader();
        reader.addEventListener("load", function (e) {
            focusEditor();
            document.execCommand("insertImage", false, `${reader.result}`);
        });
        reader.readAsDataURL(file);
    }

    function setFontColor(color) {
        document.execCommand("foreColor", false, color);
        focusEditor();
    }

    function setFontBackground(color) {
        document.execCommand("hiliteColor", false, color);
        focusEditor();
    }

    function setStyle(style) {
        document.execCommand(style);
        focusEditor();
        checkStyle();
    }

    function focusEditor() {
        editor.focus({ preventScroll: true });
    }

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

    function getComputedStyleProperty(el, propName) {
        if (window.getComputedStyle) {
            return window.getComputedStyle(el, null)[propName];
        } else if (el.currentStyle) {
            return el.currentStyle[propName];
        }
    }

    function changeFontName(name) {
        document.execCommand("fontName", false, name);
        focusEditor();
    }

    function changeFontSize(size) {
        document.execCommand("fontSize", false, size);
        focusEditor();
    }

    function isStyle(style) {
        return document.queryCommandState(style);
    }

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

    function componentToHex(c) {
        const hex = parseInt(c).toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    }

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