package com.mmddvg.taskmanager.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final Environment env;

    public JwtService(Environment env) {
        this.env = env;
    }

    public boolean isValid(String token){
        return extractSingleClaim(token,Claims::getExpiration).before(new Date());
    }

    public String generateJwt(Map<String ,Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getJwtKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return extractSingleClaim(token,Claims::getSubject);
    }

    private Key getJwtKey(){
        byte[] keyBytes =Decoders.BASE64.decode(env.getProperty("security.jwt.secret-key"));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractSingleClaim(String token, Function<Claims,T> claimsResolver){
        var tmp = extractAllClaims(token);
        return claimsResolver.apply(tmp);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getJwtKey()).build().parseClaimsJws(token).getBody();
    }
}
