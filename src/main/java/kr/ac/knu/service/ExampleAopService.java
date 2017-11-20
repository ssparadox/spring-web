package kr.ac.knu.service;

import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rokim on 2017. 6. 11..
 */
@Service
public class ExampleAopService {
    @Autowired private UserRepository userRepository;

//    @Transactional
    public void dealUserPoint(KnuUser from, KnuUser to, long count) {
        from.setPoint(from.getPoint() - count);
        to.setPoint(to.getPoint() + count);

        userRepository.save(from);
        userRepository.save(to);
    }
}
