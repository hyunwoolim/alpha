package com.max.alpha.config.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

  @Override
  public String encode(CharSequence charSequence) {
    try {
      return getSha256((String) charSequence);
    } catch (NoSuchAlgorithmException e) {
      return "NoSuchAlgorithmException";
    }
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    return encode(charSequence).equals(s);
  }

  public String getSha256(String password) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    messageDigest.update(password.getBytes());
    byte[] digest = messageDigest.digest();
    return convertByteToHex(digest);
  }

  private static String convertByteToHex(byte[] byteData) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
  }

}
