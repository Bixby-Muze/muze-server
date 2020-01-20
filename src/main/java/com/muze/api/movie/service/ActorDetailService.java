package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

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

    public String getActorDetail(String peopleCd) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + actorDetailUrl)
                .queryParam("key", key)
                .queryParam("peopleCd", peopleCd);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
