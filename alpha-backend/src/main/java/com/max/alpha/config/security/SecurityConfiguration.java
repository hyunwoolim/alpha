package com.max.alpha.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").permitAll();// .anyRequest().authenticated();
    // http.authorizeRequests().anyRequest().permitAll();
    http.formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/api/secure-login")
      .defaultSuccessUrl("/login-succeeded")
      .failureUrl("/login-failed")
      .and()
      .logout()
      .logoutUrl("/api/logout")
      .logoutSuccessUrl("/logout-succeeded");
    http.csrf().disable();
    http.cors().disable();
  }

}

