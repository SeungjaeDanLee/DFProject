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

    public void replyDTO(DF03_ReplyDTO replyDTO) {
        replyRepository.write_reply(replyDTO);
    }

    public List<DF03_ReplyDTO> findAllReply(int bno) {
        return replyRepository.findAllReply(bno);
    }

    public void delete_reply(int rno) {
        replyRepository.deleteReply(rno);
    }

    public void update_reply(DF03_ReplyDTO replyDTO) {
        replyRepository.updateReply(replyDTO);
    }

    public int findAuthorMnoByReplyRno(int rno) {
        return replyRepository.findAuthorMnoByReplyRno(rno);
    }

    public DF03_ReplyDTO findByReplyRno(int rno) {
        return replyRepository.findByReplyRno(rno);
    }

    public List<DF03_ReplyDTO> findAll() {
        return replyRepository.findAll();
    }

//    public int findAuthorMnoByAllReplyRno(List<DF03_ReplyDTO> replyDTOList) {
//        return replyRepository.findAuthorMnoByAllReplyRno(replyDTOList);
//    }
}
