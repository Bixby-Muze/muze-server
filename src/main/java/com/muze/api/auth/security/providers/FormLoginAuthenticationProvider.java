package com.muze.api.auth.security.providers;

import com.muze.api.auth.domain.Account.Account;
import com.muze.api.auth.repository.AccountRepository;
import com.muze.api.auth.security.AccountContext;
import com.muze.api.auth.security.tokens.PostAuthorizationToken;
import com.muze.api.auth.security.tokens.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String email = token.getEmail();
        String password = token.getPassword();

        Account account = accountRepository.findByEmail(email);
        if (isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }

        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
