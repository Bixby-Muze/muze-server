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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 일간 박스오피스 조회 서비스
 * @author ooeunz
 */

@Service
public class DailyBoxOfficeService {

    private final RestTemplate restTemplate;

    @Autowired
    public DailyBoxOfficeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public ImageCaching imageCaching;

    @Value("${api.url.apiBaseUrl}")
    private String baseUrl;

    @Value("${api.url.dailyBoxOfficeUrl}")
    private String dailyBoxOfficeUrl;

    @Value("${api.key}")
    private String key;

    private Map<String, Object> insertImg(Map<String, Object> data) throws IOException {

        Map<String, Object> boxOfficeResult = (Map<String, Object>) data.get("boxOfficeResult");

        List<Map<String, String>> dailyBoxOfficeList = (List<Map<String, String>>) boxOfficeResult.get("dailyBoxOfficeList");

        List<Map<String, String>> exDailyBoxOfficeList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < dailyBoxOfficeList.size(); i++) {

            Map<String, String> dailyBoxOfficeOne = dailyBoxOfficeList.get(i);
            String movieCd = dailyBoxOfficeOne.get("movieCd");

            String imgUrl = imageCaching.getImageUrl(movieCd);

            dailyBoxOfficeOne.put("imgUrl", imgUrl);
            exDailyBoxOfficeList.add(dailyBoxOfficeOne);
        }
        boxOfficeResult.put("dailyBoxOfficeList", exDailyBoxOfficeList);

        return boxOfficeResult;
    }

    public ResponseMessage getAll(String targetDt) throws IOException {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + dailyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", targetDt)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[일간 박스오피스 조회]");
        System.out.println("targetDt: " + targetDt);
        System.out.println("URI: " + uri);


        Map<String, Object> data = restTemplate.getForObject(uri, Map.class);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("boxOfficeResult", insertImg(data));

        return responseMessage;
    }
}
