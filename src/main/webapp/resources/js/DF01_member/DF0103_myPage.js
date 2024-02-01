function confirmDelete() {
    if (confirm("정말로 회원을 탈퇴하시겠습니까?")) {
        let id = prompt("아이디를 입력해주세요:");
        let password = prompt("비밀번호를 입력해주세요:");

        // AJAX 요청을 통해 회원 본인 확인하기
        // $.ajax({
        //     type: "GET",
        //     url: "/members/checkMe",
        //     data: {
        //         inputId: id,
        //         inputPassword: password
        //     },
        //     success: function(response) {
        //         console.log("response: ", response); // 수정된 부분
        //         if (response === OK) {
        //             // 회원 본인인 경우 회원 탈퇴 작업 수행
        //             window.location.href = "/members/delete";
        //             alert("회원 탈퇴가 성공적으로 진행되었습니다.");
        //         } else {
        //             // 회원 본인이 아닌 경우 오류 처리
        //             alert("회원 본인이 아닙니다. 다시 시도해주세요.");
        //         }
        //     },
        //     error: function(jqXHR, textStatus, errorThrown) {
        //         console.log("jqXHR: ", jqXHR);
        //         console.log("textStatus: ", textStatus);
        //         console.log("errorThrown: ", errorThrown);
        //         alert("회원 본인 확인 중 오류가 발생했습니다.");
        //     }
        // });


        $.ajax({
            type: "GET",
            url: "/members/checkMe",
            data: {
                inputId: id,
                inputPassword: password
            },
            success: function(response, textStatus, jqXHR) {
                console.log("response: ", response);
                console.log("textStatus: ", textStatus);
                console.log("jqXHR: ", jqXHR);
            },
            statusCode: {
                200: function() {
                    // 회원 본인인 경우 회원 정보 수정 작업 수행
                    let confirmPassword = prompt("비밀번호를 한 번 더 입력해주세요:");
                    if (confirmPassword) {
                        // AJAX 요청을 통해 비밀번호 확인
                        $.ajax({
                            type: "GET",
                            url: "/members/checkMe",
                            data: {
                                inputId: id,
                                inputPassword: password
                            },
                            success: function(response) {
                                // 비밀번호가 일치하는 경우 회원 탈퇴 작업 수행
                                window.location.href = "/members/delete?inputPassword=" + password; // 비밀번호를 쿼리 파라미터로 전달
                                alert("회원 탈퇴가 성공적으로 진행되었습니다.");
                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                // 비밀번호가 일치하지 않는 경우 오류 처리
                                alert("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
                            }
                        });
                    }
                },
                401: function() {
                    // 회원 본인이 아닌 경우 오류 처리
                    alert("회원 본인이 아닙니다. 다시 시도해주세요.");
                }
            },
            error: function() {
                // 오류 발생 시 처리
                alert("회원 본인 확인 중 오류가 발생했습니다.");
            }
        });
    }
}


function confirmUpdate() {
    if (confirm("회원 정보를 수정하시겠습니까?")) {
        let id = prompt("아이디를 입력해주세요:");
        let password = prompt("비밀번호를 입력해주세요:");

        $.ajax({
            type: "GET",
            url: "/members/checkMe",
            data: {
                inputId: id,
                inputPassword: password
            },
            success: function(response, textStatus, jqXHR) {
                console.log("response: ", response);
                console.log("textStatus: ", textStatus);
                console.log("jqXHR: ", jqXHR);
            },
            statusCode: {
                200: function() {
                    // 회원 본인인 경우 회원 정보 수정 작업 수행
                    // 실무에서 이렇게 하면 큰 일
                    window.location.href = "/members/update?inputPassword=" + password;
                },
                401: function() {
                    // 회원 본인이 아닌 경우 오류 처리
                    alert("회원 본인이 아닙니다. 다시 시도해주세요.");
                }
            },
            error: function() {
                // 오류 발생 시 처리
                alert("회원 본인 확인 중 오류가 발생했습니다.");
            }
        });

        // $.ajax({
        //     type: "POST", // 요청 방식을 POST로 변경
        //     url: "/members/checkMe",
        //     data: JSON.stringify({
        //         inputId: id,
        //         inputPassword: password
        //     }),
        //     contentType: "application/json", // 데이터 형식을 JSON으로 설정
        //     success: function(response, textStatus, jqXHR) {
        //         console.log("response: ", response);
        //         console.log("textStatus: ", textStatus);
        //         console.log("jqXHR: ", jqXHR);
        //     },
        //     statusCode: {
        //         200: function() {
        //             window.location.href = "/members/update";
        //         },
        //         401: function() {
        //             alert("회원 본인이 아닙니다. 다시 시도해주세요.");
        //         }
        //     },
        //     error: function() {
        //         alert("회원 본인 확인 중 오류가 발생했습니다.");
        //     }
        // });

    }
}