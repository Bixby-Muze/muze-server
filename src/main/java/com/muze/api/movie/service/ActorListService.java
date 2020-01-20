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
 * 배우 리스트 조회 서비스
 * @author ooeunz
 */

@Service
public class ActorListService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.actorListUrl}")
    private String actorListUrl;

    @Value("${api.key}")
    private String key;


    public ResponseMessage getActorList(String peopleNm, String curPage) {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + actorListUrl)
                .queryParam("key", key)
                .queryParam("peopleNm", peopleNm)
                .queryParam("curPage", curPage)
                .queryParam("itemPerPage", 5)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[배우 리스트 조회]");
        System.out.println("peopleNm: " + peopleNm);
        System.out.println("curPage: " + curPage);
        System.out.println("itemPerPage: " + 5);
        System.out.println("URI: " + uri);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("peopleListResult", restTemplate.getForObject(uri, Map.class));

        return responseMessage;
    }
}
