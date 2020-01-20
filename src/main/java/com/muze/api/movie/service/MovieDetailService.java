package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String getMovieDetail(String movieCd) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + movieDetailUrl)
                .queryParam("key", key)
                .queryParam("movieCd", movieCd);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
