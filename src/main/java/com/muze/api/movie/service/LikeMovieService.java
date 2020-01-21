package com.muze.api.movie.service;

import com.muze.api.auth.domain.Account.Account;
import com.muze.api.auth.domain.Account.Like;
import com.muze.api.auth.repository.AccountRepository;
import com.muze.api.auth.repository.LikeRepository;
import com.muze.api.auth.security.tokens.PostAuthorizationToken;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LikeMovieService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LikeRepository likeRepository;

    public ResponseMessage setLike(Map<String, Object> map, Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken) authentication;

        String email = token.getAccountContext().getUsername();

        Like like = new Like((String) map.get("movieCd"), (String) map.get("genreNm"));

        Account account = accountRepository.findByEmail(email);
        account.setLike(like);

        likeRepository.save(like);
        accountRepository.save(account);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("email", account.getEmail());
        responseMessage.add("like", like);

        return responseMessage;
    }

}
