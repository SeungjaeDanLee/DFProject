<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../resources/css/DF01_member.css" rel="stylesheet"/>
<script src="../resources/js/DF01_member/DF0101_join.js"></script>
<fieldset>
    <label>주소</label>
    <div class="form-group">
        <input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="zipcode" id="zipcode" type="text" value="${member.zipcode}" readonly="readonly" >
        <button type="button" class="btn btn-secondary" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>
    </div>
    <div class="form-group">
        <input class="form-control" style="top: 5px;" placeholder="도로명 주소" name="streetAddress" id="streetAddress" type="text" value="${member.streetAddress}" readonly="readonly" />
    </div>
    <div class="form-group">
        <input class="form-control" placeholder="상세주소" name="detailAddress" id="detailAddress" value="${member.detailAddress}" type="text"  />
    </div>
    <div class="form-group" hidden="hidden">
        <input class="form-control" placeholder="참고항목" name="extraAddress" id="extraAddress" type="text"  />
    </div>
</fieldset>
