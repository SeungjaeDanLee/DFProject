<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../resources/css/DF01_member.css" rel="stylesheet"/>
<script src="../resources/js/DF01_member/DF0101_join.js"></script>
<fieldset>
    <label for="phone">전화번호</label>
    <input type="text" id="phone" name="phone" oninput="autoHyphen(this)" maxlength="13" placeholder="-없이 숫자만 입력해주세요" value="${member.phone}">
</fieldset>
