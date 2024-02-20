package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF04_LikePointDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.service.DF04_LikePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/like")
public class DF04_LikePointController {
    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

    @Autowired
    DF04_LikePointService likePointService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLikePoint(@RequestBody DF04_LikePointDTO likePoint,
                                                  HttpSession session) throws Exception {
        likePoint.setMno(memberService.loginMno(session));
        boolean liked = likePointService.toggleLikePoint(likePoint);

        return ResponseEntity.ok(liked ? "liked" : "unliked");
    }
}