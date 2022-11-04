package com.dgsw.studyjpa.repository;

import com.dgsw.studyjpa.entity.AA;
import org.springframework.data.jpa.repository.JpaRepository;


// select, insert, update, delete 가 자동으로 만들어 진다...
public interface AARepository extends JpaRepository<AA,Long> {
}
