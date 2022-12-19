package com.example.jwttest.service;

import com.example.jwttest.config.JwtUtil;
import com.example.jwttest.entity.Board;
import com.example.jwttest.entity.User;
import com.example.jwttest.repository.BoardRepository;
import com.example.jwttest.repository.UserRepository;
import com.example.jwttest.response.CommonResponse;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    private JwtUtil jwtUtil = new JwtUtil();

    public CommonResponse boardServiceWrite(Board board, String token) {
        CommonResponse commonResponse = new CommonResponse();

        token = token.substring("Bearer ".length());
        String id = jwtUtil.getIdFromJWT(token);

        User user = userRepository.findById(id).get();

        board.setUserid(user.getId());
        board.setUsername(user.getName());

        System.out.println(board.toString());

        boardRepository.save(board);

        commonResponse.setAllCommonResponse(200, true, "성공적으로 글을 썻다");
        return commonResponse;
    }
}
