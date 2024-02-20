$(document).ready(function () {
    // AJAX 호출
    $.ajax({
        type: 'GET',
        url: '/admin/fileManagement',
        dataType: 'json',
        success: function (data) {
            console.log('Received data:', data);

            // 데이터를 받아와서 테이블에 추가
            let workList = $("#datatablesSimple");


            // 이전에 있던 빈 행 제거
            workList.find("td").remove();

            // 데이터가 배열 형태로 불러오기
            for (let i = 0; i < data.length; i++) {
                let rowData = data[i];

                // 날짜와 시간 데이터 포맷 변환
                let formattedDate = formatDate(rowData.date);
                let formattedCheckInTime = formatTime(rowData.checkInTime);
                let formattedCheckOutTime = formatTime(rowData.checkOutTime);

                // 새로운 행 추가
                let newRow = $("<tr>");
                newRow.append("<td>" + formattedDate + "</td>");
                newRow.append("<td>" + rowData.memberName + "</td>");
                newRow.append("<td>" + rowData.deptName + "</td>");
                newRow.append("<td>" + formattedCheckInTime + "</td>");
                newRow.append("<td>" + formattedCheckOutTime + "</td>");

                // 상태 동적으로 표시
                let statusCell = "<td>" + getStatus(rowData.checkInTime, rowData.checkOutTime) + "</td>";
                newRow.append(statusCell);

                workList.append(newRow);


            }
        },
        error: function () {
            // 오류 발생 시 처리
            console.error('Failed to fetch data');
        }
    });
});

//검색기능추가
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('searchBtn').addEventListener('click', function () {
        const searchTerm = document.getElementById('searchInput').value.toLowerCase();
        const rows = document.querySelectorAll('#datatablesSimple tbody tr');

        rows.forEach(row => {
            // 기존 코드에서 변경: 각 열이 존재하는지 확인
            const name = row.querySelector('td:nth-child(2)');
            const dept = row.querySelector('td:nth-child(3)');
            const checkInTime = row.querySelector('td:nth-child(4)');
            const checkOutTime = row.querySelector('td:nth-child(5)');

            // 수정: 각 열이 존재하지 않더라도 계속 검색 수행
            const nameText = name ? name.textContent.toLowerCase() : '';
            const deptText = dept ? dept.textContent.toLowerCase() : '';
            const checkInTimeText = checkInTime ? checkInTime.textContent.toLowerCase() : '';
            const checkOutTimeText = checkOutTime ? checkOutTime.textContent.toLowerCase() : '';

            // 수정: 각 열이 존재하지 않더라도 계속 검색 수행
            if (nameText.includes(searchTerm) || deptText.includes(searchTerm) || checkInTimeText.includes(searchTerm) || checkOutTimeText.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
});


// 날짜와 시간 형식 변환 함수
function formatDate(dateString) {
    let date = new Date(dateString);

    if (isNaN(date.getTime())) {
        // 날짜가 올바르게 파싱되지 않은 경우
        return '3333';
    }

    // "YYYY-MM-DD" 형식으로 반환
    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
}

function formatTime(dateTimeString) {
    let dateTime = new Date(dateTimeString);
    if (isNaN(dateTime.getTime())) {
        // 날짜가 올바르게 파싱되지 않은 경우
        return '';
    }
    return dateTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}

const deleteFile = (fno) => {
    location.href = "/admin/fileManagement/delete?fno="+fno;
}