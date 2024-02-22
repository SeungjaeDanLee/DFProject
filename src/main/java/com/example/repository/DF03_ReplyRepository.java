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
    public void writeReply(DF03_ReplyDTO replyDTO) {
        sql.insert("Reply.writeReply", replyDTO);
    }

    // 댓글 업데이트
    public void updateReply(DF03_ReplyDTO replyDTO) {
        sql.update("Reply.updateReply", replyDTO);
    }

    // 댓글 삭제
    public void deleteReply(int rno) {
        sql.delete("Reply.deleteReply", rno);
    }

    // 해당 게시글의 댓글 모두 가져오기
    public List<DF03_ReplyDTO> findAllReply(int bno) {
        return sql.selectList("Reply.findAllReply", bno);
    }

    // 해당 댓글 주인 찾기
    public int findAuthorMnoByReplyRno(int rno) {
        return sql.selectOne("Reply.findAuthorMnoByReplyRno", rno);
    }

    // rno로 해당 댓글의 모든 정보 찾기
    public DF03_ReplyDTO findByReplyRno(int rno) {
        return sql.selectOne("Reply.findByReplyRno", rno);
    }

    // 모든 댓글 가져오기
    public List<DF03_ReplyDTO> findAll() {
        return sql.selectList("Reply.findAll");
    }

//    public int findAuthorMnoByAllReplyRno(List<DF03_ReplyDTO> replyDTOList) {
//        return sql.selectOne("Reply.findAuthorMnoByAllReplyRno", replyDTOList);
//    }
}
