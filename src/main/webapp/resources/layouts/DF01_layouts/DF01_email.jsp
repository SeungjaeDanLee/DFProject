<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../resources/css/DF01_member.css" rel="stylesheet"/>
<script src="../resources/js/DF01_member/DF0101_join.js"></script>
<fieldset>
    <div style="display: flex; align-items: center;">
        <div style="margin-right: 20px;">
            <label for="emailOrigin">이메일</label>
            <input type="text" id="emailOrigin" name="emailOrigin" placeholder="이메일을 입력해주세요"
                   oninput="updateEmail()" required>
        </div>
        <div>
            <label for="email_selection" class="form-label">이메일 선택창</label>
            <div style="display: flex; align-items: baseline;">
                <input type="text" id="emailService" name="emailService" placeholder="나머지 주소"
                       style="margin-right: 5%" onchange="handleEmailSelection()" oninput="updateEmail()" readonly required>
                <select class="form-select" id="email_selection" onchange="updateEmail()">
                    <option selected disabled value=""></option>
                    <option value="1">@naver.com</option>
                    <option value="2">@daum.net</option>
                    <option value="3">@gmail.com</option>
                    <option value="4">@epcot.co.kr</option>
                    <option value="5">직접입력</option>
                </select>
                <div class="invalid-feedback">
                    Please select a valid state.
                </div>
            </div>
        </div>
    </div>
    <input type="text" id="email" name="email" placeholder="이메일" oninput="updateAndCheckEmail()" readonly>
</fieldset>
<span id="result4"></span>