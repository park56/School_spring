package com.example.jwttest.controller;

import com.example.jwttest.config.JwtUtil;
import com.example.jwttest.dto.WriteDto;
import com.example.jwttest.entity.Board;
import com.example.jwttest.repository.BoardRepository;
import com.example.jwttest.response.CommonResponse;
import com.example.jwttest.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("write")               // 게시물 쓰기
    public CommonResponse write(@RequestHeader("Authorization") String token, @RequestBody WriteDto board) {

        Board board1 = Board.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .views(1)
                .build();
        CommonResponse CommonResponse = boardService.boardServiceWrite(board1, token);

        return CommonResponse;
    }

    @PostMapping("update")              // 게시물 수정
    public CommonResponse update(@RequestBody Board board) {
        boardRepository.save(board);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setAllCommonResponse(200, true, "업데이트가 되었습니다");
        return commonResponse;
    }

    @PostMapping("delete")              // 게시물 삭제
    public CommonResponse delete(@RequestParam("id") String id) {

        Long new_id = Long.parseLong(id);
        boardRepository.deleteById(new_id);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.setAllCommonResponse(200, true, "삭제가 되었습니다");
        return commonResponse;
    }

    @PostMapping("uploadimage")         // 파일 업로드
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file, @RequestBody Board board) {

        Map<String, String> newMap = new HashMap<>();

        Path path = Paths.get("/Users/sunghan/Documents/GitHub/School_spring/2_activiti test/jwtest/src/main/resources/static/image" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e) {
            e.printStackTrace();
            newMap.put("message", "파일 업로드 실패");
            return new ResponseEntity<>(newMap, HttpStatus.BAD_REQUEST);
        }

        Board newBoard = boardRepository.findById(board.getId()).get();

        System.out.println("upload img id = " + newBoard.getId());
        System.out.println("upload img name = " + file.getOriginalFilename());

        newBoard.setImageName(file.getOriginalFilename());

        boardRepository.save(newBoard);

        newMap.put("message","upload image success");
        return new ResponseEntity<>(newMap, HttpStatus.ACCEPTED);
    }

    private int flag = 0;
    @GetMapping("view")                 // 게시물 보기
    public Board view(@RequestParam("id") String id) {
        flag += 1;

        Long new_id = Long.parseLong(id);

        Board board = boardRepository.findById(new_id).get();

        if(flag >= 2){
            board.setViews(board.getViews() + 1);
            flag = 0;
        }

        boardRepository.save(board);

        return board;
    }

    @GetMapping("search")   // 제목을 이용한 검색
    public List<Board> boardSearch(@RequestParam("title") String title) {

        if(title.equals("")){
            return null;
        }

        return boardRepository.findByTitleLike(title);
    }

    @GetMapping("list")                 // 전체 내용 보기
    public List<Board> list() {
       return boardRepository.findAllByOrderByIdDesc();
    }
}
