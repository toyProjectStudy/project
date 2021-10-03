/**
 * ===============================================================
 * File name : Route.java
 * Created by injeahwang on 2021-10-02
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
public class Route {

    @Column(name = "from_location_info_id")
    private long fromLocationInfoId;

    @Column(name = "to_location_info_id")
    private long toLocationInfoId;
}
