package com.muze.api.movie.controller;

import com.muze.api.movie.service.LikeMovieService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * 영화 찜하기
 *
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "api/like", produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeMovieController {

    @Autowired
    private LikeMovieService likeMovieService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage setLike(@RequestBody Map<String, Object> json, Authentication authentication) throws IOException {

        // TODO return 값
        return null;

    }
}
