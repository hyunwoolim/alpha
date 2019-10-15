package com.max.alpha.config.security;

public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

  @Override
  public String encode(CharSequence charSequence) {
    return null;
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    return false;
  }
}
