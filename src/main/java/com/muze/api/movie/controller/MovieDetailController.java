package com.muze.api.movie.controller;

import com.muze.api.movie.service.MovieDetailService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 영화 디테일 조회 컨트롤러
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "api/movieDetail", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieDetailController {

    @Autowired
    private MovieDetailService movieDetailService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public ResponseMessage getMovieDetail(@RequestParam("movieCd") String movieCd) throws IOException {
        return movieDetailService.getMovieDetail(movieCd);
    }
}
