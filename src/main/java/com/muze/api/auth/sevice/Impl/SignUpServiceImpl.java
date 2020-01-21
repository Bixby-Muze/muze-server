package com.muze.api.auth.sevice.Impl;

import com.muze.api.auth.domain.Account.Account;
import com.muze.api.auth.dto.SignUpDto;
import com.muze.api.auth.repository.AccountRepository;
import com.muze.api.auth.sevice.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ooeunz
 *
 */

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl() {}

    public String signUp(Map<String, Object> json) {

        SignUpDto signUpDto = new SignUpDto(json);

        try {

            System.out.println(json);

            if ((signUpDto.getEmail() == null && signUpDto.getEmail().length() == 0)
                    | (signUpDto.getEmail() == null && signUpDto.getUsername().length() == 0)
                    | (signUpDto.getPassword() == null && signUpDto.getPassword().length() == 0)) {

                throw new Exception("Email, Username, Password가 채워지지 않았습니다.");
            }

            if (accountRepository.findByEmail(signUpDto.getEmail()) != null) {
                throw new Exception("해당 Email이 이미 존재합니다!");
            }

            Account account = new Account(signUpDto.getEmail(), signUpDto.getUsername(), passwordEncoder.encode(signUpDto.getPassword()));
            accountRepository.save(account);

            return account.getEmail();

        } catch (Exception e) {
            System.out.println("ErrorCode: " + e.getMessage());
            return e.getMessage();
        }
    }
}
