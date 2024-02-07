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
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

    @Autowired
    DF04_LikePointRepository likePointRepository;

    public boolean toggleLikePoint(DF04_LikePointDTO likePoint) {
        int bno = likePoint.getBno();

        if (likePointRepository.isLiked(bno, bno)) {
            likePointRepository.deleteLikePoint(bno, bno);
            boardService.decreaseLikePoints(bno);
            return false; // 좋아요 취소
        } else {
            likePointRepository.insertLikePoint(likePoint);
            boardService.increaseLikePoints(bno);
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
