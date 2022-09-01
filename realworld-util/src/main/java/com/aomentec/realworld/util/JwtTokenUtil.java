package com.aomentec.realworld.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

/**
 * @author : noelsaldanha
 * @created : 2022-08-30
**/

@Component
public class JwtTokenUtil {

  private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
  
  // Token and encoded base64Secret
  public Claims parseJwt(String token, SecretKey key) {

    try {
      Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
      return claims;
    } catch (Exception ex) {
      return null;
    }
  }

  public SecretKey createKey(String secret) {
    return new SecretKeySpec(secret.getBytes(), signatureAlgorithm.getJcaName());
  }

  public String createJwt(String subject, String issuer, String audience, String secret, long TTLMillis) {
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    SecretKey secretKey = createKey(secret);

    JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
            .setSubject(subject)
            .setIssuer(issuer)
            .setAudience(audience)
            .signWith(secretKey);

    if (TTLMillis > 0) {
      long expMillis = nowMillis + TTLMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp).setNotBefore(now);
    }

    return builder.compact();
  }

  public String getToken(String authHeader) {
    if (authHeader == null) {
      return null;
    } else {
      String[] split = authHeader.split(" ");
      if (split.length < 2) {
        return null;
      } else {
        return split[1];
      }
    }
  }

}
