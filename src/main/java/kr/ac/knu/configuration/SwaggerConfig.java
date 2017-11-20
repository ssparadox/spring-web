package kr.ac.knu.configuration;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by rokim on 2017. 5. 30..
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(ZonedDateTime.class, String.class)
//                .globalOperationParameters(parameterList())
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }


    private Predicate<String> paths() {
        return or(
                regex("/board-service.*"),
                regex("/user-service.*"),
                regex("/authenticate.*"),
                regex("/example.*"),
                regex("/db.*"),
                regex("/health"),
                regex("/info")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KNU Board API SERVER.")
                .description("This is WAS for KNU Board.")
                .contact("rokim@riotgames.com")
                .version("1.0")
                .build();
    }

    private List<Parameter> parameterList() {
        List<Parameter> parameterList = new ArrayList<>();

        parameterList.add(new ParameterBuilder()
                .name("token")
                .description("Facebook client access token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build());

        return parameterList;
    }
}