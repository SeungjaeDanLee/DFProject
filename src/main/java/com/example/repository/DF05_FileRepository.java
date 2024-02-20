package com.example.repository;

import com.example.dto.DF05_FileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DF05_FileRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public void uploadFile(DF05_FileDTO fileDTO) {
        sql.insert("File.uploadFile", fileDTO);
    }

    public void updateUploadFile(DF05_FileDTO fileDTO) {
        sql.insert("File.updateUploadFile", fileDTO);
    }

    public List<DF05_FileDTO> findFiles(int bno) {
        return sql.selectList("File.findFiles", bno);
    }

    public int updateBno(int bno, int fno) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("fno", fno);
        return sql.update("File.updateBno", paramMap);
    }

    public void deleteFile(int fno) {
        sql.delete("File.deleteFile", fno);
    }

    public List<DF05_FileDTO> findAll() {
        return sql.selectList("File.findAll");
    }

    public int updateStatusFile(int fno) {
        return sql.update("File.updateStatusFile", fno);
    }

//    public String getOriginNameByFileNameAndPath(String fileName, String path) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("file_name", fileName);
//        paramMap.put("path", path);
//        return sql.selectOne("File.getOriginNameByFileNameAndPath", paramMap);
//    }

//    public void fileSave(DF05_FileDTO fileDTO) {
//        sql.insert("File.fileSave", fileDTO);
//    }

}
