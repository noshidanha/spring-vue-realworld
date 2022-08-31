package com.aomentec.realworld.web.config.security;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import com.aomentec.realworld.core.entity.User;
import com.aomentec.realworld.core.service.IUserService;
import com.aomentec.realworld.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

/**
 * @author : noelsaldanha
 * @created : 2022-08-30
**/

@Component
public class JwtTokenProvider {

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Autowired
  IUserService userService;

  @Value("${jwt.secret}")
  String secret;

  @Value("${jwt.sessionTime}")
  int sessionTime;

  private SecretKey secretKey;

  @PostConstruct
  protected void init() {
    this.secretKey = jwtTokenUtil.createKey(this.secret);
  }

  public String resolveToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    return jwtTokenUtil.getToken(authHeader);
  }

  // Returns ID of user that logged in
  public String getSubject(String token) {
    return jwtTokenUtil.parseJwt(token, this.secretKey).getSubject();
  }

  public Authentication getAuthentication(String token, HttpServletRequest request) {
    User user = userService.getById(getSubject(token));
    UsernamePasswordAuthenticationToken upatoken = new UsernamePasswordAuthenticationToken(user, "", Collections.emptyList()); //Usually authorities
    upatoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    return upatoken;
  }


}
