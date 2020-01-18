package com.muze.api.movie.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    String movieCd; // 영화 코드
    String movieNm; // 영화명(국문)
    String movieNmEn; // 영화명(영문)
    String prdtYear; // 제작연도
    String openDt; // 개봉연도
    String typeNm; // 영화유형
    String prdtStatNm; // 제작상
    String nationAlt; // 제작국가(전체)
    String genreAlt; // 영화장르(전체)
    String repNationNm; // 대표 제작국가명
    String repGenreNm; // 대표 장르명
    String peopleNm; // 감독명
    String companyCd; // 제작사 코드
    String companyNm; // 제작사명

    public Movie() {
    }
    public Movie(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm, String prdtStatNm, String nationAlt, String genreAlt, String repNationNm, String repGenreNm, String peopleNm, String companyCd, String companyNm) {
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.movieNmEn = movieNmEn;
        this.prdtYear = prdtYear;
        this.openDt = openDt;
        this.typeNm = typeNm;
        this.prdtStatNm = prdtStatNm;
        this.nationAlt = nationAlt;
        this.genreAlt = genreAlt;
        this.repNationNm = repNationNm;
        this.repGenreNm = repGenreNm;
        this.peopleNm = peopleNm;
        this.companyCd = companyCd;
        this.companyNm = companyNm;
    }
}
