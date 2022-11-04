package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.Person;
import com.dgsw.studyjpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("list")
    public List<Person> list(String lastname){
        System.out.println(lastname);
        if(lastname == null)
            return personRepository.findAll();
        else
            return personRepository.findByLastName(lastname);
    }
    // insert 는 다음시간에..
}
