package com.aomentec.realworld.web.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author : noelsaldanha
 * @created : 2022-08-30
**/

public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
    String token = jwtTokenProvider.resolveToken(request);

    try {
      if (SecurityContextHolder.getContext().getAuthentication() == null) {
        Authentication auth = jwtTokenProvider.getAuthentication(token, request);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (Exception ex) {
      SecurityContextHolder.clearContext();
      // response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "No JWT Token in Authorization Header");
      // return;
    }

    filterChain.doFilter(request, response);
  }

}
