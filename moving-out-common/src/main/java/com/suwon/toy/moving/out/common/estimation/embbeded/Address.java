/**
 * ===============================================================
 * File name : Address.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.estimation.embbeded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(name = "location_province")
    private String locationProvince;            //     시/도

    @Column(name = "location_city")
    private String locationCity;                //      시/군/구

    @Column(name = "location_district")
    private String locationDistrict;            //      구/면/읍

    @Column(name = "location_details")
    private String locationDetails;
}
