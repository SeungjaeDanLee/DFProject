package com.example.repository;

import com.example.dto.DF04_LikePointDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DF04_LikePointRepository {
    @Autowired
    private SqlSessionTemplate sql;

    // 좋아요 저장
    public void insertLikePoint(DF04_LikePointDTO likePoint) {
        sql.insert("LikePoint.insertLikePoint", likePoint);
    }

    // 좋아요 삭제
    public void deleteLikePoint(int bno, int mno) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("bno", bno);
        parameters.put("mno", mno);
        sql.delete("LikePoint.deleteLikePoint", parameters);
    }

    // 게시물 번호(bno)와 사용자 번호(mno)를 이용하여 좋아요 여부를 확인
    public boolean isLiked(int bno, int mno) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("bno", bno);
        parameters.put("mno", mno);
        int count = sql.selectOne("LikePoint.isLiked", parameters);
        return count > 0;
    }
}
