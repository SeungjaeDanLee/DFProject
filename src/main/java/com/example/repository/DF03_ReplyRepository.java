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

    public void write_reply(DF03_ReplyDTO replyDTO) {
        sql.insert("Reply.writeReply", replyDTO);
    }

    public List<DF03_ReplyDTO> findAllReply(int bno) {
        return sql.selectList("Reply.findAllReply", bno);
    }
}
