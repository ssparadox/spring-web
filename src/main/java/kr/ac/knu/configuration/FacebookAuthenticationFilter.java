package kr.ac.knu.configuration;

import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.domain.facebook.FacebookUser;
import kr.ac.knu.restclient.FacebookClient;
import kr.ac.knu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rokim on 2017. 11. 20..
 */
@Slf4j
public class FacebookAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private UserOperator userOperator;

    public void setUserOperator(UserOperator userOperator) {
        this.userOperator = userOperator;
    }

    protected FacebookAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String accessToken = httpServletRequest.getHeader("token");
        if (accessToken == null) {
            return null;
        }

        KnuUser knuUser = userOperator.getKnuUserFromAccessToken(accessToken);
        if (knuUser == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        return new KnuAuthentication(knuUser, null);
    }
}
