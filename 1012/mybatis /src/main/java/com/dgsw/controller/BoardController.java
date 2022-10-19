package com.dgsw.controller;

import com.dgsw.dto.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    // select 해서 테이블 내용 뿌려주는거
    @GetMapping("findall")
    public String findall(Model model){
        System.out.println("findall");

        List<Test> testlist = sqlSessionTemplate.selectList("test.findall");

        model.addAttribute("testList", testlist);
        model.addAttribute("a","10");

        return "findall";
    }
    //insert 해서 테이블에 행 삽입
    @GetMapping("insert")
    public String insert(Test test){
        return "insert";
    }

    @PostMapping("insert")
    public String pinsert(@Valid Test test, BindingResult bindingResult, Model model){
        //System.out.println(aa);
        //System.out.println(bb);

        if (bindingResult.hasErrors()){
            System.out.println("빈칸 허용 ㄴㄴ");
            model.addAttribute("error",true);
            return "insert";
        }

        System.out.println(test);
        sqlSessionTemplate.insert("test.inserttest",test);

        return "redirect:/board/findall";
        //return "findall"; 컨트롤러를 거치지 않고 바로 html 호출
    }

    @PostMapping("delete")
    public String delete(int[] idx){

        List<Integer> list = new ArrayList<>();

        if(idx != null){
            for(int i = 0; i<idx.length; i++) {
                list.add(idx[i]);
            }
        }
        sqlSessionTemplate.delete("test.deletetest",list);
        System.out.println("delete success");
        return "redirect:/board/findall";
    }

    @GetMapping("update")
    public String update(int idx, Test test, Model model){
        test = sqlSessionTemplate.selectOne("test.findbyidx", idx);
        model.addAttribute("test",test);
        return "insert";
    }

    @PostMapping("update")
    public String pupdate(Test test) {
        sqlSessionTemplate.update("updatetest",test);
        return "redirect:/board/findall";
    }
}