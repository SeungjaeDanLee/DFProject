package com.example.repository;

import com.example.dto.DF03_ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DF03_ReplyRepository {
    @Autowired
    private SqlSessionTemplate sql;

    // 댓글 쓰기
    public void write_reply(DF03_ReplyDTO replyDTO) {
        sql.insert("Reply.writeReply", replyDTO);
    }

    // 댓글 삭제
    public void deleteReply(int rno) {
        sql.delete("Reply.deleteReply", rno);
    }

    public List<DF03_ReplyDTO> findAllReply(int bno) {
        return sql.selectList("Reply.findAllReply", bno);
    }

    public int findAuthorMnoByReplyRno(int rno) {
        return sql.selectOne("Reply.findAuthorMnoByReplyRno", rno);
    }

    public DF03_ReplyDTO findByReplyRno(int rno) {
        return sql.selectOne("Reply.findByReplyRno", rno);
    }

    public void updateReply(DF03_ReplyDTO replyDTO) {
        sql.update("Reply.updateReply", replyDTO);
    }

//    public int findAuthorMnoByAllReplyRno(List<DF03_ReplyDTO> replyDTOList) {
//        return sql.selectOne("Reply.findAuthorMnoByAllReplyRno", replyDTOList);
//    }
}
