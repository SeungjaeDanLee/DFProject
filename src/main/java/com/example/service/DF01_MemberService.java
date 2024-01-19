package com.example.service;

import com.example.dto.DF01_MemberDTO;
import com.example.repository.DF01_MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DF01_MemberService {
    @Autowired
    DF01_MemberRepository memberRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // 회원가입
    // 평문
    public void member_join(DF01_MemberDTO memberDTO){
        memberRepository.memberJoin(memberDTO);
    }

    // 비밀번호 암호화 후 저장
//    public void member_join(DF01_MemberDTO memberDTO) {
////        String encodedId = passwordEncoder.encode(memberDTO.getId());
////        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
////
////        memberDTO.setId(encodedId);
////        memberDTO.setPassword(encodedPassword);
////        memberRepository.memberJoin(memberDTO);
//
//
//        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
//        memberDTO.setPassword(encodedPassword);
//        memberRepository.memberJoin(memberDTO);
//
//    }

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

    // 로그인 평문
    public boolean member_login(DF01_MemberDTO memberDTO){
        DF01_MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }


    // 로그인
//    public boolean member_login(DF01_MemberDTO memberDTO){
//        DF01_MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
//
//        String id = memberDTO.getId();
//        String password = memberDTO.getPassword();
////        if (loginMember != null
////                && passwordEncoder.matches(id, loginMember.getId())
////                && passwordEncoder.matches(password, loginMember.getPassword())) {
////            return true;
////        } else {
////            return false;
////        }
//
//        if (loginMember != null
//                && loginMember.getId().equals(id) // 아이디를 평문끼리 비교하도록 수정
//                && passwordEncoder.matches(password, loginMember.getPassword())) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    // 로그인된 아이디로 해당 아이디의 mno 정보 찾기
    public DF01_MemberDTO findMno(String loginId) {
        return memberRepository.findMno(loginId);
    }

    // 로그인된 아이디로 해당 아이디의 모든 정보 찾기
    public DF01_MemberDTO findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    // 회원 상세 정보

    public void member_detail(DF01_MemberDTO memberDTO) {

        memberRepository.memberDetail(memberDTO);
    }
    // 회원탈퇴

    public void member_delete(int mno) {
        memberRepository.memberDelelte(mno);
    }
}
