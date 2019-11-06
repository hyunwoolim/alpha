package com.max.alpha.config.security;

import com.max.alpha.model.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {

  public static void refresh(Member member) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(member, auth.getCredentials(), member.getAuthorities());
    SecurityContext ctx = new SecurityContextImpl();
    ctx.setAuthentication(token);
    RequestContextHolder.currentRequestAttributes().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, ctx, RequestAttributes.SCOPE_SESSION);
  }

  public static Member sessionMember() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth.getPrincipal() instanceof Member) {
      return (Member) auth.getPrincipal();
    }
    return null;
  }

}
