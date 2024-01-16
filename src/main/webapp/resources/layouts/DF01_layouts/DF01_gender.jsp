<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../resources/css/DF01_member.css" rel="stylesheet"/>
<script src="../resources/js/DF01_member.js"></script>
<fieldset>
    <div style="display: flex; align-items: center;">
        <div style="margin-right: 20px;">
            <label for="gender">성별</label>
            <input type="text" id="gender" name="gender" style="width: 100%; display: inline;" readonly>
        </div>
        <div>
            <label for="validationCustom04" class="form-label">선택창</label>
            <select class="form-select" id="validationCustom04">
                <option selected disabled value="">Choose...</option>
                <option value="1">남자</option>
                <option value="2">여자</option>
                <option value="3">직접입력</option>
            </select>
            <div class="invalid-feedback">
                Please select a valid state.
            </div>
        </div>
    </div>
</fieldset>
