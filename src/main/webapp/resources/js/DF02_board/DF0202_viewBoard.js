function openEditModal(replyId, content) {
    // 댓글 ID와 내용을 기반으로 수정할 내용을 팝업에 표시
    document.getElementById('editedContent').value = content;

    // 현재 수정 중인 댓글의 ID를 저장
    document.getElementById('editedReplyId').value = replyId;

    document.getElementById('editModal').style.display = 'block';
    document.querySelector('.overlay').style.display = '';
}

function saveEdit() {
    // 수정된 내용을 서버로 전송하고 댓글 업데이트 로직 추가
    let editedContent = document.getElementById('editedContent').value;
    let replyId = document.getElementById('editedReplyId').value;

    // 서버로 수정된 내용과 댓글 ID 전송 및 업데이트 로직 추가(AJAX)
    $.ajax({
        type: 'POST',
        url: '/reply/update',
        data: {
            replyId: replyId,
            editedContent: editedContent
        },
        success: function (response) {
            // 수정이 성공하면 서버에서의 응답을 처리
            // ...

            // 팝업 닫기
            closeEditModal();
        },
        error: function (error) {
            // 수정이 실패하면 에러 처리
            // ...

            // 팝업 닫기
            closeEditModal();
        }
    });

    // 팝업 닫기
    closeEditModal();
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
    document.querySelector('.overlay').style.display = 'none';
}