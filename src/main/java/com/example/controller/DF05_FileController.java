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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class DF05_FileController {
    @Autowired
    DF05_FileService fileService;

    // 이미지 업로드
    @ResponseBody
    @RequestMapping(value = {"/imageUpload"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> imageUpload(@RequestParam("uploadFile") MultipartFile uploadFile) {

        // 파일 크기 및 가로세로 크기 제한 설정
        long maxSizeInBytes = 10 * 1024 * 1024; // 10MB 제한
        int maxWidth = 1920; // 최대 가로 크기
        int maxHeight = 1080; // 최대 세로 크기

        if (uploadFile.getSize() > maxSizeInBytes) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("errorFat", "이미지 파일이 너무 큽니다. 10MB 미만의 이미지 파일을 선택해주세요."));
        }

        try {
            // 이미지 파일을 메모리에 로드하여 크기 확인
            BufferedImage img = ImageIO.read(uploadFile.getInputStream());
            int width = img.getWidth();
            int height = img.getHeight();

            if (width > maxWidth || height > maxHeight) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("errorBig", "이미지 크기가 너무 큽니다. 최대 가로 " + maxWidth + "px, 세로 " + maxHeight + "px 이하의 이미지를 선택해주세요."));
            }

            // 이미지 저장 경로 설정
            String uploadFolder = "C:\\upload\\image";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            String datePath = sdf.format(new Date()).replace("-", File.separator);
            File uploadPath = new File(uploadFolder, datePath);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // 파일 이름 설정 (유니크한 이름으로)
            String originalFilename = uploadFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

            // 파일 저장
            File saveFile = new File(uploadPath, uniqueFileName);
            uploadFile.transferTo(saveFile);

            // 응답 데이터 생성 및 반환
            Map<String, String> response = new HashMap<>();
            response.put("datePath", datePath);
            response.put("fileName", uniqueFileName);
            return ResponseEntity.ok().body(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile file) {
//        String result = String.valueOf(fileService.uploadFile(file));
        int fno = fileService.uploadFile(file);
        if (fno != -1) {
            return ResponseEntity.ok(fno);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // 게시글 업데이트 파일 업로드
    @PostMapping("/fileUpdateUpload")
    public ResponseEntity<Integer> uploadUpdateFile(@RequestParam("file") MultipartFile file, @RequestParam("bno") int bno) {
        int result = fileService.uploadUpdateFile(file, bno);
        if (result != -1) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
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