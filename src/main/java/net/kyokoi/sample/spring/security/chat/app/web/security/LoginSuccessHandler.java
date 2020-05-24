package net.kyokoi.sample.spring.security.chat.app.web.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, Authentication authentication)
        throws IOException, ServletException {
        httpServletResponse.sendRedirect("/portal");
    }
}
