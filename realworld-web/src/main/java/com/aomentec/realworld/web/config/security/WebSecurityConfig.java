package com.aomentec.realworld.web.config.security;
/**
 * @author : noelsaldanha
 * @created : 2022-08-25
**/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  public JwtTokenProvider jwtTokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .cors()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS)
      .permitAll()
      .antMatchers("/", "/index")
      .permitAll()
      .antMatchers("/assets/*", "favicon.ico")
      .permitAll()
      .antMatchers("/actuator/*")
      .permitAll()
      .antMatchers(HttpMethod.GET, "/api/articles/feed")
      .permitAll()
      .antMatchers(HttpMethod.POST, "/api/users", "/api/users/login")
      .permitAll()
      .antMatchers(HttpMethod.GET, "/api/articles/**", "/api/profiles/**", "/api/tags")
      .permitAll()
      .anyRequest()
      // .authenticated();
      .permitAll();

    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    return http.build();
  }

}
