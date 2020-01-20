package com.muze.api.movie.service;

import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    public ResponseMessage getAll(String movieNm, String directorNm, String openStartDt, String openEndDt) {

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

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("movieListResult", restTemplate.getForObject(uri, Map.class));

        return responseMessage;
    }
}
