package com.example.repository;

import com.example.dto.DF05_FileDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DF05_FileRepository {
    @Autowired
    private SqlSessionTemplate sql;

//    public void fileSave(DF05_FileDTO fileDTO) {
//        sql.insert("File.fileSave", fileDTO);
//    }

}
