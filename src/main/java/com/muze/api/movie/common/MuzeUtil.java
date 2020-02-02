package com.muze.api.movie.common;

import org.springframework.stereotype.Component;

@Component
public class MuzeUtil {

    public String convertDateStringFormat(String date) {
        try {
            StringBuffer stringBuffer = new StringBuffer(date);

            // 2019.0509
            stringBuffer.insert(4, ".");
            // 2019.05.09
            stringBuffer.insert(7, ".");

            return stringBuffer.toString();
        }catch (Exception e) {
            return null;
        }
    }
}
