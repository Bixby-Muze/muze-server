package com.muze.api.movie.controller;

import com.muze.api.movie.common.ImageCaching;
import com.muze.api.movie.service.ActorDetailService;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * 배우 디테일 조회 컨트롤러
 * @author ooeunz
 */

@RestController
@RequestMapping(value = "/api/actorDetail", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActorDetailController {

    @Autowired
    private ActorDetailService actorDetailService;

    @Autowired
    ImageCaching imageCaching;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    @PostAuthorize("hasRole('ROLE_USER')")
    public ResponseMessage getActorDetail(@RequestParam("peopleCd") String peopleCd) {
        return actorDetailService.getActorDetail(peopleCd);
    }

    @PostMapping("test")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testController(@RequestBody Map<String, Object> json) throws IOException {

        return imageCaching.getImageUrl((String) json.get("code"));
    }
}
