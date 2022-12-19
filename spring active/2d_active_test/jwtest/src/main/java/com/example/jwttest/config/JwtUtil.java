package com.example.jwttest.config;

import com.example.jwttest.dto.TokenInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private String secret =  "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa";

    private  static  final int exTime = 604800000;

    /*private final Key key;
    // 토큰 셋팅
    public JwtUtil(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    // 토큰 제작
    public TokenInfo generateToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + (60 * 24* 30));

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authorities)
                .setIssuedAt(new Date())
                .claim("id", authentication.getName())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .accessToken(accessToken)
                .build();
    }

    // 토큰 정보 추출
    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("토큰의 권한이 없음.");
        }

        // 클레임에서 권한 정보 추출
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT Token " +  e);
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token " +  e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT Token " +  e);
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty " +  e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public String jwt_getId(String token) {
        Jws jwt = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return token;
    }*/

    // 토큰 제작
    public TokenInfo generateToken(Authentication authentication) {
        TokenInfo tokenInfo = new TokenInfo(null);
        Date now = new Date();
        Date exDate = new Date(now.getTime() + exTime);

        String newJwt = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(exDate)
                .claim("id",authentication.getName())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        tokenInfo.setAccessToken(newJwt);
        return tokenInfo;

    }

    // 토큰에서 아이디 가져오기
    public String getIdFromJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid Jwt signature" +  e);
        } catch (MalformedJwtException e) {
            System.out.println("Invalid Jwt signature" +  e);
        } catch (ExpiredJwtException e) {
            System.out.println("Invalid Jwt signature" +  e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Invalid Jwt signature" +  e);
        }
        return false;
    }
}