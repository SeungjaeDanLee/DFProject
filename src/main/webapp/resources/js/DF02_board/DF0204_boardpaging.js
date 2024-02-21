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
