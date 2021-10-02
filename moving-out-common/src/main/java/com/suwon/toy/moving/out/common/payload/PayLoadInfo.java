/**
 * ===============================================================
 * File name : PayLoadInfo.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TB_PAYLOAD_INFO")
@NoArgsConstructor
@AllArgsConstructor
public class PayLoadInfo {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payload_info_id")
    private long payloadInfoId;

    @Column(name = "payload_category")
    private String payloadCategory;

    @Column(name = "payload_name")
    private String payloadName;

    @Column(name = "is_fragile")
    private String isFragile;

    @Column(name = "is_disassemble")
    private String isDisassemble;
}
