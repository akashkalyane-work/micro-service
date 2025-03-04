package com.example.ApiGateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public JwtService(){
    }


    private SecretKey getKey() {
        String secretKey = "a976d3e879ef09ee0c6d63d82c4fde6689f946eac42cc7c5b54c10a40a0b7fe8b91e604ff274256a5bc5226f32935a87aaa3f4f995c6cc3031751bbd09fede6df595a4df7667a1390478010bd9400e74e994460c2a8c1acfc0daa9bf51c0091bcd503d912f365940ef9d4f1345b7484ddeaaf989679310bde0e26b294fd3020e988280d41eed2f2db3891bf3d2aa717dcd83958ba1dda1a9c561d6b747b31dc6371faf63d4cca4bc4849d61e77b77737ba04bd80dd5c738db253caa451734524c584845c6c3544df802283090e815eaf4eb9487fb0b3c0ef04cb68f29f3936e8beb5e27a4dab720b01c117314fdc6985543fa78f5c8e7f798783d1792d442fb6";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public  <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String getRole(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
