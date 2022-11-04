package com.dgsw.studyjpa.repository;

import com.dgsw.studyjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String>{

    User getById(String id);

}
