/**
 * ===============================================================
 * File name : LocationInfo.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.estimation;

import com.suwon.toy.moving.out.common.estimation.embbeded.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TB_LOCATION_INFO")
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "location_id")
    private long locationId;

    @Embedded
    private Address address;

    @Column(name = "location_house_size")
    private String locationHouseSize;

    @Column(name = "location_house_type")
    private String locationHouseType;         // 주거 타입(Ex. 아파트, 빌라 ...)
}
