package com.muze.api.movie.controller;

import com.muze.api.movie.service.ActorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/actorDetail", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorDetailController {

    @Autowired
    private ActorDetailService actorDetailService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getActorDetail(@RequestParam("peopleCd") String peopleCd) {
        return actorDetailService.getActorDetail(peopleCd);
    }
}
