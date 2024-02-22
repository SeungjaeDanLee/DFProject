function confirmDelete() {
    if (confirm("DogFit을 탈퇴 하시겠습니까?")) {
        let id = prompt("아이디를 입력해주세요:");
        let password = prompt("비밀번호를 입력해주세요:");
        $.ajax({
            type: "POST", // 요청 방식을 POST로 변경
            url: "/members/delete",
            data: JSON.stringify({
                inputId: id,
                inputPassword: password
            }),
            dataType: "JSON",
            contentType: "application/json", // 데이터 형식을 JSON으로 설정
            success: function(result) {
                console.log('success');
                console.log(result.status);
                if (result.status === 'success'){
                    alert("회원탈퇴에 성공했습니다.");
                    window.location.href = "/";
                } else {
                    alert("회원탈퇴에 실패했습니다.");
                    location.reload();
                }

            }
        });

    }
}


// function confirmUpdate() {
//     if (confirm("회원 정보를 수정하시겠습니까?")) {
//         let id = prompt("아이디를 입력해주세요:");
//         let password = prompt("비밀번호를 입력해주세요:");
//         $.ajax({
//             type: "POST", // 요청 방식을 POST로 변경
//             url: "/members/checkMe",
//             data: JSON.stringify({
//                 inputId: id,
//                 inputPassword: password
//             }),
//             dataType: "JSON",
//             contentType: "application/json", // 데이터 형식을 JSON으로 설정
//             success: function(result) {
//                 console.log('success');
//                 console.log(result.status);
//                 window.location.href = "/members/updating";
//
//             }
//         });
//
//     }
// }


function confirmUpdate() {
    if (confirm("회원 정보를 수정하시겠습니까?")) {
        let id = prompt("아이디를 입력해주세요:");
        let password = prompt("비밀번호를 입력해주세요:");
        $.ajax({
            type: "POST", // 요청 방식을 POST로 변경
            url: "/members/checkMe",
            data: JSON.stringify({
                inputId: id,
                inputPassword: password
            }),
            dataType: "JSON",
            contentType: "application/json", // 데이터 형식을 JSON으로 설정
            success: function(result) {
                if (result.status === 'success'){
                    window.location.href = "/members/updating";
                }
            }
        });

    }
}