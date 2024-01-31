package com.example.controller.DF00;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DF00_HomeController {

    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//        logger.error("test");
//        logger.debug("한글");
//        logger.debug("1234");

    @GetMapping("/")
    public String homePage(Model model){


        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> boardViewList = boardService.findViewBoard10();
//        List<DF02_BoardDTO> boardDTOList = boardService.findAll();

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", boardViewList);
        return "DF00_index";
    }
}
