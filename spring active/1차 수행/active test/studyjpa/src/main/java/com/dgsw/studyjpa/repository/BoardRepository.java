package com.dgsw.studyjpa.repository;

import com.dgsw.studyjpa.entity.Board;
import com.dgsw.studyjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

// CRUD가 자동으로 만들어 진다
public interface BoardRepository extends JpaRepository<Board,Long> {
    //@Query("SELECT c from Board WHERE c.title like concat('%',:title,'%')")
    //List<Board> findByTitle(@Param("title") String title);
    List<Board> findByTitleLike(String title);
    List<Board> findAllByOrderByIdDesc();


    Board findByUsername(String name);
}
