package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.AA;
import com.dgsw.studyjpa.repository.AARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("aa")
public class AAController {

    @Autowired
    AARepository aaRepository;

    @GetMapping("save")
    public String save(){
        System.out.println("save");

        // inset 구문이 자동으로 만들어져 실행
        aaRepository.save(new AA());

        return "save";
    }

    @GetMapping("savesetting")
    public String savesetting(){
        System.out.println("savesetting");

//        AA aa = new AA();
//        aa.setAa("aa");
//        aa.setCdate(LocalDateTime.now());

        AA aa = AA.builder()
                .aa("aa")
                .bb("aa")
                .cdate(LocalDateTime.now())
                .mdate(LocalDateTime.now())
                .build();

        aaRepository.save(aa);

        return "save";
    }

    @GetMapping("all")
    public @ResponseBody List<AA> all(){    // @ResponseBody ==
        return aaRepository.findAll();
    }



}
