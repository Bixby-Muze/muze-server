package com.muze.api.movie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 주간 박스오피스 조회 서비스
 * @author ooeunz
 */

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

    public ResponseMessage getAll(String targetDt) {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + weeklyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", targetDt)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        Map<String,Object> map = restTemplate.getForObject(uri, Map.class);
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, map);

        return responseMessage;
    }
}