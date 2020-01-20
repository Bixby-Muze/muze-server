package com.muze.api.movie.controller;

import com.muze.api.movie.entity.DailyBoxOffice;
import com.muze.api.movie.service.DailyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/dailyBoxOffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class DailyBoxOfficeController {

    @Autowired
    private DailyBoxOfficeService dailyBoxOfficeService;

    public DailyBoxOfficeController(DailyBoxOfficeService dailyBoxOfficeService) {
        this.dailyBoxOfficeService = dailyBoxOfficeService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getAll(@RequestParam("targetDt") String targetDt) {
        return dailyBoxOfficeService.getAll(targetDt);
    }
}

