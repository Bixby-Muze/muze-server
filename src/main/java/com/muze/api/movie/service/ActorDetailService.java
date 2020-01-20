package com.muze.api.movie.service;

import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 배우 디테일 조회 서비스
 * @author ooeunz
 */

@Service
public class ActorDetailService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.actorDetailUrl}")
    private String actorDetailUrl;

    @Value("${api.key}")
    private String key;

    public ResponseMessage getActorDetail(String peopleCd) {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + actorDetailUrl)
                .queryParam("key", key)
                .queryParam("peopleCd", peopleCd)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[배우 디테일 조회]");
        System.out.println("peopleCd: " + peopleCd);
        System.out.println("URI: " + uri);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("peopleInfoResult", restTemplate.getForObject(uri, Map.class));

        return responseMessage;
    }
}
