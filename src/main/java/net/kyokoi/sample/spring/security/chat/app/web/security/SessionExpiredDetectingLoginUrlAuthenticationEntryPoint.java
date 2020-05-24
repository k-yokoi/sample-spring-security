package net.kyokoi.sample.spring.security.chat.app.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class SessionExpiredDetectingLoginUrlAuthenticationEntryPoint
        extends LoginUrlAuthenticationEntryPoint {

    public SessionExpiredDetectingLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String buildRedirectUrlToLoginPage(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException) {
        String redirectUrl = super.buildRedirectUrlToLoginPage(
                request, response, authException);
        if (isRequestedSessionInvalid(request)) {
            redirectUrl = "timeout";
        }
        return redirectUrl;
    }

    private boolean isRequestedSessionInvalid(HttpServletRequest request) {
        return request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
    }


}
