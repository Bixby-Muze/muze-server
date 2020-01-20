package com.muze.api.movie.controller;

import com.muze.api.movie.service.ActorListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/actorList", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorListController {

    @Autowired
    private ActorListService actorListService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public String getActorList(@RequestParam("curPage") String curPage, @RequestParam("peopleNm") String peopleNm) {
        return actorListService.getActorList(curPage, peopleNm);
    }
}
