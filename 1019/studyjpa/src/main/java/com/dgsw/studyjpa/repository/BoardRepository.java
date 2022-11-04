package com.dgsw.studyjpa.repository;

import com.dgsw.studyjpa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// CRUD가 자동으로 만들어 진다
public interface BoardRepository extends JpaRepository<Board,String> {}
