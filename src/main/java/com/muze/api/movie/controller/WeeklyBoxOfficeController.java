package com.muze.api.movie.controller;

import com.muze.api.movie.service.WeeklyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/weeklyBoxOffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeeklyBoxOfficeController {

    private WeeklyBoxOfficeService weeklyBoxOfficeService;

    @Autowired
    public WeeklyBoxOfficeController(WeeklyBoxOfficeService weeklyBoxOfficeService) {
        this.weeklyBoxOfficeService = weeklyBoxOfficeService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getAll() {
        return weeklyBoxOfficeService.getAll();
    }
}
