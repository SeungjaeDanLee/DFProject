package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.util.AES128Util;
import com.example.util.AES192Util;
import com.example.util.AES256Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class DF01_MemberController {
    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    // AES256Util 의존성 주입
    @Autowired
    AES256Util aes256Util;

    // AES192Util 의존성 주입
    @Autowired
    AES192Util aes192Util;

    // AES128Util 의존성 주입
    @Autowired
    AES128Util aes128Util;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    // 회원가입
    @GetMapping("/new")
    public String joinPage() {
        return "DF01_member/DF0101_memberJoin";
    }

    @PostMapping("/new")
    public String memberJoin(@ModelAttribute DF01_MemberDTO memberDTO) throws Exception {

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);

        // 개인정보 암호화
        encryptPersonalData(memberDTO);

        memberService.member_join(memberDTO);
        return "redirect:/members/login";
    }

    // 회원가입시 아이디 중복 검사
//    @PostMapping("/checkId")
//    public @ResponseBody String checkId(@RequestParam("inputId") String inputId) {
//        String checkResult = memberService.checkId(inputId);
//        return checkResult;
//    }

    @PostMapping("/checkId")
    public @ResponseBody int checkIdCount(@RequestParam("inputId") String inputId){
        return memberService.checkIdCount(inputId);
    }

    // 회원가입시 닉네임 중복 검사
//    @PostMapping("/checkNickName")
//    public @ResponseBody String checkNickName(@RequestParam("inputNickName") String inputNickName) {
//        String checkResult = memberService.checkNickName(inputNickName);
//        return checkResult;
//    }
    @PostMapping("/checkNickName")
    public @ResponseBody int checkNickName(@RequestParam("inputNickName") String inputNickName) {
        return memberService.checkNickNameCount(inputNickName);
    }


    @PostMapping("/checkUpdateNickName")
    public @ResponseBody int checkNickName(@RequestParam("inputNickName") String inputNickName, HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        String usedNickName = memberDTO.getNick_name();

        if (Objects.equals(usedNickName, inputNickName)){
            return 0;
        } else {
            return memberService.checkNickNameCount(inputNickName);
        }
    }


    // 로그인
    @GetMapping("/login")
    public String loginPage() {
        return "DF01_member/DF0102_memberLogin";
    }

    @PostMapping("/login")
    public String memberLogin(@ModelAttribute DF01_MemberDTO memberDTO, HttpSession session) {
        DF01_MemberDTO loginMember = memberService.memberLogin(memberDTO);


        if (loginMember != null) {
            // 세션으로 로그인 아이디를 넘겨줌
            session.setAttribute("loginId", loginMember.getId());
            session.setAttribute("loginNickName", loginMember.getNick_name());
            session.setAttribute("loginMemberLevel", loginMember.getMember_level());

            // 세션 무한대
            session.setMaxInactiveInterval(-1);
            Integer loginMemberLevel = loginMember.getMember_level();

            if (loginMemberLevel == 0) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/";
            }
        } else {
            // 로그인 실패 시 처리
            return "redirect:/members/login?error";
        }
    }

    // 회원 상세 정보
    @GetMapping("/my")
    public String memberDetail(HttpSession session, Model model) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        logger.info("auth" + auth);

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 상세 정보 가져오기
            memberService.member_detail(memberDTO);

            // 개인정보 복호화
            decryptPersonalData(memberDTO);

            // 날짜 변경 넣기
            Date date = memberDTO.getBirthday();

            if (date != null){
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                // memberDTO의 birthday 필드에 설정
                memberDTO.setBirthday(sqlDate);
                model.addAttribute("member", memberDTO);
                return "DF01_member/DF0103_memberMyPage";
            } else {
                model.addAttribute("member", memberDTO);
                return "DF01_member/DF0103_memberMyPage";
            }


        } else {
            return "redirect:/members/login";
        }
    }


    // 회원 수정&삭제 본인 검정
    @GetMapping("/checkMe")
    public ResponseEntity<Void> checkMe(HttpSession session, @RequestParam String inputId, @RequestParam String inputPassword) {

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        if (loginId.equals(inputId)) {
            // 아이디가 일치하는 경우
            // 여기서 inputPassword와 실제 회원의 비밀번호를 확인하고, 일치하는 경우에만 작업 수행
            DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
            if (memberDTO != null && passwordEncoder.matches(inputPassword, memberDTO.getPassword())) {
                return new ResponseEntity<>(HttpStatus.OK); // 200: OK
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401: Unauthorized
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/checkMe")
//    public ResponseEntity<Void> checkMe(HttpSession session, @RequestBody Map<String, String> requestData) {
//        String inputId = requestData.get("inputId");
//        String inputPassword = requestData.get("inputPassword");
//
//        // 세션에서 아이디 가져오기
//        String loginId = (String) session.getAttribute("loginId");
//
//        if (loginId.equals(inputId)) {
//            // 아이디가 일치하는 경우
//            // 여기서 inputPassword와 실제 회원의 비밀번호를 확인하고, 일치하는 경우에만 작업 수행
//            DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
//            if (memberDTO != null && passwordEncoder.matches(inputPassword, memberDTO.getPassword())) {
//                return new ResponseEntity<>(HttpStatus.OK); // 200: OK
//            } else {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401: Unauthorized
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    // 회원 정보 수정
    @GetMapping("/update")
    public String updatePage(HttpSession session, Model model, @RequestParam String inputPassword) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null && passwordEncoder.matches(inputPassword, memberDTO.getPassword())) {
            // mno를 이용하여 회원 상세 정보 가져오기
            memberService.member_detail(memberDTO);

            decryptPersonalData(memberDTO);

            // 이메일을 @ 문자를 기준으로 분리하여 배열에 저장
            String[] emailParts = memberDTO.getEmail().split("@");
            if (emailParts.length == 2) {
                // 첫 번째 요소는 이메일 아이디, 두 번째 요소는 도메인
                String emailId = emailParts[0];
                String domain = emailParts[1];

                // 모델에 분리된 이메일 아이디와 도메인을 추가
                model.addAttribute("emailId", emailId);
                model.addAttribute("domain", "@" + domain);
            } else {
                // 이메일 형식이 올바르지 않을 경우 예외 처리
                // 필요에 따라 로깅 또는 다른 처리를 수행할 수 있음
                throw new Exception("Invalid email format");
            }


            model.addAttribute("member", memberDTO);
            return "DF01_member/DF0104_memberMyPageUpdate";
        } else {
            return "DF01_member/DF0102_login";
        }
    }

    @PostMapping("/update")
    public String memberUpdate(@ModelAttribute DF01_MemberDTO memberDTO, HttpSession session) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        logger.info("memberDTO" + memberDTO);
        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO loginMember = memberService.findByLoginId(loginId);
        if (loginMember != null) {
            // 가져온 정보를 업데이트에 필요한 DTO로 설정
            memberDTO.setMno(loginMember.getMno());
            memberDTO.setMember_level(loginMember.getMember_level());

            // 개인정보 암호화
            encryptPersonalData(memberDTO);

            // 회원 정보 업데이트
            memberService.member_update(memberDTO);

            return "redirect:/members/my";
        } else {
            return "redirect:/members/login";
        }
    }


    // 회원 탈퇴
    @GetMapping("/delete")
    public String memberDelete(HttpSession session, @RequestParam String inputPassword) {

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (inputPassword == null){
            return "redirect:/error/400";
        }

        if (memberDTO != null && passwordEncoder.matches(inputPassword, memberDTO.getPassword())) {
            // mno를 이용하여 회원 탈퇴 수행
            memberService.member_delete(memberDTO.getMno());
            // 세션에서 아이디 제거
            session.removeAttribute("loginId");
            return "redirect:/";
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
            return "redirect:/members/login";
        }

//        if (memberDTO != null){
//            if (passwordEncoder.matches(inputPassword, memberDTO.getPassword())){
//                // mno를 이용하여 회원 탈퇴 수행
//                memberService.member_delete(memberDTO.getMno());
//                // 세션에서 아이디 제거
//                session.removeAttribute("loginId");
//                return "redirect:/";
//            } else {
//                return "redirect:/error/400";
//            }
//        } else {
//            return "redirect:/members/login";
//        }


    }
    
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginId");
        return "redirect:/";
    }

    // 이메일과 폰 번호를 암호화하는 메서드
    private void encryptPersonalData(DF01_MemberDTO memberDTO) throws Exception {
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

    private void decryptPersonalData(DF01_MemberDTO memberDTO) throws Exception {
        // 이메일 복호화
        String decodedEmail = AES256Util.decrypt(memberDTO.getEmail());
        memberDTO.setEmail(decodedEmail);

        // 이름 복호화
        String decodedName = AES256Util.decrypt(memberDTO.getName());
        memberDTO.setName(decodedName);

        // 폰 번호 복호화
        String decodedPhone = AES256Util.decrypt(memberDTO.getPhone());
        memberDTO.setPhone(decodedPhone);

        // 지번 복호화
        String decodedZipcode = AES192Util.decrypt(memberDTO.getZipcode());
        memberDTO.setZipcode(decodedZipcode);

        // 도로명 복호화
        String decodedStreetAddress = AES192Util.decrypt(memberDTO.getStreetAddress());
        memberDTO.setStreetAddress(decodedStreetAddress);

        // 상세주소 복호화
        String decodedDetailAddress = AES192Util.decrypt(memberDTO.getDetailAddress());
        memberDTO.setDetailAddress(decodedDetailAddress);

        // 성별 복호화
        String decodedGender = AES128Util.decrypt(memberDTO.getGender());
        memberDTO.setGender(decodedGender);

        // 생년월일 복호화
//        java.sql.Date decodedBirthday = java.sql.Date.valueOf(AES128Util.decrypt(String.valueOf(memberDTO.getBirthday())));
//        memberDTO.setBirthday(decodedBirthday);
    }

}
