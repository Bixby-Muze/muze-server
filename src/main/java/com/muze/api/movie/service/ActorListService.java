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
 *
 * @author ooeunz
 * @author yoogle
 */

@Service
public class ActorListService extends AbstractMovie{

    @Value("${api.url.actorListUrl}")
    private String actorListUrl;

    public ResponseMessage getActorList(String curPage, String peopleNm, String filmoNames) {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + actorListUrl)
                .queryParam("key", key)
                .queryParam("curPage", curPage)
                .queryParam("peopleNm", peopleNm)
                .queryParam("filmoNames", filmoNames)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println(uri.toString());
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
