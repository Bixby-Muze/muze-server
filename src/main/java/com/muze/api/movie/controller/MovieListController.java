package com.muze.api.movie.controller;

import com.muze.api.movie.service.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movieList", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieListController {

    private final MovieListService movieListService;

    @Autowired
    MovieListController(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getAll() {
        return movieListService.getAll();
    }

}
