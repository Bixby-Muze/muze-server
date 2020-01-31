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
 * 주간 박스오피스 조회 서비스
 *
 * @author ooeunz
 */

@Service
public class WeeklyBoxOfficeService extends AbstractMovie{

    @Value("${api.url.weeklyBoxOfficeUrl}")
    private String weeklyBoxOfficeUrl;

    private Map<String, Object> insertImgUrl(Map<String, Object> data) throws IOException {

        Map<String, Object> boxOfficeResult = (Map<String, Object>) data.get("boxOfficeResult");

        List<Map<String, String>> weeklyBoxOfficeList = (List<Map<String, String>>) boxOfficeResult.get("weeklyBoxOfficeList");

        List<Map<String, String>> exWeeklyBoxOfficeList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < weeklyBoxOfficeList.size(); i++) {

            Map<String, String> weeklyBoxOfficeOne = weeklyBoxOfficeList.get(i);

            String movieCd = weeklyBoxOfficeOne.get("movieCd");

            String imgUrl = imageCaching.getImageUrl(movieCd);

            weeklyBoxOfficeOne.put("imgurl", imgUrl);
            exWeeklyBoxOfficeList.add(weeklyBoxOfficeOne);
        }

        boxOfficeResult.put("weeklyBoxOfficeList", exWeeklyBoxOfficeList);

        return boxOfficeResult;
    }

    public ResponseMessage getAll(String targetDt) throws IOException {

        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + weeklyBoxOfficeUrl)
                .queryParam("key", key)
                .queryParam("targetDt", targetDt)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        System.out.println("[주간 박스오피스 조회 서비스]");
        System.out.println("targetDt: " + targetDt);
        System.out.println("URI: " + uri);

        Map<String, Object> data = restTemplate.getForObject(uri, Map.class);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("boxOfficeResult", insertImgUrl(data));

        return responseMessage;
    }
}