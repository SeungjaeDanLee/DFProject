// const replyWrite = () => {
//     const mno = '${member.mno}'
//     const content = document.getElementById("content").value;
//     const board = '${board.bno}';
//     $.ajax({
//         type: "post",
//         url: "/reply/write",
//         data: {
//             mno: mno,
//             content: content,
//             bno: board
//         },
//         dataType: "json",
//         success: function(replyList) {
//             console.log("작성성공");
//             console.log(replyList);
//             let output = "<table>";
//             output += "<th>작성자</th>";
//             output += "<th>내용</th>";
//             output += "<th>작성시간</th></tr>";
//             for(let i in replyList){
//                 output += "<tr>";
//                 output += "<td>"+replyList[i].mno+"</td>";
//                 output += "<td>"+replyList[i].content+"</td>";
//                 output += "<td>"+replyList[i].written_date+"</td>";
//                 output += "</tr>";
//             }
//             output += "</table>";
//             document.getElementById('ReplyList').innerHTML = output;
//             document.getElementById('mno').value='';
//             document.getElementById('content').value='';
//         },
//         error: function() {
//             console.log("실패");
//         }
//     });
// }


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
//                 success: function(replyList) {  // 서버로부터 응답을 성공적으로 받았을 때 실행할 함수
//                     $('#replyList').empty(); // 기존 댓글 목록을 비우기
//                     $.each(replyList, function(index, reply) {  // 응답으로 받은 댓글 리스트를 순회하며 각 댓글에 대해 다음의 함수를 실행
//                         let row = '<tr><td>' + reply.mno + '</td><td>' + reply.content + '</td><td>' + reply.written_date + '</td></tr>';  // 댓글 정보를 담은 테이블 행을 생성
//                         $('#replyList').append(row);  // 테이블에 새로운 행을 추가
//                     });
//                 }
//             });
//         });
//     });
// });