package com.example.service;

import com.example.dto.DF05_FileDTO;
import com.example.repository.DF02_BoardRepository;
import com.example.repository.DF05_FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DF05_FileService {
    @Autowired
    DF05_FileRepository fileRepository;

    @Autowired
    DF02_BoardRepository boardRepository;

    private static final String UPLOAD_PATH = "C:\\upload\\";

    public DF05_FileDTO uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "." + getExtension(file.getOriginalFilename());
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File uploadedFile = new File(UPLOAD_PATH + fileName);
        file.transferTo(uploadedFile);

        DF05_FileDTO fileDTO = new DF05_FileDTO();
        fileDTO.setFile_name(fileName);
        fileDTO.setPath(UPLOAD_PATH + fileName);
        fileDTO.setOrigin_name(file.getOriginalFilename());
        fileDTO.setUploaded_date(new Timestamp(System.currentTimeMillis()));
        fileDTO.setUpdated_date(new Timestamp(System.currentTimeMillis()));
        fileDTO.setBno(getNextBno());
        // DB에 저장
        fileRepository.fileSave(fileDTO); // 예시

        return fileDTO;
    }

    private String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        return lastIndexOfDot == -1 ? "" : fileName.substring(lastIndexOfDot + 1);
    }

    private int getNextBno() {
        // DB에서 다음 fno 값을 가져오는 코드 (예시)
        return boardRepository.getNextBno();
    }
}
