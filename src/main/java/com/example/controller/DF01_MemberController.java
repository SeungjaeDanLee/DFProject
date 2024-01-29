package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    // 회원가입
    @GetMapping("/new")
    public String joinPage() {
        return "DF01_member/DF0101_join";
    }

    @PostMapping("/new")
    public String memberJoin(@ModelAttribute DF01_MemberDTO memberDTO) {

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encodedPassword);

        memberService.member_join(memberDTO);
        return "redirect:/members/login";
    }

    // 회원가입시 아이디 중복 검사
    @PostMapping("/checkId")
    public @ResponseBody String checkId(@RequestParam("inputId") String inputId) {
        String checkResult = memberService.checkId(inputId);
        return checkResult;
    }

    // 회원가입시 닉네임 중복 검사
    @PostMapping("/checkNickName")
    public @ResponseBody String checkNickName(@RequestParam("inputNickName") String inputNickName) {
        String checkResult = memberService.checkNickName(inputNickName);
        return checkResult;
    }


    // 로그인
    @GetMapping("/login")
    public String loginPage() {
        return "DF01_member/DF0102_login";
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

            if (loginMemberLevel == 0){
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
    public String memberDetail(HttpSession session, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("auth" + auth);


        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 상세 정보 가져오기
            memberService.member_detail(memberDTO);
            model.addAttribute("member", memberDTO);
            return "DF01_member/DF0103_mypage";
        } else {
            return "redirect:/members/login";
        }
    }


    // 회원 정보 수정
    @GetMapping("/update")
    public String updatePage(HttpSession session, Model model) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 상세 정보 가져오기
            memberService.member_detail(memberDTO);
            model.addAttribute("member", memberDTO);
            return "DF01_member/DF0104_update";
        } else {
            return "DF01_member/DF0102_login";
        }
    }

    @PostMapping("/update")
    public String memberUpdate(@ModelAttribute DF01_MemberDTO updatedMember, HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            // 가져온 정보를 업데이트에 필요한 DTO로 설정
            updatedMember.setMno(memberDTO.getMno());

            // 회원 정보 업데이트
            memberService.member_update(updatedMember);

            return "redirect:/members/my";
        } else {
            return "redirect:/members/login";
        }
    }


    // 회원 탈퇴
    @GetMapping("/delete")
    public String memberDelete(HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 탈퇴 수행
            memberService.member_delete(memberDTO.getMno());
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }

        // 세션에서 아이디 제거
        session.removeAttribute("loginId");
        return "redirect:/";
    }


    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginId");
        return "redirect:/";
    }
}
