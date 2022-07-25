package br.com.pos.aws.cbf;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableRabbit
public class CbfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbfApplication.class, args);
	}

}
