package com.muze.api.movie.controller;

import com.muze.api.movie.service.WeeklyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 주간 박스오피스 조회 컨트롤러
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "/api/weeklyBoxOffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeeklyBoxOfficeController {

    @Autowired
    private WeeklyBoxOfficeService weeklyBoxOfficeService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getAll(@RequestParam("targetDt") String targetDt) {
        return weeklyBoxOfficeService.getAll(targetDt);
    }
}
