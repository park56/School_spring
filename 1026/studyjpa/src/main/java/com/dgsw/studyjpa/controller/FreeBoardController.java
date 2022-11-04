package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.FreeBoard;
import com.dgsw.studyjpa.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("board")
public class FreeBoardController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("list")
    public List<FreeBoard> list() {
        List<FreeBoard> list = freeBoardRepository.findAll();
        System.out.println("111 : "+list);
        return list;
    }

    @PostMapping("write")
    public String write() {
        return "write";
    }

    @PostMapping("update")
    public String update() {
        return "update";
    }

    @PostMapping("delete")
    public String delete() {
        return "delete";
    }
}
