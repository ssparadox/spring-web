package kr.ac.knu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KnuWasApplication {
	public static void main(String[] args) {
		SpringApplication.run(KnuWasApplication.class, args);

	}
}
