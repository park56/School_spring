package com.dip.dip.controller;

import com.dip.dip.entity.FreeBoard;
import com.dip.dip.repository.FreeBoardRepository;
import com.dip.dip.req.FreeBoardReq;
import com.dip.dip.service.FreeBoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("board")
public class FreeBoardController {

    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @GetMapping("list")
    public String freeboard(Model model){

        freeBoardService.regist(
                FreeBoard.builder()
                        .id(1L)
                        .title("제목제목")
                        .content("내용")
                        .regdate(LocalDateTime.now().toString())
                        .build()
        );

        freeBoardService.regist(
                FreeBoard.builder()
                        .id(2L)
                        .title("123제목123제목")
                        .content("내용22")
                        .regdate(LocalDateTime.now().toString())
                        .build()
        );

        List<FreeBoard> list =
                freeBoardService.selectlist();

        model.addAttribute("list",list);
        System.out.println(list);

        return "freeboard/list";
    }

    @GetMapping("write")
    public String write(){
        return "freeboard/write";
    }

    @PostMapping("write")
    public String pwrite(@Valid FreeBoardReq freeBoardReq, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "freeboard/write";
        }

        freeBoardService.regist(
                FreeBoard.builder()
//                        .id(-1L)
                        .content(freeBoardReq.getContent())
                        .title(freeBoardReq.getTitle())
                        .regdate(LocalDateTime.now().toString())
                        .build()
        );
        return "redirect:/list";
    }

    @GetMapping("update")
    public String update(){
        return "freeboard/update";
    }

    @PostMapping("update")
    public String pupdate(@Valid FreeBoardReq freeBoardReq, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "freeboard/update";
        }

        freeBoardService.regist(
                FreeBoard.builder()
                        .id(freeBoardReq.getId())
                        .content(freeBoardReq.getContent())
                        .title(freeBoardReq.getTitle())
                        .regdate(LocalDateTime.now().toString())
                        .build()
        );
        return "redirect:/board/list";
    }

    @GetMapping("delete")
    public String delete(Long id){
        freeBoardRepository.deleteById(id);
        return "redirect:/board/list";
    }
}
