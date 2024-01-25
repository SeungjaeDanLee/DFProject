// $(document).ready(function(){
//     $("form").on('submit', function(event){
//         event.preventDefault(); // 폼 제출을 막아 페이지가 새로고침되는 것을 방지
//         let formValues= $(this).serialize(); // 폼 데이터를 쿼리 문자열로 인코딩
//         $.post("/reply/write", formValues, function(data){
//             // 댓글 작성이 완료된 후 실행할 코드
//             $('.reply_content_box').val('');  // textarea의 내용을 지움
//             $.ajax({
//                 url: '/reply/' + '${board.bno}',  // 댓글 리스트를 가져오는 URL
//                 type: 'get',
//                 success: function(replyList) {
//                     $('#replyList').empty(); // 기존 내용을 비우기
//
//                     $.each(replyList, function(index, reply) {
//                         let writtenDate = new Date(reply.written_date); // 작성시간을 Date 객체로 변환
//                         let year = writtenDate.getFullYear();
//                         let month = (writtenDate.getMonth() + 1).toString().padStart(2, '0');
//                         let day = writtenDate.getDate().toString().padStart(2, '0');
//                         let hours = writtenDate.getHours().toString().padStart(2, '0');
//                         let minutes = writtenDate.getMinutes().toString().padStart(2, '0');
//                         let seconds = writtenDate.getSeconds().toString().padStart(2, '0');
//
//                         let div = '<div style="text-align: center; margin: 10px;">' +
//                             '<div>작성자 ' + reply.mno + '</div>' +
//                             '<div>내용 ' + reply.content + '</div>' +
//                             '<div>작성시간 ' + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds + '</div>' +
//                             '</div>' + '<hr>';
//                         $('#replyList').append(div); // 새로운 내용 추가
//                     });
//                 }
//
//             });
//         });
//     });
// });


$(document).ready(function(){
    $("form").on("submit", function(event){
        event.preventDefault(); // 폼 제출을 막아 페이지가 새로고침되는 것을 방지
        let formValues = $(this).serialize(); // 폼 데이터를 쿼리 문자열로 인코딩
        $.post("/reply/write", formValues, function(data){
            // 댓글 작성이 완료된 후 페이지를 리로드
            location.reload();
        });
    });
});

function openEditModal(rno, content) {
    // 현재 수정 중인 댓글의 ID를 저장
    document.getElementById('editedReplyId').value = rno;

    // 댓글 ID와 내용을 기반으로 수정할 내용을 팝업에 표시
    document.getElementById('editedContent').value = content;

    document.getElementById('editModal').style.display = 'block';
    document.querySelector('.overlay').style.display = '';
}

function saveEdit() {
    // 수정된 내용을 서버로 전송하고 댓글 업데이트 로직 추가
    let rno = document.getElementById('editedReplyId').value;
    // let bno = document.getElementById('boardId').value;
    let content = document.getElementById('editedContent').value;

    // 서버로 수정된 내용과 댓글 ID 전송 및 업데이트 로직 추가(AJAX)
    $.ajax({
        type: 'POST',
        url: '/reply/update',
        data: {
            rno: rno,
            content: content
        },
        success: function (response) {

            // 팝업 닫기
            closeEditModal();

            // 페이지 리로드
            location.reload();


            // 업데이트된 댓글 목록을 가져와서 화면에 반영
            // $.ajax({
            //     url: '/reply/' + '${board.bno}',
            //     type: 'get',
            //     success: function (updatedReplyList) {
            //         $('#replyList').empty(); // 기존 내용을 비우기
            //
            //         $.each(updatedReplyList, function (index, updatedReply) {
            //             let writtenDate = new Date(updatedReply.written_date);
            //             let year = writtenDate.getFullYear();
            //             let month = (writtenDate.getMonth() + 1).toString().padStart(2, '0');
            //             let day = writtenDate.getDate().toString().padStart(2, '0');
            //             let hours = writtenDate.getHours().toString().padStart(2, '0');
            //             let minutes = writtenDate.getMinutes().toString().padStart(2, '0');
            //             let seconds = writtenDate.getSeconds().toString().padStart(2, '0');
            //
            //             let div = '<div style="text-align: center; margin: 10px;">' +
            //                 '<div>작성자 ' + updatedReply.mno + '</div>' +
            //                 '<div>내용 ' + updatedReply.content + '</div>' +
            //                 '<div>작성시간 ' + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds + '</div>' +
            //                 '</div>' + '<hr>';
            //             $('#replyList').append(div); // 새로운 내용 추가
            //         });
            //     }
            // });
        },
        error: function (error) {
            // 수정이 실패하면 에러 처리

            // 팝업 닫기
            closeEditModal();
        }
    });

    // 팝업 닫기
    closeEditModal();

    // 페이지 리로드
    location.reload();
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
    document.querySelector('.overlay').style.display = 'none';
}