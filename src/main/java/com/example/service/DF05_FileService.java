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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DF05_FileService {
    @Autowired
    DF05_FileRepository fileRepository;

    @Autowired
    DF02_BoardRepository boardRepository;

    @Autowired
    DF02_BoardService boardService;

    // 화이트 리스트
    private String[] whiteList = {"hwp", "txt"};

    // 게시글 작성시 파일 업로드
    public int uploadFile(MultipartFile file) {
        try {
            String uploadFolder = "C:/upload/file";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            String datePath = sdf.format(new Date()).replace("-", "/");
            File uploadPath = new File(uploadFolder, datePath);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String uniqueFileName = UUID.randomUUID() + "." + fileExtension;

            // 화이트리스트에 포함된 확장자인지 확인
            boolean isWhiteList = false;

            for (String extension : whiteList) {
                if (extension.equals(fileExtension)) {
                    isWhiteList = true;
                    break;
                }
            }

            // 화이트리스트에 포함된 확장자가 아니면 업로드 거부
            if (!isWhiteList) {
                return -1;
            }

            File saveFile = new File(uploadPath, uniqueFileName);
            file.transferTo(saveFile);

            DF05_FileDTO fileDTO = new DF05_FileDTO();
            fileDTO.setPath(uploadFolder + "/" + datePath);
            fileDTO.setFile_name(uniqueFileName);
            fileDTO.setOrigin_name(file.getOriginalFilename());

            fileRepository.uploadFile(fileDTO);
            int fno = fileDTO.getFno();
            return fno; // fno 반환
        }  catch (IOException e) {
            e.printStackTrace();
            return -1; // 파일 업로드 실패 시 -1 반환
        }
    }

    // 파일의 fno를 받아서 해당 게시글의 bno를 업데이트
    public int updateBno(int bno, int fno){
        return fileRepository.updateBno(bno, fno);
    }

    // 게시글 수정시 파일 업로드
    public int uploadUpdateFile(MultipartFile file, int bno) {
        try {
            String uploadFolder = "C:/upload/file";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
            String datePath = sdf.format(new Date()).replace("-", "/");
            File uploadPath = new File(uploadFolder, datePath);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String uniqueFileName = UUID.randomUUID() + "." + fileExtension;

            // 화이트리스트에 포함된 확장자인지 확인
            boolean isWhiteList = false;

            for (String extension : whiteList) {
                if (extension.equals(fileExtension)) {
                    isWhiteList = true;
                    break;
                }
            }

            // 화이트리스트에 포함된 확장자가 아니면 업로드 거부
            if (!isWhiteList) {
                return -1;
            }

            File saveFile = new File(uploadPath, uniqueFileName);
            file.transferTo(saveFile);

            DF05_FileDTO fileDTO = new DF05_FileDTO();
            fileDTO.setPath(uploadFolder + "/" + datePath);
            fileDTO.setFile_name(uniqueFileName);
            fileDTO.setOrigin_name(file.getOriginalFilename());
            fileDTO.setBno(bno);

            fileRepository.updateUploadFile(fileDTO);

            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

//    public String uploadFile(MultipartFile file) {
//        return uploadFileCommon(file, boardRepository.getNextBno());
//    }



    // 다운로드할 파일의 경로를 받아서 파일 내용(byte 배열)을 반환하는 메서드
    public byte[] downloadFileByFileNameAndDatePath(String fileName, String datePath) throws IOException {
//        String filePath = "C:\\upload\\file\\" + datePath + "\\" + fileName;
        String filePath = datePath + "/" + fileName;
        return downloadFile(filePath);
    }

    // 파일 다운로드 메서드
    public byte[] downloadFile(String filePath) throws IOException {
        // 파일 읽기
        File file = new File(filePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }

    public List<DF05_FileDTO> findFiles(int bno) {
        return fileRepository.findFiles(bno);
    }

    public void delete_file(int fno) {
        fileRepository.deleteFile(fno);
    }

    public List<DF05_FileDTO> findAll() {
        return fileRepository.findAll();
    }

    // 일반 업로드 예시
//    public String uploadFileCommon(MultipartFile file, int bno) {
//        try {
//            String uploadFolder = "C:/upload/file";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
//            String datePath = sdf.format(new Date()).replace("-", "/");
//            File uploadPath = new File(uploadFolder, datePath);
//            if (!uploadPath.exists()) {
//                uploadPath.mkdirs();
//            }
//
//            String originalFileName = file.getOriginalFilename();
//            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//            String uniqueFileName = UUID.randomUUID() + "." + fileExtension;
//
//            // 화이트리스트에 포함된 확장자인지 확인
//            boolean isWhiteList = false;
//
//            for (String extension : whiteList) {
//                if (extension.equals(fileExtension)) {
//                    isWhiteList = true;
//                    break;
//                }
//            }
//
//            // 화이트리스트에 포함된 확장자가 아니면 업로드 거부
//            if (!isWhiteList) {
//                return null;
//            }
//
//            File saveFile = new File(uploadPath, uniqueFileName);
//            file.transferTo(saveFile);
//
//            DF05_FileDTO fileDTO = new DF05_FileDTO();
//            fileDTO.setPath(uploadFolder + "/" + datePath);
//            fileDTO.setFile_name(uniqueFileName);
//            fileDTO.setOrigin_name(file.getOriginalFilename());
//            fileDTO.setBno(bno);
//
//            fileRepository.updateUploadFile(fileDTO);
//
//            return "파일 업로드 성공";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "파일 업로드 실패: " + e.getMessage();
//        }
//    }
}
