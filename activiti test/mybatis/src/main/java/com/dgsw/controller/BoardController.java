package com.dgsw.controller;

import com.dgsw.dto.User;
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

        List<User> testlist = sqlSessionTemplate.selectList("test.findall");

        model.addAttribute("testList", testlist);
        //model.addAttribute("a","10");

        return "findall";
    }

    @PostMapping("delete")
    public String delete(@RequestParam(required = false) List<Integer> check){
            System.out.println("delete");

            if(!check.equals(null))
                check.forEach(idx -> sqlSessionTemplate.delete("test.deletetest", idx));
        return "redirect:/board/findall";
    }

    //insert 해서 테이블에 행 삽입
    @GetMapping("insert")
    public String insert(User test){
        return "insert";
    }

    @PostMapping("insert")
    public String pinsert(@Valid User test, BindingResult bindingResult, Model model) {
        //System.out.println(aa);
        //System.out.println(bb);

        if (bindingResult.hasErrors()) {
            System.out.println("빈칸 허용 ㄴㄴ");
            //model.addAttribute("error",true);
            return "insert";
        }

        System.out.println(test);
        sqlSessionTemplate.insert("test.inserttest", test);

        return "redirect:/board/findall";
        //return "findall"; 컨트롤러를 거치지 않고 바로 html 호출
    }
}


