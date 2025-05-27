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
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private String secretKey = "yourShorterSecretKeyForMeLoveMeLikeYouDoBeHappyDontWorry"; // Замените на свой секретный ключ
    private long validityInMilliseconds = 3600000; // 1 час

    @Autowired
    private MyUserService myUserService;

    public String createToken(Authentication authentication) {
        // Логика создания токена
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

/*    public boolean validateToken(String token) { // проверка на валидацию токена
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
    }*/

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            // Невалидный токен
            System.err.println("Invalid JWT token: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            // Истек срок действия
            System.err.println("Expired JWT token: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            // Неподдерживаемый токен
            System.err.println("Unsupported JWT token: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            // Пустой claims
            System.err.println("JWT claims string is empty: " + ex.getMessage());
        } catch (SignatureException ex) {
            // Ошибка подписи
            System.err.println("JWT signature does not match locally computed signature: " + ex.getMessage());
        } catch (Exception ex) {
            // Общая обработка исключений
            System.err.println("Error validating JWT token: " + ex.getMessage());
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