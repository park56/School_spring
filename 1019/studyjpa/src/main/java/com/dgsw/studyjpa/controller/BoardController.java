package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.Board;
import com.dgsw.studyjpa.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class BoardController {

        @Autowired
        BoardRepository boardRepository;

        @GetMapping("list")
        public List<Board> list() {
            return boardRepository.findAll();
        }
}
