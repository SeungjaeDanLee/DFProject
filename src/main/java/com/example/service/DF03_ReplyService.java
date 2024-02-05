package com.example.service;

import com.example.dto.DF03_ReplyDTO;
import com.example.repository.DF03_ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DF03_ReplyService {
    @Autowired
    private DF03_ReplyRepository replyRepository;

    // 댓글 쓰기
    public void writeReply(DF03_ReplyDTO replyDTO) {
        replyRepository.writeReply(replyDTO);
    }

    // 해당 게시글의 댓글 모두 가져오기
    public List<DF03_ReplyDTO> findAllReply(int bno) {
        return replyRepository.findAllReply(bno);
    }

    // 댓글 업데이트(rno)
    public void update_reply(DF03_ReplyDTO replyDTO) {
        replyRepository.updateReply(replyDTO);
    }

    // 댓글 삭제(rno)
    public void delete_reply(int rno) {
        replyRepository.deleteReply(rno);
    }

    // 해당 댓글 주인 찾기
    public int findAuthorMnoByReplyRno(int rno) {
        return replyRepository.findAuthorMnoByReplyRno(rno);
    }

    // rno로 해당 댓글의 모든 정보 찾기
    public DF03_ReplyDTO findByReplyRno(int rno) {
        return replyRepository.findByReplyRno(rno);
    }

    // 모든 댓글 가져오기
    public List<DF03_ReplyDTO> findAll() {
        return replyRepository.findAll();
    }

//    public int findAuthorMnoByAllReplyRno(List<DF03_ReplyDTO> replyDTOList) {
//        return replyRepository.findAuthorMnoByAllReplyRno(replyDTOList);
//    }
}
