package com.demo.controllerWeb;

import com.demo.model.User;
import com.demo.services.SecurityService.MyUserDetails;
import com.demo.services.SecurityService.MyUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JWTUtil {
    private String secretKey = "your_secret_key"; // Замените на свой секретный ключ
    private long validityInMilliseconds = 3600000; // 1 час

    @Autowired
    private MyUserService myUserService;

    public String createToken(Authentication authentication) { // создание токена

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

/*        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();*/


        return Jwts.builder()
                .setSubject(Long.toString(myUserDetails.getUser().getId()))
                .claim("username", myUserDetails.getUsername())
                .claim("role", myUserDetails.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) { // проверка на валидацию токена
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            // Невалидный токен
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            // Истек срок действия
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            // Неподдерживаемый токен
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            // Пустой claims
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }

    private String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    private UserDetails loadUserByUsername(String login) {
        return  myUserService.loadUserByUsername(login);
    }
}