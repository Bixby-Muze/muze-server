package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * 배우 리스트 조회 서비스
 * @author ooeunz
 */

@Service
public class ActorListService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.actorListUrl}")
    private String actorListUrl;

    @Value("${api.key}")
    private String key;


    public String getActorList(String peopleNm, String curPage) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + actorListUrl)
                .queryParam("key", key)
                .queryParam("peopleNm", peopleNm)
                .queryParam("curPage", curPage)
                .queryParam("itemPerPage", 5)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
