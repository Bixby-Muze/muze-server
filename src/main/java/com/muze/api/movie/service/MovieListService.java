package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MovieListService {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieListService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.movieListUrl}")
    private String movieListUrl;

    @Value("${api.key}")
    private String key;

    public String getAll() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + movieListUrl)
                .queryParam("key", key);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
