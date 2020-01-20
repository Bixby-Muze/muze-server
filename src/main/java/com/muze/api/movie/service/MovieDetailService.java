package com.muze.api.movie.service;

import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 영화 리스트 조회 서비스
 * @author ooeunz
 */

@Service
public class MovieDetailService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.movieDetailUrl}")
    private String movieDetailUrl;

    @Value("${api.key}")
    private String key;

    public ResponseMessage getMovieDetail(String movieCd) {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + movieDetailUrl)
                .queryParam("key", key)
                .queryParam("movieCd", movieCd)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        Map<String,Object> map = restTemplate.getForObject(uri, Map.class);
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, map);

        return responseMessage;
    }
}
