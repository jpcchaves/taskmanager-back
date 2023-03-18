package com.ws.taskmanager.security;

import com.ws.taskmanager.exceptions.BadRequestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT Token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from jwt token
    public String getUsernameFromToken(String token) {

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // validate jwt token
    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (MalformedJwtException ex) {
            throw new BadRequestException("Token inválido!");
        } catch (ExpiredJwtException ex) {
            throw new BadRequestException("Token expirado!");
        } catch (UnsupportedJwtException ex) {
            throw new BadRequestException("O tipo do token é inválido!");
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("O token é obrigatório para acessar esse recurso!");
        }
    }
}
