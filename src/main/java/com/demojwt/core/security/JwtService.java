package com.demojwt.core.security;

import com.demojwt.core.model.User;
import com.demojwt.core.util.SystemConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final  String SECRET_KEY = "EB87C6DADF7DC361431E230A7D49E577BC66A48D2226194D274EF0657B4E4941";

    public String extractUsername(String token, SystemConfiguration systemConfiguration){
        return extractClaim(token, systemConfiguration, Claims::getSubject);
    }

    public <T> T extractClaim(String token, SystemConfiguration systemConfiguration, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token,systemConfiguration);
        return claimResolver.apply(claims);
    }

    public String generateToken(User userDetails, SystemConfiguration systemConfiguration) {
        return generateToken(new HashMap<>(), userDetails, systemConfiguration);
    }

    public String generateToken(Map<String, Object> extraClaims, User userDetails, SystemConfiguration systemConfiguration) {
        JwtBuilder jwtBuilder = Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(systemConfiguration), SignatureAlgorithm.HS256);

        if (userDetails.isEnabled2FA()) {
            jwtBuilder.claim("require-2fa", true);
        }
        return  jwtBuilder.compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails, SystemConfiguration systemConfiguration) {
        final String username = extractUsername(token, systemConfiguration);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, systemConfiguration));
    }

    private boolean isTokenExpired(String token, SystemConfiguration systemConfiguration) {
        return extraExpiration(token, systemConfiguration).before(new Date());
    }

    private Date extraExpiration(String token, SystemConfiguration systemConfiguration) {
        return extractClaim(token,systemConfiguration,  Claims::getExpiration);
    }

    private Claims extractAllClaims(String token, SystemConfiguration systemConfiguration) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey(systemConfiguration))
                .build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(SystemConfiguration systemConfiguration) {
        byte[] keyBytes = Decoders.BASE64.decode(systemConfiguration.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
