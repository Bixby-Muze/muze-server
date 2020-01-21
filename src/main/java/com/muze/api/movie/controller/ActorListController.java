package com.muze.api.movie.controller;

import com.muze.api.movie.service.ActorListService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 배우 리스트 조회 컨트롤러
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "/api/actorList", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorListController {

    @Autowired
    private ActorListService actorListService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage getActorList(@RequestParam("curPage") String curPage, @RequestParam("peopleNm") String peopleNm) {
        return actorListService.getActorList(curPage, peopleNm);
    }
}
