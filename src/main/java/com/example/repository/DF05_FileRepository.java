package com.example.repository;

import com.example.dto.DF05_FileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DF05_FileRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public void uploadFile(DF05_FileDTO fileDTO) {
        sql.insert("File.uploadFile", fileDTO);
    }

    public List<DF05_FileDTO> findFiles(int bno) {
        return sql.selectList("File.findFiles", bno);
    }

//    public void fileSave(DF05_FileDTO fileDTO) {
//        sql.insert("File.fileSave", fileDTO);
//    }

}
