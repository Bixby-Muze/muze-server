package com.muze.api.movie.service;

import com.muze.api.movie.common.ImageCaching;
import com.muze.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
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

    @Autowired
    private ImageCaching imageCaching;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.movieDetailUrl}")
    private String movieDetailUrl;

    @Value("${api.key}")
    private String key;

    private Map<String, Object> insertImgUrl(Map<String, Object> data, String imgUrl) {

        // inside
        Map<String, Object> movieInfoResult = (Map<String, Object>) data.get("movieInfoResult");

        Map<String, String> movieInfo = (Map<String, String>) movieInfoResult.get("movieInfo");
        movieInfo.put("imgUrl", imgUrl);

        // outside
        movieInfoResult.put("movieInfo", movieInfo);

        return movieInfoResult;
    }

    public ResponseMessage getMovieDetail(String movieCd) throws IOException {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + movieDetailUrl)
                .queryParam("key", key)
                .queryParam("movieCd", movieCd)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[영화 리스트 조회 서비스]");
        System.out.println("movieCd: " + movieCd);
        System.out.println("URI: " + uri);

        Map<String, Object> data = (Map<String, Object>) restTemplate.getForObject(uri, Map.class);

        String imgUrl = imageCaching.getImageUrl(movieCd);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);

        responseMessage.add("movieInfoResult", insertImgUrl(data, imgUrl));

        return responseMessage;
    }
}
