package kr.co.sloop.security;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

  /*아이디의 권한에 따라 이동하는 페이지가 다르게 표시된다.*/
  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth)
          throws IOException, ServletException {

    log.warn("Login Success");
    List<String> roleNames = new ArrayList<>();

    auth.getAuthorities().forEach(authority -> {
      roleNames.add(authority.getAuthority());
    });

    log.warn("ROLE NAME : " + roleNames);

    if (roleNames.contains("ROLE_ADMIN")){
      httpServletResponse.sendRedirect("/member/list");
      return;
    }
    if (roleNames.contains("ROLE_MEMBER")){
      httpServletResponse.sendRedirect("/");
      return;
    }
    httpServletResponse.sendRedirect("/");
  }

}
