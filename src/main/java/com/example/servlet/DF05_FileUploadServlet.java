//package com.example.servlet;
//
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import javax.sql.DataSource;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.UUID;
//
//@WebServlet("/file/upload")
//@MultipartConfig
//public class DF05_FileUploadServlet extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        // 클라이언트로부터 이미지를 받아서 서버에 저장하는 코드 구현
//        Part filePart = request.getPart("image");
//        String fileName = generateFileName(filePart);
//        String uploadPath = getServletContext().getRealPath("/upload/") + fileName;  // 절대 경로 사용
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();  // 경로가 없으면 생성
//        }
//
//        try (InputStream input = filePart.getInputStream()) {
//            Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        }
//
//        String imageUrl = getServletContext().getContextPath() + "/upload/" + fileName;  // 컨텍스트 경로 포함
//
//
//        // 이미지 URL을 JSON 형태로 반환
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("imageUrl", imageUrl);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(jsonResponse.toString());
//    }
//
//    private String generateFileName(Part part) {
//        String originalFileName = getFileName(part);
//        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
//        return UUID.randomUUID() + extension;
//    }
//
//    private String getFileName(Part part) {
//        String contentDisposition = part.getHeader("content-disposition");
//        String[] elements = contentDisposition.split(";");
//        for (String element : elements) {
//            if (element.trim().startsWith("filename")) {
//                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
//}
