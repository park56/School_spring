package com.dgsw.studyjpa.controller;

import com.dgsw.studyjpa.entity.Board;
import com.dgsw.studyjpa.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class BoardController {

        Integer count = 0;

        @Autowired
        BoardRepository boardRepository;

        @PostMapping("write")   // 게시물 작성
        public ResponseEntity<Map<String, String>> writeBoard(@RequestBody Board board) {

                HashMap<String, String> newMap = new HashMap<>();

                if(board.getTitle().equals("") || board.getUsername().equals("")){
                        newMap.put("message","제목이나 글쓴이가 비어있습니다");
                        return new ResponseEntity<>(newMap, HttpStatus.BAD_REQUEST);
                }

                if(100 <= board.getTitle().length() || 1000 <= board.getContent().length() || 50 <= board.getUsername().length()){
                        newMap.put("message","제목이나 내용이나 유저이름의 길이가 너무 길다ㄴ");
                        return new ResponseEntity<>(newMap, HttpStatus.BAD_REQUEST);
                }

                board.setLikes(1);
                board.setViews(1);

                boardRepository.save(board);

                newMap.put("message","글쓰기가 성공했습니다");
                return new ResponseEntity<>(newMap,HttpStatus.ACCEPTED);
        }

        @PostMapping("update")  // 게시물 수정
        public ResponseEntity<Map<String,String>> updateBoard(@RequestBody Board board) {

                HashMap<String, String> newMap = new HashMap<>();

                if(boardRepository.findById(board.getId()).isEmpty()) {
                        newMap.put("message","게시물이 존재하지 않습니다");
                        return new ResponseEntity<>(newMap,HttpStatus.BAD_REQUEST);
                }

                if(100 <= board.getTitle().length() || 1000 <= board.getContent().length() || 50 <= board.getUsername().length()){
                        newMap.put("message","제목이나 내용이나 유저이름의 길이가 너무 길다ㄴ");
                        return new ResponseEntity<>(newMap, HttpStatus.BAD_REQUEST);
                }

                Board newBoard = boardRepository.findById(board.getId()).get();

                newBoard.setTitle(board.getTitle());
                newBoard.setContent(board.getContent());
                newBoard.setUsername(board.getUsername());

                boardRepository.save(newBoard);

                newMap.put("message","수정을 성공했습니다");
                return new ResponseEntity<>(newMap, HttpStatus.ACCEPTED);
        }

        @GetMapping("delete")  // 게시물 삭제
        public ResponseEntity<Map<String, String>> deleteBoard(@RequestParam("id") String id) {

                System.out.println("Id ====" + id);

                HashMap<String, String> newMap = new HashMap<>();

                Long newId = Long.valueOf(id);

                if(boardRepository.findById(newId).isEmpty()){
                        newMap.put("message","게시물이 존재하지 않습니다");
                        return new ResponseEntity<>(newMap,HttpStatus.ACCEPTED);
                }

                boardRepository.deleteById(newId);

                newMap.put("message","삭제가 성공했습니다");
                return new ResponseEntity<>(newMap,HttpStatus.ACCEPTED);
        }

        @PostMapping("uploadimage")     // 파일 업로드
        public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {

                Map<String, String> newMap = new HashMap<>();

                Path path = Paths.get("/Users/sunghan/Documents/GitHub/School_spring/activiti test/studyjpa/src/main/resources/static/image" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

                try {
                        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }catch (IOException e) {
                        e.printStackTrace();
                        newMap.put("message", "파일 업로드 실패");
                        return new ResponseEntity<>(newMap,HttpStatus.BAD_REQUEST);
                }

                Board newBoard = boardRepository.findByUsername(name);

                System.out.println("upload img id = " + newBoard.getId());
                System.out.println("upload img name = " + file.getOriginalFilename());

                newBoard.setImageName(file.getOriginalFilename());

                boardRepository.save(newBoard);

                newMap.put("message","upload image success");
                return new ResponseEntity<>(newMap, HttpStatus.ACCEPTED);
        }

        @GetMapping("list")     // 모든 게시물
        public List<Board> allBoard() {
                return boardRepository.findAllByOrderByIdDesc();
        }

        @GetMapping("view")     // 게시물 보여주기
        public Board viewBoard(@RequestParam("id") String id) {

                count++;

                if (id.equals("")){
                        return null;
                }

                Long newId = Long.parseLong(id);

                Board board = boardRepository.findById(newId).get();

                if(count >= 2) {
                        board.setViews(board.getViews() + 1);   // view 더하기
                        count  = 0;
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

        @GetMapping("addlike")  // 게시판 좋아요
        public ResponseEntity<Map<String, String>> addLike(@RequestParam("id") String id) {

                System.out.println("id = "+ id);

                Map<String, String> newMap = new HashMap<>();

                if(id.equals("")){
                        newMap.put("message","id가 오지 않음");
                        return new ResponseEntity<>(newMap, HttpStatus.BAD_REQUEST);
                }

                Long newId = Long.parseLong(id);

                Board board = boardRepository.findById(newId).get();

                board.setLikes(board.getLikes() + 1);

                boardRepository.save(board);

                newMap.put("message","성공");
                return new ResponseEntity<>(newMap, HttpStatus.ACCEPTED);
        }
}
