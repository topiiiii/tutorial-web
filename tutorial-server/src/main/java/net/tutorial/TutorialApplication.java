package net.tutorial;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
@EnableCaching
public class TutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @Bean
    public Random createRandom() {
        return new Random();
    }
}
