package com.muze.api.movie.common;

import com.muze.api.movie.domain.Movie;
import com.muze.api.movie.repository.MovieRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * GET Image URL in database OR crawling
 *
 * @author ooeunz
 */

@Component
public class ImageCaching {

    @Autowired
    private MovieRepository movieRepository;

    public String crawling(String code) throws IOException {

        final String URL = "http://kobis.or.kr/kobis/business/mast/mvie/searchMovieDtl.do";
        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

        final String BASE_URL = "http://kobis.or.kr";

        HashMap<String, String> body = new HashMap<>();
        body.put("code", code);
        body.put("titleYN", "Y");

        Document doc = Jsoup.connect(URL)
                .data(body)
                .userAgent(USER_AGENT)
                .post();
        Elements elements = doc.select(".info1 img");
        String imgUrl = elements.attr("src");

        return new String(BASE_URL + imgUrl);
    }

    public String getImageUrl(String code) throws IOException {

        System.out.println("[movie repo type]");
        System.out.println(this.movieRepository.getClass().getName());

        Movie movie = movieRepository.findByCode(code);

        if (ObjectUtils.isEmpty(movie)) {

            String imgUrl = crawling(code);
            Movie exMovie = new Movie(code, imgUrl);

            movieRepository.save(exMovie);

            System.out.println("[Save Movie ImgUrl]");
            System.out.println("code: " + code);
            System.out.println("imgUrl: " + imgUrl);

            return imgUrl;
        }

        return movie.getImgUrl();
    }
}
