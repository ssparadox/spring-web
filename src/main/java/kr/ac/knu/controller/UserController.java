package kr.ac.knu.controller;

import kr.ac.knu.domain.KnuUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by rokim on 2017. 6. 8..
 */
@RestController
@RequestMapping("/user-service")
public class UserController {
    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    public KnuUser findKnuUser(@ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        return knuUser;
    }
}
