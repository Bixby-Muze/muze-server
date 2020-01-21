package com.muze.api.movie.controller;

import com.muze.api.movie.service.WeeklyBoxOfficeService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseMessage getAll(@RequestParam("targetDt") String targetDt) throws IOException {
        return weeklyBoxOfficeService.getAll(targetDt);
    }
}
