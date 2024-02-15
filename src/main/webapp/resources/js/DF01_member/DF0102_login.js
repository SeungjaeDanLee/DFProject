// 아이디 특수 문자 및 한글 금지
function characterCheck(obj){
    let regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]|[\u3131-\u314e\u314f-\u3163\uac00-\ud7a3]/gi;
    // 허용할 특수문자는 여기서 삭제
    // 띄어쓰기도 특수문자 처리
    // 한글 범위 추가
    if( regExp.test(obj.value) ){
        // alert("특수문자 및 한글은 입력하실 수 없습니다.");
        $("#result1").text("아이디 및 비밀번호는 알파벳 대소문자 및 숫자만 사용할 수 있습니다.").css("color", "red");
        obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 및 한글 한자리 지움
        // obj.value = ""; // 입력한 전체 값을 지움
    }
}