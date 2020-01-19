package com.muze.api.movie.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class DailyBoxOffice implements Serializable {

    private String rnum; // 순번
    private String rank; // 해당일자의 박스오피스 순위
    private String rankIntent; // 전일대비 순위의 증감분
    private String rankOldAndNew; // 랭킹에 신규진입여부를 출력합니다. “OLD” : 기존 , “NEW” : 신규
    @Id
    @GeneratedValue
    private String movieCd; // 영화의 대표코드
    private String movieNm; // 영화명(국문)
    private String openDt; // 영화의 개봉일
    private String salesAmt; // 해당일의 매출액
    private String salesShare; // 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율
    private String salesInten; // 	전일 대비 매출액 증감분
    private String salesChange; // 전일 대비 매출액 증감 비율
    private String salesAcc; // 누적매출액
    private String audiCnt; // 	해당일의 관객수
    private String audiInten; // 전일 대비 관객수 증감분
    private String audiChange; // 전일 대비 관객수 증감 비율
    private String audiAcc; // 누적관객수
    private String scrnCnt; // 해당일자에 상영한 스크린수
    private String showCnt; // 해당일자에 상영된 횟수

    public DailyBoxOffice() {
    }

    public DailyBoxOffice(String rnum, String rank, String rankIntent, String rankOldAndNew, String movieCd, String movieNm, String openDt, String salesAmt, String salesShare, String salesInten, String salesChange, String salesAcc, String audiCnt, String audiInten, String audiChange, String audiAcc, String scrnCnt, String showCnt) {
        this.rnum = rnum;
        this.rank = rank;
        this.rankIntent = rankIntent;
        this.rankOldAndNew = rankOldAndNew;
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.openDt = openDt;
        this.salesAmt = salesAmt;
        this.salesShare = salesShare;
        this.salesInten = salesInten;
        this.salesChange = salesChange;
        this.salesAcc = salesAcc;
        this.audiCnt = audiCnt;
        this.audiInten = audiInten;
        this.audiChange = audiChange;
        this.audiAcc = audiAcc;
        this.scrnCnt = scrnCnt;
        this.showCnt = showCnt;
    }
}
