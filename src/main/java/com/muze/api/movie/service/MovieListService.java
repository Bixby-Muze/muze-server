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
 * 영화 리스트 조회 서비스
 *
 * @author ooeunz
 */

@Service
public class MovieListService extends AbstractMovie {

    @Value("${api.url.movieListUrl}")
    private String movieListUrl;

    private Map<String, Object> insertImgUrl(Map<String, Object> data) throws IOException {

        // inside
        Map<String, Object> movieListResult = (Map<String, Object>) data.get("movieListResult");

        List<Map<String, String>> movieList = (List<Map<String, String>>) movieListResult.get("movieList");

        List<Map<String, String>> exMovieList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < movieList.size(); i++) {

            Map<String, String> movieInfoOne = movieList.get(i);

            String movieCd = movieInfoOne.get("movieCd");

            String imgUrl = imageCaching.getImageUrl(movieCd);

            movieInfoOne.put("imgUrl", imgUrl);
            exMovieList.add(movieInfoOne);
        }

        // outside
        movieListResult.put("movieList", exMovieList);

        return movieListResult;
    }

    public ResponseMessage getAll(String movieNm, String directorNm, String openStartDt, String openEndDt) throws IOException {

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

        Map<String, Object> data = restTemplate.getForObject(uri, Map.class);


        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);
        responseMessage.add("movieListResult", insertImgUrl(data));

        return responseMessage;
    }
}
