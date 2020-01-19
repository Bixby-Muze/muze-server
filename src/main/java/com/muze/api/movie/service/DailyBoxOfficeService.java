package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DailyBoxOfficeService {

    private final RestTemplate restTemplate;

    @Autowired
    public DailyBoxOfficeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.dailyBoxOfficeUrl}")
    private String dailyBoxOfficeUrl;

    @Value("${api.key}")
    private String key;

    public String getAll() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + dailyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", 20200119);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
