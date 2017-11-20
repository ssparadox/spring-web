package kr.ac.knu.configuration;

import kr.ac.knu.domain.KnuUser;

/**
 * Created by rokim on 2017. 5. 31..
 */
public interface UserOperator {
    KnuUser getKnuUserFromAccessToken(String accessToken);
}
