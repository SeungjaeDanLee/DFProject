//package com.example.service;
//
//import com.example.dto.DF03_ReplyDTO;
//import com.example.filter.RequestWrapper;
//import com.example.repository.DF03_ReplyRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DF03_ReplyService {
//    @Autowired
//    private DF03_ReplyRepository replyRepository;
//
//    // 댓글 쓰기
//    public void writeReply(DF03_ReplyDTO replyDTO) {
//        String encodedReply = RequestWrapper.cleanXSS(replyDTO.getContent());
//        replyDTO.setContent(encodedReply);
//
//        replyRepository.writeReply(replyDTO);
//    }
//
//    // 해당 게시글의 댓글 모두 가져오기
////    public List<DF03_ReplyDTO> findAllReply(int bno) {
////        return replyRepository.findAllReply(bno);
////    }
//
//    public List<DF03_ReplyDTO> findAllReply(int bno) {
//        List<DF03_ReplyDTO> replyDTOList = replyRepository.findAllReply(bno);
////        String replyDTOList = StringEscapeUtils.escapeJava(String.valueOf(replyRepository.findAllReply(bno)));
//
//
//        for (DF03_ReplyDTO replyDTO : replyDTOList) {
//            String unescapedContent = RequestWrapper.decodeHtmlEntities(replyDTO.getContent());
//            replyDTO.setContent(unescapedContent);
//        }
//
//        return replyDTOList;
//    }
//
//
//    // 댓글 업데이트(rno)
//    public void update_reply(DF03_ReplyDTO replyDTO) {
//        String encodedReply = RequestWrapper.cleanXSS(replyDTO.getContent());
//        replyDTO.setContent(encodedReply);
//
//        replyRepository.updateReply(replyDTO);
//    }
//
//    // 댓글 삭제(rno)
//    public void delete_reply(int rno) {
//        replyRepository.deleteReply(rno);
//    }
//
//    // 해당 댓글 주인 찾기
//    public int findAuthorMnoByReplyRno(int rno) {
//        return replyRepository.findAuthorMnoByReplyRno(rno);
//    }
//
//    // rno로 해당 댓글의 모든 정보 찾기
//    public DF03_ReplyDTO findByReplyRno(int rno) {
//        return replyRepository.findByReplyRno(rno);
//    }
//
//    // 모든 댓글 가져오기
//    public List<DF03_ReplyDTO> findAll() {
//
//        List<DF03_ReplyDTO> replyDTOList = replyRepository.findAll();
////        String replyDTOList = StringEscapeUtils.escapeJava(String.valueOf(replyRepository.findAllReply(bno)));
//
//
//        for (DF03_ReplyDTO replyDTO : replyDTOList) {
//            String unescapedContent = RequestWrapper.decodeHtmlEntities(replyDTO.getContent());
//            replyDTO.setContent(unescapedContent);
//        }
//
//        return replyDTOList;
//    }
//
////    public int findAuthorMnoByAllReplyRno(List<DF03_ReplyDTO> replyDTOList) {
////        return replyRepository.findAuthorMnoByAllReplyRno(replyDTOList);
////    }
//
//
//
////    private String cleanXSS(String value) {
////        System.out.println("XSS Filter before : " + value);
////        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
////        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
////        value = value.replaceAll("'", "& #39;");
////        value = value.replaceAll("eval\\((.*)\\)", "");
////        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
////        value = value.replaceAll("script", "");
////        System.out.println("XSS Filter after : " + value);
////        return value;
////    }
////
////    public static String decodeHtmlEntities(String value) {
////        System.out.println("XSS Filter before : " + value);
////        value = value.replaceAll("& lt;", "<").replaceAll("& gt;", ">");
////        value = value.replaceAll("& #40;", "(").replaceAll("& #41;", ")");
////        value = value.replaceAll("& #39;", "'");
////        value = value.replaceAll("eval\\((.*)\\)", "");
////        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
////        value = value.replaceAll("script", "");
////        System.out.println("XSS Filter after : " + value);
////        return value;
////    }
//
//}


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
}