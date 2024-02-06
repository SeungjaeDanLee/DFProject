package com.example.controller;

import com.example.dto.DF05_FileDTO;
import com.example.service.DF05_FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class DF05_FileController {
    @Autowired
    DF05_FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam ("image") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            DF05_FileDTO uploadFileDTO = fileService.uploadFile(file);
            response.put("fno", uploadFileDTO.getFno());
            response.put("url", uploadFileDTO.getPath());
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}