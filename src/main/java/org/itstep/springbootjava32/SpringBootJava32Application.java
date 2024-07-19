package org.itstep.springbootjava32;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = { "org.itstep.springbootjava32.model" })
@EnableJpaRepositories("org.itstep.springbootjava32.repository")
public class SpringBootJava32Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJava32Application.class, args);
    }

}
