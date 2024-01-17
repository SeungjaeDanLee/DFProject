package com.example.service;

import com.example.dto.DF01_MemberDTO;
import com.example.repository.DF01_MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DF01_MemberService {
    @Autowired
    DF01_MemberRepository memberRepository;

    // 회원가입
    public void member_join(DF01_MemberDTO memberDTO){
        memberRepository.memberJoin(memberDTO);
    }

    // 회원가입시 아이디 중복 체크
    public String checkId(String inputId) {
        DF01_MemberDTO memberDTO = memberRepository.findById(inputId);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    // 회원가입시 닉네임 중복 체크
    public String checkNickName(String inputNickName) {
        DF01_MemberDTO memberDTO = memberRepository.findByNickName(inputNickName);
        if (memberDTO == null) {
            return "ok";
        } else {
            return "no";
        }
    }


    // 로그인
    public boolean member_login(DF01_MemberDTO memberDTO){
        DF01_MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    public DF01_MemberDTO findbyMno(int mno) {
        return memberRepository.findbyMno(mno);
    }

    // 회원탈퇴
    public void member_delete(int mno) {
        memberRepository.memberDelelte(mno);
    }
}
