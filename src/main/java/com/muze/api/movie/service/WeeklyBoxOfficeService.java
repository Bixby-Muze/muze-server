package com.muze.api.movie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeeklyBoxOfficeService {

    private final RestTemplate restTemplate;

    @Autowired
    public WeeklyBoxOfficeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.weeklyBoxOfficeUrl}")
    private String weeklyBoxOfficeUrl;

    @Value("${api.key}")
    private String key;

    public String getAll(String targetDt) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + weeklyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", targetDt);

//        Map<String, Object> map = new HashMap<String, Object>();
//        map = restTemplate.getForObject(builder.toUriString(), Map.class);
//        System.out.println(map.get("boxOfficeResult"));

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}