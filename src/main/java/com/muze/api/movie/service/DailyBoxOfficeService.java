package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * 일간 박스오피스 조회 서비스
 * @author ooeunz
 */

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

    public String getAll(String targetDt) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + dailyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", targetDt)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
