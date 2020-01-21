package com.muze.api.movie.controller;

import com.muze.api.movie.service.DailyBoxOfficeService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 일간 박스오피스 조회 컨트롤러
 * @author ooeunz
 */

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
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage getAll(@RequestParam("targetDt") String targetDt) throws IOException {
        return dailyBoxOfficeService.getAll(targetDt);
    }
}

