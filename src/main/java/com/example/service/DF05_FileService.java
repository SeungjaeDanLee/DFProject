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
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DF05_FileService {
    @Autowired
    DF05_FileRepository fileRepository;

    @Autowired
    DF02_BoardRepository boardRepository;

    public String uploadFile(MultipartFile file) {
        // 파일 업로드 로직 구현


        try {
            // 파일을 저장할 경로 설정
            String uploadFolder = "C:\\upload\\file";

            // 파일 저장을 위한 날짜 형식 지정
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datePath = sdf.format(new Date()).replace("-", File.separator);

            // 파일 저장 경로 설정
            File uploadPath = new File(uploadFolder, datePath);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // 파일명 중복을 방지하기 위한 UUID 생성
            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // 파일 저장할 경로 및 파일명 설정
            File saveFile = new File(uploadPath, uniqueFileName);

            // 파일 저장
            file.transferTo(saveFile);

            // 파일 정보를 DTO에 저장
            DF05_FileDTO fileDTO = new DF05_FileDTO();
            fileDTO.setPath(uploadFolder + File.separator + datePath);
            fileDTO.setFile_name(uniqueFileName);
            fileDTO.setOrigin_name(file.getOriginalFilename());
            // 나머지 파일 정보 설정
//            fileDTO.setBno(boardRepository.getNextBno());

            // 파일 정보를 DB에 저장
            fileRepository.uploadFile(fileDTO);

            return "파일 업로드 성공";
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 업로드 실패: " + e.getMessage();
        }
    }

    // 다운로드할 파일의 경로를 받아서 파일 내용(byte 배열)을 반환하는 메서드
    public byte[] downloadFileByFileNameAndDatePath(String fileName, String datePath) throws IOException {
        String filePath = "C:\\upload\\file\\" + datePath + "\\" + fileName;
        return downloadFile(filePath);
    }

    // 파일 다운로드 메서드
    public byte[] downloadFile(String filePath) throws IOException {
        // 파일 읽기
        File file = new File(filePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }

//    private static final String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0207\\apache-tomcat-9.0.85\\webapps\\upload";


//    public DF05_FileDTO uploadFile(MultipartFile file) throws IOException {
//        String fileName = UUID.randomUUID() + "." + getExtension(file.getOriginalFilename());
//        File uploadDir = new File(UPLOAD_PATH);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//        File uploadedFile = new File(UPLOAD_PATH + fileName);
//        file.transferTo(uploadedFile);
//
//        DF05_FileDTO fileDTO = new DF05_FileDTO();
//        fileDTO.setFile_name(fileName);
//        fileDTO.setPath(UPLOAD_PATH + fileName);
//        fileDTO.setOrigin_name(file.getOriginalFilename());
//        fileDTO.setUploaded_date(new Timestamp(System.currentTimeMillis()));
//        fileDTO.setUpdated_date(new Timestamp(System.currentTimeMillis()));
//        fileDTO.setBno(getNextBno());
//        // DB에 저장
//        fileRepository.fileSave(fileDTO); // 예시
//
//        return fileDTO;
//    }
//
//    private String getExtension(String fileName) {
//        int lastIndexOfDot = fileName.lastIndexOf('.');
//        return lastIndexOfDot == -1 ? "" : fileName.substring(lastIndexOfDot + 1);
//    }
//
//    private int getNextBno() {
//        // DB에서 다음 fno 값을 가져오는 코드 (예시)
//        return boardRepository.getNextBno();
//    }


}
