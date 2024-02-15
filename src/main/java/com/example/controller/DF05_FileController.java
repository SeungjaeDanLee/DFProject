package com.example.controller;

import com.example.service.DF05_FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class DF05_FileController {
    @Autowired
    DF05_FileService fileService;

    // 이미지 업로드
    @ResponseBody
    @RequestMapping(value = {"/imageUpload"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> imageUpload(MultipartFile[] uploadFile) {

        // 파일 크기 및 가로세로 크기 제한 설정
        long maxSize = 10 * 1024 * 1024; // 최대 10MB
        int maxWidth = 1920; // 최대 가로 크기
        int maxHeight = 1080; // 최대 세로 크기

        // 톰캣 내부 저장경로 (*윈도우), 없을 경우 폴더생성
//        String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0207\\apache-tomcat-9.0.85\\webapps\\upload";
        String uploadFolder = "C:\\upload\\image";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datePath = sdf.format(new Date()).replace("-", File.separator);
        File uploadPath = new File(uploadFolder, datePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        Map<String, String> response = new HashMap<>();
        byte b;
        int i;
        MultipartFile[] arrayOfMultipartFile;
        // 배열에서 한 개씩 꺼내서 저장
        for (i = (arrayOfMultipartFile = uploadFile).length, b = 0; b < i; ) {
            MultipartFile upFile = arrayOfMultipartFile[b];
            String originalFileName = upFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String uniqueFileName = UUID.randomUUID() + "." + fileExtension;

            File saveFile = new File(uploadPath, uniqueFileName);
            try {
                upFile.transferTo(saveFile);
                response.put("datePath", datePath);
                response.put("fileName", uniqueFileName);
            } catch (IOException e) {
                e.printStackTrace();
//                logger.info("[ 이미지 업로드 ] Fail :: "+originalFileName+" File IOException Error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            b++;
        }
        return ResponseEntity.ok().body(response);
    }

    // 이미지 보기
    @ResponseBody
    @GetMapping({"/display"})
    public ResponseEntity<byte[]> showImageGET(@RequestParam("fileName") String fileName,
                                               @RequestParam("datePath") String datePath) {
        // 톰캣 내부 저장경로 (*윈도우)
//        String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0207\\apache-tomcat-9.0.85\\webapps\\upload";
        String uploadFolder = "C:\\upload\\image";

        File file = new File(uploadFolder + File.separator + datePath + File.separator + fileName);
        if (file.exists()) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-type", Files.probeContentType(file.toPath()));
                byte[] fileBytes = FileCopyUtils.copyToByteArray(file);
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
//                logger.info("[ 이미지 보기 ] Fail :: "+fileName+" File IOException Error");
            }
        }
        return ResponseEntity.notFound().build();
    }

    // 파일 업로드
    @PostMapping("/fileUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String result = fileService.uploadFile(file);
        return ResponseEntity.ok(result);
    }

    // 게시글 업데이트 파일 업로드
    @PostMapping("/fileUpdateUpload")
    public ResponseEntity<String> uploadUpdateFile(@RequestParam("file") MultipartFile file, @RequestParam("bno") int bno) {
        String result = fileService.uploadUpdateFile(file, bno);
        return ResponseEntity.ok(result);
    }

    // 파일 다운로드
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("file_name") String file_name,
                                               @RequestParam("path") String path) {
        try {
            byte[] fileContent = fileService.downloadFileByFileNameAndDatePath(file_name, path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", file_name);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}