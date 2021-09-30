package org.onekr.trial.datarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@EnableJpaAuditing
@SpringBootApplication
public class SpringDataRestApplication {

    public static ConfigurableApplicationContext app;

    public static void main(String[] args) {
        var app = SpringApplication.run(SpringDataRestApplication.class, args);
        SpringDataRestApplication.app = app;
    }

    /**
     * Pre-load the system with employees and items.
     */
    @PostConstruct
    public void init() {

//        SecurityContextHolder.clearContext();
    }

}
