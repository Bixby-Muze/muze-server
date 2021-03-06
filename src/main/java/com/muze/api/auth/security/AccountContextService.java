package com.muze.api.auth.security;

import com.muze.api.auth.domain.Account.Account;
import com.muze.api.auth.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountContextService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Account account = accountRepository.findByEmail(email);
            return getAccountcontext(account);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private AccountContext getAccountcontext(Account account) {
        return AccountContext.fromAccountModel(account);
    }
}
