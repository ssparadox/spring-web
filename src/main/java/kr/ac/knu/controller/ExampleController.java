package kr.ac.knu.controller;

import io.swagger.annotations.ApiOperation;
import kr.ac.knu.annotation.Interceptive;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.service.ExampleService;
import kr.ac.knu.study.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rokim on 2017. 5. 18..
 */
@Controller
@RequestMapping("/example")
@Slf4j
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        // resouces/templates/{RETURN}.html 를 찾는다
        return "index";
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String board(Model model) {
        // {RETURN}.html 에 매핑할 attribute 정보를 넣는다.
        model.addAttribute("boardList", exampleService.getDummyBoardList());

        // resouces/templates/{RETURN}.html 를 찾는다
        return "board";
    }
    
    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String board() {
        /**
         * hosts 에 local.robinkim.net 127.0.0.1 을 등록하고,
         * http://local.robinkim.net:8888/example/facebook 호출
         */
        return "facebook";
    }


    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("Response 에 직접 value 를 set 한다.")
    public String helloWorld() {
        return "Hello, World";
    }

    @RequestMapping(value = "/helloParam", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("URI 의 쿼리 파라미터를 넣는다.")
    public String helloParam(@RequestParam String param) {
        return param;
    }

    @RequestMapping(value = "/helloPath/{path}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("URI 의 path 를 파라미터로 사용한다.")
    public String helloPath(@PathVariable String path) {
        // HttpMessageConverter 가 String 을 HTTP body 에 직접 value 를 넣어준다.
        return path;
    }

    @RequestMapping(value = "/helloBody", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("POST 의 json 으로 된 request body 를 매핑하여 파라미터로 전달한다.")
    public Student helloBody(@RequestBody Student student) {
        // Response 에 직접 value 를 set 한다.
        // 이 경우 MappingJackson2HttpMessageConverter 가 Object 를 json 포맷으로 변경한다.

        // TODO insert Student

        return student;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/hello200", method = RequestMethod.GET)
    public void hello200() {
        log.info("Hello 200!");
    }

    @ResponseBody
    @RequestMapping(value = "/helloMap", method = RequestMethod.GET)
    public Map<String, String> helloMap() {
        Map<String, String> map = new HashMap<>();
        String s = "Hello, World!!";
        map.put("Message", s);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/helloUser", method = RequestMethod.GET)
    @ApiOperation(value = "POST 의 json 으로 된 request body 를 매핑하여 파라미터로 전달한다.", response = KnuUser.class)
    public KnuUser helloUser() {
        KnuUser knuUser = new KnuUser();
        knuUser.setIdx(1);
        knuUser.setUserId("userID");
        knuUser.setEmail("rokim@riotgames.com");
        return knuUser;
    }

    // WebConfig 의 Interceptor 부분을 참고
    @ResponseBody
    @RequestMapping(value = "/helloInterceptor", method = RequestMethod.GET)
    @Interceptive
    public String helloInterceptor(@RequestAttribute String sample) {
        return sample;
    }

    // WebConfig 의 Interceptor 부분을 참고
    @ResponseBody
    @RequestMapping(value = "/helloArgument", method = RequestMethod.GET)
    public String helloResolver(@ApiIgnore String springArgument) {
        return springArgument;
    }
}
