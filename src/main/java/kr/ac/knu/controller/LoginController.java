package kr.ac.knu.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.ac.knu.domain.KnuUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by rokim on 2017. 7. 5..
 */
@RestController
@RequestMapping("/authenticate")
public class LoginController {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Facebook client access token", required = true, dataType = "string", paramType = "header", defaultValue = "")
    })
    @PostMapping("/login")
    public void login() {
        // Do not thing for security filter.
    }

    @PostMapping("/logon")
    public KnuUser logon(@ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        return knuUser;
    }
}
