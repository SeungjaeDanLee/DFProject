package com.example.repository;

import com.example.dto.DF01_MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DF01_MemberRepository {

    private final SqlSessionTemplate sql;

    //회원 가입
    public void memberJoin(DF01_MemberDTO memberDTO) {
        sql.insert("Member.memberJoin", memberDTO);
    }

    // 회원가입 시 아이디 중복 검사
    public DF01_MemberDTO findById(String inputId) {
        return sql.selectOne("Member.findById", inputId);
    }

    // 회원가입 시 닉네임 중복 검사
    public DF01_MemberDTO findByNickName(String inputNickName) {
        return sql.selectOne("Member.findByNickName", inputNickName);
    }

    // 로그인
    public DF01_MemberDTO memberLogin(DF01_MemberDTO memberDTO) {
        return sql.selectOne("Member.memberLogin", memberDTO);
    }

    public DF01_MemberDTO findbyMno(int mno) {
        return sql.selectOne("Member.findbyMno", mno);
    }

    // 회원탈퇴
    public void memberDelelte(int mno) {
        sql.delete("Member.memberDelete", mno);
    }


}
