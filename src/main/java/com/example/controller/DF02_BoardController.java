package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class DF02_BoardController {

    @GetMapping("/write")
    public String writeBoardPage(){
        return "DF02_board/DF0201_wirteBoard";
    }

    @GetMapping("/view")
    public String viewBoardPage(){
        return "DF02_board/DF0202_viewBoard";
    }

    @GetMapping("/boardList")
    public String boardListPage(){
        return "DF02_board/DF0203_boardList";
    }
}
