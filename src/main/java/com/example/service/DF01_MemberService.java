package com.example.service;

import com.example.dto.DF01_MemberDTO;
import com.example.repository.DF01_MemberRepository;
import com.example.util.AES128Util;
import com.example.util.AES192Util;
import com.example.util.AES256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class DF01_MemberService {
    @Autowired
    DF01_MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    // 비밀번호 암호화 후 저장
    public void member_join(DF01_MemberDTO memberDTO) {
        memberRepository.memberJoin(memberDTO);

    }

    // 회원가입시 아이디 중복 체크
//    public String checkId(String inputId) {
//        DF01_MemberDTO memberDTO = memberRepository.findById(inputId);
//        if (memberDTO == null) {
//            return "ok";
//        } else {
//            return "no";
//        }
//    }

    // 회원가입시 아이디 중복 체크(0 or 1)
    public int checkIdCount(String inputId){
        return memberRepository.checkIdCount(inputId);
    }

    // 회원가입시 닉네임 중복 체크
//    public String checkNickName(String inputNickName) {
//        DF01_MemberDTO memberDTO = memberRepository.findByNickName(inputNickName);
//        if (memberDTO == null) {
//            return "ok";
//        } else {
//            return "no";
//        }
//    }

    // 회원가입시 닉네임 중복 체크(0 or 1)
    public int checkNickNameCount(String inputNickName) {
        return memberRepository.checkNickNameCount(inputNickName);
    }

    // 로그인
    public DF01_MemberDTO memberLogin(DF01_MemberDTO memberDTO) {
        DF01_MemberDTO loginMember = memberRepository.memberLogin(memberDTO);
        if (loginMember != null && passwordEncoder.matches(memberDTO.getPassword(), loginMember.getPassword())) {
            return loginMember;
        } else {
            return null;
        }
    }

    // 로그인된 아이디로 해당 아이디의 모든 정보 찾기
    public DF01_MemberDTO findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    // 회원정보 테스트중
    public boolean memberIdCheck(Map paramMap) throws Exception {
        String loginId = paramMap.get("loginId").toString();
        String inputId = paramMap.get("inputId").toString();
        String inputPassword = paramMap.get("inputPassword").toString();

        DF01_MemberDTO memberDTO;
        if (loginId.equals(inputId)) {
            // 아이디가 일치하는 경우
            // 여기서 inputPassword와 실제 회원의 비밀번호를 확인하고, 일치하는 경우에만 작업 수행
            memberDTO = findByLoginId(loginId);
            if (memberDTO != null && loginId.equals(inputId) && passwordEncoder.matches(inputPassword, memberDTO.getPassword())) {
                return true;

//                DF01_MemberDTO decryptMemberDto;
//                decryptMemberDto = decryptPersonalData(memberDTO);

                // 이메일을 @ 문자를 기준으로 분리하여 배열에 저장
//                String[] emailParts = decryptMemberDto.getEmail().split("@");
//                if (emailParts.length == 2) {
//                    // 첫 번째 요소는 이메일 아이디, 두 번째 요소는 도메인
//                    String emailId = emailParts[0];
//                    String domain = emailParts[1];
//
//                } else {
//                    // 이메일 형식이 올바르지 않을 경우 예외 처리
//                    // 필요에 따라 로깅 또는 다른 처리를 수행할 수 있음
//                }


            } else {
            }

        }
        return false;
    }

        // 회원 상세 정보
        public void member_detail (DF01_MemberDTO memberDTO){
            memberRepository.memberDetail(memberDTO);
        }

        // 모든 회원정보 찾기
        public List<DF01_MemberDTO> findAll () {
            return memberRepository.findAll();
        }

        // Mno로 회원정보 찾기
        public DF01_MemberDTO findByMno ( int mno){
            return memberRepository.findByMno(mno);
        }

        // 회원정보 업데이트
        public void member_update (DF01_MemberDTO mno){
            memberRepository.memberUpdate(mno);
        }

        // 회원탈퇴
        public void member_delete ( int mno){
            memberRepository.memberDelete(mno);
        }

        // 개인정보 암호화
        public void encryptPersonalData (DF01_MemberDTO memberDTO) throws Exception {
            // 이메일 암호화
            String encodedEmail = AES256Util.encrypt(memberDTO.getEmail());
            memberDTO.setEmail(encodedEmail);

            // 이름 암호화
            String encodedName = AES256Util.encrypt(memberDTO.getName());
            memberDTO.setName(encodedName);

            // 폰 번호 암호화
            String encodedPhone = AES256Util.encrypt(memberDTO.getPhone());
            memberDTO.setPhone(encodedPhone);

            // 지번 암호화
            String encodedZipcode = AES192Util.encrypt(memberDTO.getZipcode());
            memberDTO.setZipcode(encodedZipcode);

            // 도로명 암호화
            String encodedStreetAddress = AES192Util.encrypt(memberDTO.getStreetAddress());
            memberDTO.setStreetAddress(encodedStreetAddress);

            // 상세주소 암호화
            String encodedDetailAddress = AES192Util.encrypt(memberDTO.getDetailAddress());
            memberDTO.setDetailAddress(encodedDetailAddress);

            // 성별 암호화
            String encodedGender = AES128Util.encrypt(memberDTO.getGender());
            memberDTO.setGender(encodedGender);

            // 생년월일 암호화
//        java.sql.Date encodedBirthday = java.sql.Date.valueOf(AES128Util.encrypt(String.valueOf(memberDTO.getBirthday())));
//        memberDTO.setBirthday(encodedBirthday);
        }

        // 개인정보 복호화
//        public DF01_MemberDTO decryptPersonalData (DF01_MemberDTO memberDTO) throws Exception {
//            DF01_MemberDTO returnMemberDto = new DF01_MemberDTO();
//
//            // 이메일 복호화
//            String decodedEmail = AES256Util.decrypt(memberDTO.getEmail());
//            returnMemberDto.setEmail(decodedEmail);
//
//            // 이름 복호화
//            String decodedName = AES256Util.decrypt(memberDTO.getName());
//            returnMemberDto.setName(decodedName);
//
//            // 폰 번호 복호화
//            String decodedPhone = AES256Util.decrypt(memberDTO.getPhone());
//            returnMemberDto.setPhone(decodedPhone);
//
//            // 지번 복호화
//            String decodedZipcode = AES192Util.decrypt(memberDTO.getZipcode());
//            returnMemberDto.setZipcode(decodedZipcode);
//
//            // 도로명 복호화
//            String decodedStreetAddress = AES192Util.decrypt(memberDTO.getStreetAddress());
//            returnMemberDto.setStreetAddress(decodedStreetAddress);
//
//            // 상세주소 복호화
//            String decodedDetailAddress = AES192Util.decrypt(memberDTO.getDetailAddress());
//            returnMemberDto.setDetailAddress(decodedDetailAddress);
//
//            // 성별 복호화
//            String decodedGender = AES128Util.decrypt(memberDTO.getGender());
//            returnMemberDto.setGender(decodedGender);
//
//            // 생년월일 복호화
////        java.sql.Date decodedBirthday = java.sql.Date.valueOf(AES128Util.decrypt(String.valueOf(memberDTO.getBirthday())));
////        memberDTO.setBirthday(decodedBirthday);
//
//            return returnMemberDto;
//        }

    public void decryptPersonalData (DF01_MemberDTO memberDTO) throws Exception {
        DF01_MemberDTO returnMemberDto = new DF01_MemberDTO();

        // 이메일 복호화
        String decodedEmail = AES256Util.decrypt(memberDTO.getEmail());
        returnMemberDto.setEmail(decodedEmail);

        // 이름 복호화
        String decodedName = AES256Util.decrypt(memberDTO.getName());
        returnMemberDto.setName(decodedName);

        // 폰 번호 복호화
        String decodedPhone = AES256Util.decrypt(memberDTO.getPhone());
        returnMemberDto.setPhone(decodedPhone);

        // 지번 복호화
        String decodedZipcode = AES192Util.decrypt(memberDTO.getZipcode());
        returnMemberDto.setZipcode(decodedZipcode);

        // 도로명 복호화
        String decodedStreetAddress = AES192Util.decrypt(memberDTO.getStreetAddress());
        returnMemberDto.setStreetAddress(decodedStreetAddress);

        // 상세주소 복호화
        String decodedDetailAddress = AES192Util.decrypt(memberDTO.getDetailAddress());
        returnMemberDto.setDetailAddress(decodedDetailAddress);

        // 성별 복호화
        String decodedGender = AES128Util.decrypt(memberDTO.getGender());
        returnMemberDto.setGender(decodedGender);

        // 생년월일 복호화
//        java.sql.Date decodedBirthday = java.sql.Date.valueOf(AES128Util.decrypt(String.valueOf(memberDTO.getBirthday())));
//        memberDTO.setBirthday(decodedBirthday);

    }
    }
