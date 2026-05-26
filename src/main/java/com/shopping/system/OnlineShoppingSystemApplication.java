// Owner: HeenuReet | Core | Spring Boot main entry point
package com.shopping.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineShoppingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingSystemApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
