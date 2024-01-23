package com.example.service;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF03_ReplyDTO;
import com.example.repository.DF01_MemberRepository;
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
}
