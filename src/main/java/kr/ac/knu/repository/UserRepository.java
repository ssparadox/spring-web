package kr.ac.knu.repository;

import kr.ac.knu.domain.KnuUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rokim on 2017. 5. 18..
 */
public interface UserRepository extends JpaRepository<KnuUser, Integer> {
    KnuUser findByUserId(String userId);
}
