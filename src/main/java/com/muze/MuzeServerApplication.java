package com.muze;

import com.muze.api.auth.domain.Account.Account;
import com.muze.api.auth.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MuzeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuzeServerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    CommandLineRunner bootstrapeTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            Account account = new Account("yuns994@gmail.com", "ooeunz", passwordEncoder.encode("123123"));
//            accountRepository.save(account);
//        };
//    }
}
