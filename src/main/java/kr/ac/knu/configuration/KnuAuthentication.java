package kr.ac.knu.configuration;

import kr.ac.knu.domain.KnuUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by rokim on 2017. 11. 20..
 */
public class KnuAuthentication extends AbstractAuthenticationToken {
    private KnuUser knuUser;

    public KnuAuthentication(KnuUser knuUser,  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.knuUser = knuUser;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return knuUser;
    }
}
