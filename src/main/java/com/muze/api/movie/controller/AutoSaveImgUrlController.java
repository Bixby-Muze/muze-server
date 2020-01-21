package com.muze.api.movie.controller;

import com.muze.api.movie.common.ImageCaching;
import com.muze.api.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Image URL 자동저장
 * @author ooeunz
 */

@RestController
@RequestMapping("/api/authSaveImg")
public class AutoSaveImgUrlController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ImageCaching imageCaching;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.movieListUrl}")
    private String movieListUrl;

    @Value("${api.key}")
    private String key;

    @GetMapping("/mustBeUsedOnlyOnce")
    public void autoSaveImg() {

        for (int curPage = 1; curPage < 5; curPage++) {
            URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + movieListUrl)
                    .queryParam("key", key)
                    .queryParam("curPage", curPage)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUri();
            Map<String, Object> json = restTemplate.getForObject(uri, Map.class);
//            String code = (String) json.get("movieList");
            System.out.println(json);
        }

    }
}
