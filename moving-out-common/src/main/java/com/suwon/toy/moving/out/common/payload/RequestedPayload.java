/**
 * ===============================================================
 * File name : RequestedPayload.java
 * Created by injeahwang on 2021-10-01
 * ===============================================================
 */
package com.suwon.toy.moving.out.common.payload;

import com.suwon.toy.moving.out.common.estimation.EstimationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_REQUESTED_PAYLOAD")
@NoArgsConstructor
@AllArgsConstructor
public class RequestedPayload {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "requested_payload_info_id")
    private long requestedPayloadInfoId;

    @Column(name="estimation_id")
    private long number;

    @Column(name = "payload_category")
    private String payloadCategory;

    @Column(name = "payload_description")
    private String payload_description;

    @ManyToMany(mappedBy = "requestedPayloadList")
    private List<EstimationRequest> estimationRequestList = Collections.emptyList();
}
