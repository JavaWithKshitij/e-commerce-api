package com.example.authservice.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

import javax.crypto.SecretKey;
import java.util.Date;

@Data
public class JwtUtil {

    private static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("6351665468576D5A7134743777217A25432A462D4A614E645267556B58703273"));

    public static String generateToken(String username) {
        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key).compact();
    }

    public static void validateToken(String token) {
        Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    }
}
