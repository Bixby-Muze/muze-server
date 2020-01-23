package com.muze.api.movie.controller;

import com.muze.api.movie.service.MovieListService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 영화 리스트 조회 컨트롤러
 *
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "/api/movieList", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage getAll(@RequestParam("movieNm") String movieNm,
                                  @RequestParam("directorNm") String directorNm,
                                  @RequestParam("openStartDt") String openStartDt,
                                  @RequestParam("openEndDt") String openEndDt) throws IOException {
        return movieListService.getAll(movieNm, directorNm, openStartDt, openEndDt);
    }
}
