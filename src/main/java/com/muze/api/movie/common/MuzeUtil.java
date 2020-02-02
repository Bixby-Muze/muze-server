package com.muze.api.movie.common;

import org.springframework.stereotype.Component;

@Component
public class MuzeUtil {

    public String convertDateStringFormat(String date) {
        try {
            StringBuffer stringBuffer = new StringBuffer(date);
            System.out.println("yoogle test : " + date);

            // 20190509
            // 2019년 0509
            stringBuffer.insert(4, "년 ");
            // 2019년 05월 09
            stringBuffer.insert(8, "월 ");
            stringBuffer.insert(12, "일");

            return stringBuffer.toString();
        }catch (Exception e) {
            return null;
        }
    }
}
