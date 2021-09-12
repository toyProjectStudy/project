/**
 * ===============================================================
 * File name : DateUtils.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Date와 관련된 유틸성 기능들을 응집한 클래스 입니다. */
public class DateUtils {
    public static String YYYYMMDD_T_HHMMSS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static LocalDateTime convertStringToLocalDateTimeYYYYMMDDTHHMMSSSSS(String dateTimeStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYYMMDD_T_HHMMSS_SSS);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
