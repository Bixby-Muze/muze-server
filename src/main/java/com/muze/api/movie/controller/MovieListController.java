package com.muze.api.movie.controller;

import com.muze.api.movie.service.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movieList", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieListController {

    @Autowired
    private MovieListService movieListService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getAll(@RequestParam("movieNm") String movieNm,
                         @RequestParam("directorNm") String directorNm,
                         @RequestParam("openStartDt") String openStartDt,
                         @RequestParam("openEndDt") String openEndDt) {
        return movieListService.getAll(movieNm, directorNm, openStartDt, openEndDt);
    }
}
