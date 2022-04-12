package net.manthrowshat.shmurdabot.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BotSpringApplication {
    public static void springmain(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BotSpringApplication.class, args);
    }
}
