package com.example.controller;

import com.example.dto.DF05_FileDTO;
import com.example.service.DF05_FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @PostMapping("/upload")
//    @ResponseBody
//    public Map<String, Object> uploadFile(@RequestParam ("image") MultipartFile file) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            DF05_FileDTO uploadFileDTO = fileService.uploadFile(file);
//            response.put("fno", uploadFileDTO.getFno());
//            response.put("url", uploadFileDTO.getPath());
//            return response;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    // 이미지 업로드
    @ResponseBody
    @RequestMapping(value = {"/imageUpload"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> imageUpload(MultipartFile[] uploadFile) {

        // 톰캣 내부 저장경로 (*윈도우), 없을 경우 폴더생성
        String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0207\\apache-tomcat-9.0.85\\webapps\\upload";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datePath = sdf.format(new Date()).replace("-", File.separator);
        File uploadPath = new File(uploadFolder, datePath);
        if (!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        Map<String, String> response = new HashMap<>();
        byte b;
        int i;
        MultipartFile[] arrayOfMultipartFile;
        // 배열에서 한 개씩 꺼내서 저장
        for (i = (arrayOfMultipartFile = uploadFile).length, b = 0; b < i; ) {
            MultipartFile upFile = arrayOfMultipartFile[b];
//            String originalFileName = upFile.getOriginalFilename();
//            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;
            String uniqueFileName = String.valueOf(UUID.randomUUID());

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
        String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0207\\apache-tomcat-9.0.85\\webapps\\upload";

        File file = new File(uploadFolder + File.separator + datePath + File.separator + fileName);
        if (file.exists()){
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
}