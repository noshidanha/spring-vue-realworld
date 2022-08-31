package com.aomentec.realworld.util;

import java.util.Base64;

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
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    return new SecretKeySpec(secret.getBytes(), signatureAlgorithm.getJcaName());
  }

  public String createJwt(String username, String issuer, String audience) {
    throw new UnsupportedOperationException("createJwt not implemented"); 
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
