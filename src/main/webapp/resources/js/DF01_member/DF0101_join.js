// 회원가입
let isCheckId = false;
let isCheckNickName = false;

// 회원가입 폼
function checkForm() {
    const id = $("[name=id]");
    const pw = $("[name=password]");
    const pwck = $("[name=password_check]");
    const email = $("[name=email]");
    const name = $("[name=name]");
    const nickname = $("[name=nick_name]");

    // const phone = $("[name=phone]");

    // const phoneNumber = phone.val().replace(/[^0-9]/g, '');
    // const address = $("[name=address]");
    // const gender = $("[name=gender]");
    // const birthday = $("[name=birthday]");


    if (id.val() === "") {
        alert("아이디를 입력해주세요.");
        id.focus();
        return false;
    } else if (pw.val() === "") {
        alert("비밀번호를 입력해주세요.");
        pw.focus();
        return false;
    } else if (pw.val() !== pwck.val()) {
        alert("비밀번호가 일치하지 않습니다.");
        pwck.focus();
        return false;
    } else if (email.val() === "") {
        alert("이메일을 입력해주세요.");
        email.focus();
        return false;
    } else if (name.val() === "") {
        alert("이름을 입력해주세요.");
        name.focus();
        return false;
    } else if (nickname.val() === "") {
        alert("닉네임을 입력해주세요.");
        nickname.focus();
        return false;
    }
    // else if(phone.val() === "") {
    //     alert("전화번호를 입력해주세요.");
    //     phone.focus();
    //     return false;
    // } else if (phoneNumber.length !== 11) {
    //     alert("전화번호를 숫자 11자리로 입력해주세요.");
    //     phone.focus();
    //     return false;
    // }

    if (!isCheckId) {
        alert("아이디 중복체크를 확인해주세요.");
        id.focus();
        return false;
    }
    if (!isCheckNickName) {
        alert("닉네임 중복체크를 확인해주세요.");
        nickname.focus();
        return false;
    }

    $("[name=member]").submit();
}

function pwCheck(){
    if ($('#password').val() != "") {
        if($('#password').val() == $('#password_check').val()){
            $('#pwConfirm').text('비밀번호 일치').css('color', 'blue')
        }else{
            $('#pwConfirm').text('비밀번호 불일치').css('color', 'red')
        }
    } else {
        $('#pwConfirm').text('비밀번호를 입력하세요').css('color', 'black')
    }
}

// 회원가입시 아이디 중복 확인
function checkId() {
    const inputId = $("[name=id]").val();
    console.log(inputId);
    $.ajax({
        type: "post",
        url: "/members/checkId",
        data: {"inputId": inputId},
        success: function (data) {
            console.log(data);
            if (inputId != "") {
                if (data == 'ok') {
                    $("#result1").text("사용 가능한 아이디입니다.").css("color", "blue");
                } else {
                    $("#result1").text("사용할 수 없는 아이디입니다.").css("color", "red");
                }
                isCheckId = true;
            } else {
                $("#result1").text("빈 값은 사용할 수 없습니다.").css("color", "red");
            }

        }, error: function () {
            console.log("서버 요청 실패");
            isCheckId = false;
        }
    });
}

// 회원가입시 닉네임 중복 확인
function checkNickName() {
    const inputNickName = $("[name=nick_name]").val();
    console.log(inputNickName);
    $.ajax({
        type: "post",
        url: "/members/checkNickName",
        data: {"inputNickName": inputNickName},
        success: function (data) {
            console.log(data);
            if (inputNickName != "") {
                if (data == 'ok') {
                    $("#result2").text("사용 가능한 닉네임입니다.").css("color", "blue");
                } else {
                    $("#result2").text("사용할 수 없는 닉네임입니다.").css("color", "red");
                }
                isCheckNickName = true;
            } else {
                $("#result2").text("빈 값은 사용할 수 없습니다.").css("color", "red");
            }

        }, error: function () {
            console.log("서버 요청 실패");
            isCheckNickName = false;
        }
    });
}

// 특수문자 입력 방지
// 일반
function characterCheck(obj){
    let regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
// 허용할 특수문자는 여기서 삭제
// 띄어쓰기도 특수문자 처리
    if( regExp.test(obj.value) ){
        alert("특수문자는 입력하실수 없습니다.");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
    }
}

// 이메일
function characterCheckEmail(obj){
    let regExp = /[ \{\}\[\]\/?,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\\(\=가-힣]/gi;
// 허용할 특수문자는 여기서 삭제
// @.만 허용
// 띄어쓰기도 특수문자 처리
    if( regExp.test(obj.value) ){
        alert("특수문자와 한글은 입력하실수 없습니다.");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
    }
}

// 이름(한글만)
function characterCheckName(obj){
    let regExp = /[^가-힣]/gi;
// 허용할 특수문자는 여기서 삭제
// 띄어쓰기도 특수문자 처리
    if( regExp.test(obj.value) ){
        alert("특수문자는 입력하실수 없습니다.");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
    }
}

// 회원가입시 휴대폰 번호 숫자 지정
const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

// 회원가입시 생년월일 숫자 지정
const autoDate = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{4})(\d{2})(\d{2})$/, `$1/$2/$3`);
}





// 회원가입시 주소
function execPostCode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.


            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("streetAddress").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}






// 회원가입시 성별입력
document.addEventListener('DOMContentLoaded', function () {
    let selectBox = document.getElementById('validationCustom04');
    let genderInput = document.getElementById('gender');

    selectBox.addEventListener('change', function () {
        switch (this.value) {
            case '1':
                genderInput.value = '남자';
                genderInput.readOnly = true; // 입력란을 읽기 전용으로 설정
                break;
            case '2':
                genderInput.value = '여자';
                genderInput.readOnly = true; // 입력란을 읽기 전용으로 설정
                break;
            case '3':
                genderInput.value = ''; // 입력란을 비우기
                genderInput.readOnly = false; // 입력란을 수정 가능하게 설정
                genderInput.focus(); // 사용자가 바로 입력할 수 있도록 입력란에 포커스
                break;
            default:
                genderInput.value = '';
                genderInput.readOnly = true;
                break;
        }
    });
});