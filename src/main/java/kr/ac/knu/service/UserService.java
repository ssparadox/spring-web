package kr.ac.knu.service;

import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.domain.facebook.FacebookUser;
import kr.ac.knu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by rokim on 2017. 5. 18..
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public KnuUser findKnuUser(String facebookUserId) {
        return userRepository.findByUserId(facebookUserId);
    }

    public KnuUser findAndCreateKnuUser(FacebookUser facebookUser) {
        KnuUser knuUser = userRepository.findByUserId(facebookUser.getUserId());
        if (knuUser == null) {
            knuUser = new KnuUser();
            knuUser.setUserId(facebookUser.getUserId());
            knuUser.setEmail(facebookUser.getEmail());
            knuUser.setPictureUrl(facebookUser.getPicture());
            knuUser.setName(facebookUser.getName());
            knuUser.setGender(facebookUser.getGender());
            knuUser.setLikeAt(new Date());
            knuUser.setCountDisLike(0);
            knuUser.setCountLike(0);
            knuUser = userRepository.save(knuUser);
        }
        return knuUser;

    }
}
