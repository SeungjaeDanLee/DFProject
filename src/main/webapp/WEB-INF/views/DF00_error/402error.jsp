<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <link href="../resources/css/DF02_board.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.7.0.js"
            integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM="
            crossorigin="anonymous"></script>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .error-code {
            font-size: 72px;
            font-weight: bold;
            color: #333;
            margin-bottom: 20px;
        }

        .error-message {
            font-size: 24px;
            color: #666;
        }
    </style>

</head>
<body>
<div class="container">
    <div>
        <div class="error-code">402</div>
        <p class="error-message">이 요청은 결제가 필요합니다.</p>
        <a href="/">홈페이지로 이동</a>
    </div>
</div>
</body>
</html>
