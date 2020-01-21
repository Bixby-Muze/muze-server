package com.muze.api.movie.controller;

import com.muze.api.movie.common.ImageCaching;
import com.muze.api.movie.domain.Movie;
import com.muze.api.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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
    @PostAuthorize("hasRole('ROLE_USER')")
    public void autoSaveImg() throws IOException {

        for (int curPage = 1; curPage < 70001; curPage++) {
            URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + movieListUrl)
                    .queryParam("key", key)
                    .queryParam("curPage", curPage)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUri();

            Map<String, Object> data = restTemplate.getForObject(uri, Map.class);

            Map<String, Object> movieListResult = (Map<String, Object>) data.get("movieListResult");

            List<Map<String, String>> movieList = (List<Map<String, String>>) movieListResult.get("movieList");

            String movieCd = movieList.get(0).get("movieCd");

            String imgUrl = imageCaching.getImageUrl(movieCd);

            Movie movie = new Movie(movieCd, imgUrl);
            movieRepository.save(movie);

            System.out.println("save count: " + curPage);
        }
    }
}
