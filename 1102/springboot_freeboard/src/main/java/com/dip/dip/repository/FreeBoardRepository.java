package com.dip.dip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dip.dip.entity.FreeBoard;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {
}
