package com.muze.api.movie.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DailyBoxOfficeController {
    @RequestMapping(value = "/dailyBoxOfficeTest", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String test() {
        return "I`m Aliver!";
    }
}
