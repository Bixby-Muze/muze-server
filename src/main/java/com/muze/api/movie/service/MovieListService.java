package com.muze.api.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 영화 리스트 조회 서비스
 * @author ooeunz
 */

@Service
public class MovieListService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.movieListUrl}")
    private String movieListUrl;

    @Value("${api.key}")
    private String key;

    public String getAll(String movieNm, String directorNm, String openStartDt, String openEndDt) {

//        restTemplate.getMessageConverters()
//                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + movieListUrl)
                .queryParam("key", key)
                .queryParam("movieNm", movieNm)
                .queryParam("directorNm", directorNm)
                .queryParam("openStartDt", openStartDt)
                .queryParam("openEndDt", openEndDt)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[영화 리스트 조회]");
        System.out.println("movieNM: " + movieNm);
        System.out.println("directorNm: " + directorNm);
        System.out.println("openStartDt: " + openStartDt);
        System.out.println("openEndDt: " + openEndDt);
        System.out.println("URI: " + uri);

        return restTemplate.getForObject(uri, String.class);
    }
}
