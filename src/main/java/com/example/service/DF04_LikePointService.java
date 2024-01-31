package com.example.service;

import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF04_LikePointDTO;
import com.example.repository.DF04_LikePointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DF04_LikePointService {

    @Autowired
    DF04_LikePointRepository likePointRepository;

    public boolean toggleLikePoint(DF04_LikePointDTO likePoint) {
        if (likePointRepository.isLiked(likePoint.getBno(), likePoint.getMno())) {
            likePointRepository.deleteLikePoint(likePoint.getBno(), likePoint.getMno());
            return false; // 좋아요 취소
        } else {
            likePointRepository.insertLikePoint(likePoint);
            return true; // 좋아요 추가
        }
    }

    public boolean isLiked(int bno, int mno) {
        // 좋아요 여부를 확인하는 로직을 구현
        // 해당 게시물 번호(bno)와 사용자 번호(mno)를 이용하여 데이터베이스에서 조회하여 좋아요 여부를 확인하고 boolean 값으로 반환
        if (likePointRepository.isLiked(bno, mno)) {
            return true;
        } else {
            return false;
        }
    }
}
