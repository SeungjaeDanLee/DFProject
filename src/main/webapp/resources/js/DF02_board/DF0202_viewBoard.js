const listFn = () => {
    const page = '${page}';
    location.href = "/board/paging?page=" + page;
}
const updateFn = () => {
    const bno = '${board.bno}';
    location.href = "/board/update?bno=" + bno;
}
const deleteFn = () => {
    const bno = '${board.bno}';
    location.href = "/board/delete?bno=" + bno;
}

// const commentWrite = () => {
//     const writer = document.getElementById("commentWriter").value;
//     const contents = document.getElementById("commentContents").value;
//     const board = '${board.id}';
//     $.ajax({
//         type: "post",
//         url: "/comment/save",
//         data: {
//             commentWriter: writer,
//             commentContents: contents,
//             boardId: board
//         },
//         dataType: "json",
//         success: function(commentList) {
//             console.log("작성성공");
//             console.log(commentList);
//             let output = "<table>";
//             output += "<tr><th>댓글번호</th>";
//             output += "<th>작성자</th>";
//             output += "<th>내용</th>";
//             output += "<th>작성시간</th></tr>";
//             for(let i in commentList){
//                 output += "<tr>";
//                 output += "<td>"+commentList[i].id+"</td>";
//                 output += "<td>"+commentList[i].commentWriter+"</td>";
//                 output += "<td>"+commentList[i].commentContents+"</td>";
//                 output += "<td>"+commentList[i].commentCreatedTime+"</td>";
//                 output += "</tr>";
//             }
//             output += "</table>";
//             document.getElementById('comment-list').innerHTML = output;
//             document.getElementById('commentWriter').value='';
//             document.getElementById('commentContents').value='';
//         },
//         error: function() {
//             console.log("실패");
//         }
//     });
// }