package com.example.repository;

import com.example.dto.DF01_MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DF01_MemberRepository {

    private final SqlSessionTemplate sql;

    @Autowired
    PasswordEncoder passwordEncoder;

    //회원 가입
    public void memberJoin(DF01_MemberDTO memberDTO) {
        sql.insert("Member.memberJoin", memberDTO);
    }

    // 회원가입 시 아이디 중복 검사
//    public DF01_MemberDTO findById(String inputId) {
//        return sql.selectOne("Member.findById", inputId);
//    }

    public int checkIdCount(String inputId) {
        return sql.selectOne("Member.checkIdCount", inputId);
    }

    // 회원가입 시 닉네임 중복 검사
//    public DF01_MemberDTO findByNickName(String inputNickName) {
//        return sql.selectOne("Member.findByNickName", inputNickName);
//    }

    public int checkNickNameCount(String inputNickName) {
        return sql.selectOne("Member.checkNickNameCount", inputNickName);
    }

    // 로그인
    public DF01_MemberDTO memberLogin(DF01_MemberDTO memberDTO) {
        DF01_MemberDTO loginMember = sql.selectOne("Member.memberLogin", memberDTO);
        return loginMember != null && passwordEncoder.matches(memberDTO.getPassword(), loginMember.getPassword()) ? loginMember : null;
    }



    // 세션 아이디로 해당 아이디의 mno 정보 찾기
    public DF01_MemberDTO findMno(String loginId) {
        return sql.selectOne("Member.findMno", loginId);
    }

    // 세션 아이디로 해당 아이디의 모든 정보 찾기
    public DF01_MemberDTO findByLoginId(String loginId) {
        return sql.selectOne("Member.findByLoginId", loginId);
    }

    // 회원 상세 정보
    public void memberDetail(DF01_MemberDTO memberDTO) {
        sql.selectOne("Member.memberDetail", memberDTO);
    }

    // 회원탈퇴
    public void memberDelete(int mno) {
        sql.delete("Member.memberDelete", mno);
    }

    public void memberUpdate(DF01_MemberDTO mno) {
        sql.update("Member.memberUpdate", mno);
    }

    public List<DF01_MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public DF01_MemberDTO findByMno(int mno) {
        return sql.selectOne("Member.findByMno", mno);
    }

    public String getPassword(String id) {
        return sql.selectOne("Member.getPassword", id);
    }



}
