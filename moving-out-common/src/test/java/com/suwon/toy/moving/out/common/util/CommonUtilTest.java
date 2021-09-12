/**
 * ===============================================================
 * File name : CommonUtilTest.java
 * Created by injeahwang on 2021-09-11
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CommonUtilTest {

    @Test
    public void commonDateUtilTest_convertStringToLocalDateTime1(){
        LocalDateTime localDateTime = DateUtils.convertStringToLocalDateTimeYYYYMMDDTHHMMSSSSS("2021-09-11T12:30:30.000");
        System.out.println("converted LocalDateTime : " + localDateTime);
    }
}
